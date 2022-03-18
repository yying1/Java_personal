//Frank Yue Ying | yying2@andrew.cmu.edu

import org.json.JSONObject;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BlockChain extends java.lang.Object {

    public ArrayList<Block> blocks;
    public String chain_hash;
    public int hashespersecond;

    public BlockChain(){
        this.blocks = new ArrayList<Block>();

    }

    public String getChainHash(){return chain_hash;}

    public Timestamp getTime(){
        return new Timestamp(System.currentTimeMillis());
    }

    public Block getLatestBlock(){
        return this.blocks.get(this.getChainSize()-1);
    }

    public int getChainSize(){
        return this.blocks.size();
    }

    public void computeHashesPerSecond() throws NoSuchAlgorithmException {
        Timestamp start = getTime();
        MessageDigest input_m = MessageDigest.getInstance("SHA-256");
        String s = "00000000";
        char ch[] = s.toCharArray();
        for (int counter = 0; counter<2000000; counter++){
            StringBuffer shex = new StringBuffer();
            for(int i = 0; i < ch.length; i++) {
                String hexString = Integer.toHexString(ch[i]);
                shex.append(hexString);
            }
            byte[] digest = input_m.digest(shex.toString().getBytes(StandardCharsets.UTF_8));
            String output = String.format("%064x", new BigInteger(1, digest));
        }
        Timestamp end = getTime();
        this.hashespersecond = 2000000/(int)((end.getTime()-start.getTime())/1000);
    }

    public int getHashesPerSecond(){return this.hashespersecond;}

    public void addBlock(Block newblock) throws NoSuchAlgorithmException{
        if (this.getChainSize() == 0){
            newblock.setPreviousHash("");
        } else {
            newblock.setPreviousHash(this.chain_hash);
        }
        this.blocks.add(newblock);
        this.chain_hash = newblock.proofOfWork();
    }

    public String toString(){
        // need to get org.json.* library from https://github.com/stleary/JSON-java
        // referenced from https://www.studytonight.com/java-examples/how-to-convert-string-to-json-and-vice-versa
        JSONObject jsonObject = new JSONObject();
        ArrayList<String> block_string = new ArrayList<>();
        for (Block b: this.blocks){
            block_string.add(b.toString());
        }
        jsonObject.put("ds_chain", block_string);
        jsonObject.put("chainHash", this.getChainHash());
        return jsonObject.toString();
    }

    public Block getBlock(int i){
        return this.blocks.get(i);
    }

    public int getTotalDifficulty(){
        int totaldifficulty = 0;
        for (Block b: blocks){
            totaldifficulty += b.getDifficulty();
        }
        return totaldifficulty;
    }

    public double getTotalExpectedHashes() throws NoSuchAlgorithmException {
        double expected_hashes = 1;
        for (Block b: blocks){
            b.proofOfWork();
            expected_hashes +=Math.pow(16,b.getDifficulty());
        }
        return expected_hashes;
    }

    public String isChainValid() throws NoSuchAlgorithmException {
        int chain_size = this.getChainSize();
        int current_difficulty;
        String current_pow = "";
        String next_previoushash ="";
        int current_pow_zeros = 0;
        if (chain_size == 1){
            current_difficulty = this.getBlock(0).getDifficulty();
            current_pow = this.getBlock(0).proofOfWork();

            //referenced from https://stackoverflow.com/questions/42712736/get-count-leading-zeros-in-a-string
            current_pow_zeros = current_pow.length() - current_pow.replaceAll("^0+", "").length();

            if ((current_difficulty == current_pow_zeros) && current_pow.equals(this.getChainHash())){return "TRUE";}
            else {return "Improper hash on node 0";}
        } else{
            for (int i = 1; i < this.getChainSize()-1; i++){
                current_pow = this.getBlock(i).proofOfWork();
                current_difficulty = this.getBlock(i).getDifficulty();

                //referenced from https://stackoverflow.com/questions/42712736/get-count-leading-zeros-in-a-string
                current_pow_zeros = current_pow.length() - current_pow.replaceAll("^0+", "").length();

                if (!(current_difficulty==current_pow_zeros)){
                    //referenced from https://stackoverflow.com/questions/24908736/java-fast-generate-string-with-zeros
                    char[] c = new char[this.getBlock(i).getDifficulty()];
                    Arrays.fill(c, '0');
                    String starting_zeros = new String(c);

                    return "Improper hash on node "+String.valueOf(i)+". Does not begin with "+starting_zeros;
                }

                if (i+1 < this.getChainSize()){
                    next_previoushash = this.getBlock(i+1).getPreviousHash();
                    if (!(current_pow.equals(next_previoushash))){

                        //referenced from https://stackoverflow.com/questions/24908736/java-fast-generate-string-with-zeros
                        char[] c = new char[this.getBlock(i).getDifficulty()];
                        Arrays.fill(c, '0');
                        String starting_zeros = new String(c);

                        return "Improper hash on node "+String.valueOf(i)+". Does not match with previous hash.";
                    }
                }
            }
            if(this.getChainHash().equals(this.getBlock(this.getChainSize()-1).proofOfWork())){
                return "TRUE";
            } else {
                return "Improper hash on node "+String.valueOf(this.getChainSize()-1)+". Error with ChainHash.";
            }
        }
    }

    public void repairChain() throws NoSuchAlgorithmException {
        int current_difficulty;
        String current_pow = "";
        String next_previoushash ="";
        int current_pow_zeros = 0;
        for (int i = 1; i < this.getChainSize()-1; i++){
            current_pow = this.getBlock(i).proofOfWork();
//            current_difficulty = this.getBlock(i).getDifficulty();
//
//            //referenced from https://stackoverflow.com/questions/42712736/get-count-leading-zeros-in-a-string
//            current_pow_zeros = current_pow.length() - current_pow.replaceAll("^0+", "").length();

            if (i+1 < this.getChainSize()){
                next_previoushash = this.getBlock(i+1).getPreviousHash();
            }
            if (!(current_pow.equals(next_previoushash))){
                this.getBlock(i+1).setPreviousHash(current_pow);
            }
        }
        this.chain_hash = this.getBlock(this.getChainSize()-1).proofOfWork();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException{
        //System behavior will change in increasing difficulty level
        //Difficulty of 4 leads to 1609 ms execution and 0 ms verification
        //Difficulty of 5 leads to 7436 ms execution and 0 ms verification
        //Difficulty of 6 leads to 117037 ms execution and 0 ms verification
        //Corrupting a level 4 block leads to 719 ms verification and 360271 ms to fix

        //increasing difficulty of blocks will cause the execution time to grow exponentially but not affect non-corrupted chain verification
        //However, after corrupting the chain, both verification and fix time increased.

        BlockChain bc = new BlockChain();
        Block genesis_block = new Block(0, bc.getTime(), "Genesis", 2);
        Timestamp start;
        int index;
        String chainvalidate;
        bc.addBlock(genesis_block);
        bc.computeHashesPerSecond();
        Scanner readinput = new Scanner(System.in);
        while (true){
            System.out.println("0. View basic blockchain status.\n1. Add a transaction to the blockchain.\n2. Verify the blockchain.\n3. View the blockchain.\n4. Corrupt the chain.\n5. Hide the corruption by repairing the chain.\n6. Exit.");
            String option = readinput.nextLine();
            if(option.equals("0")){
                //The number of blocks on the chain
                System.out.println("Current size of chain: "+bc.getChainSize());
                //Difficulty of most recent block
                System.out.println("Difficulty of most recent block: "+bc.getLatestBlock().getDifficulty());
                //The total difficulty for all blocks
                System.out.println("Total difficulty for all blocks: "+bc.getTotalDifficulty());
                //Approximate hashes per second on this machine.
                System.out.println("Approximate hashes per second on this machine: "+bc.getHashesPerSecond());
                //Expected total hashes required for the whole chain.
                System.out.println("Expected total hashes required for the whole chain: "+bc.getTotalExpectedHashes());
                //The computed nonce for most recent block.
                System.out.println("Nonce for most recent block: "+bc.getLatestBlock().getNonce());
                //The chain hash (hash of the most recent block).
                System.out.println("Chain hash: "+bc.getChainHash());

            } else if (option.equals("1")){
                //the program will prompt for and then read the difficulty level for this block.
                System.out.println("Enter difficulty > 0");
                int difficulty = Integer.parseInt(readinput.nextLine());

                //It will then prompt for and then read a line of data from the user (representing a transaction).
                System.out.println("Enter transaction");
                String data = readinput.nextLine();

                //The program will then add a block containing that transaction to the block chain.
                start = bc.getTime();
                bc.addBlock(new Block(bc.getChainSize(), bc.getTime(),data,difficulty));

                // The program will display the time it took to add this block.
                System.out.println("Total execution time to add this block was "+String.valueOf(bc.getTime().getTime() - start.getTime())+" milliseconds");
                // Note: The first block added after Genesis has index 1. The second has 2 and so on.
                // The Genesis block is at position 0.
            } else if (option.equals("2")){
                //call the isChainValid method and display the results.
                start = bc.getTime();
                chainvalidate = bc.isChainValid();
                if (chainvalidate.equals("TRUE")){
                    System.out.println("Chain verification: "+chainvalidate);
                } else {
                    System.out.println("Chain verification: FALSE");
                    System.out.println(chainvalidate);
                }

                // It is important to note that this method will execute fast.
                // Blockchains are easy to validate but time consuming to modify.
                // Your program needs to display the number of milliseconds it took for validate to run.
                System.out.println("Total execution time to verify the chain was "+String.valueOf(bc.getTime().getTime() - start.getTime())+" milliseconds");

            } else if (option.equals("3")){
                //display the entire Blockchain contents as a correctly formed JSON document
                System.out.println(bc.toString());

            } else if (option.equals("4")){
                //If the user selects option 4, she wants to corrupt the chain.
                System.out.println("corrupt the Blockchain");
                // Ask her for the block index (0..size-1)
                System.out.println("Enter block ID of block to corrupt");
                index = Integer.parseInt(readinput.nextLine());
                // and ask her for the new data that will be placed in the block.
                System.out.println("Enter new data for block "+index);
                String new_block_data = readinput.nextLine();
                System.out.println("Block "+index+" now holds "+new_block_data);
                // Her new data will be placed in the block.
                bc.getBlock(index).setData(new_block_data);

            } else if (option.equals("5")){
                start = bc.getTime();
                chainvalidate = bc.isChainValid();
                if (!chainvalidate.equals("TRUE")){
                    bc.repairChain();
                }
                System.out.println("Total execution time required to repair the chain was "+String.valueOf(bc.getTime().getTime() - start.getTime())+" milliseconds");

            } else if (option.equals("6")){
                break;
            }
        }
        readinput.close();
    }

}
