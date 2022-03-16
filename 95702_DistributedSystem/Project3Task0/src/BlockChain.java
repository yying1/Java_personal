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

    public static ArrayList<Block> blocks;
    public static String chain_hash;
    public int hashespersecond;

    public BlockChain(){
        this.blocks = new ArrayList<Block>();

    }

    public String getChainHash(){return chain_hash;}

    public Timestamp getTime(){
        return new Timestamp(System.currentTimeMillis());
    }

    public Block getLatestBlock(){
        return this.blocks.get(-1);
    }

    public int getChainSize(){
        return blocks.size();
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
        this.hashespersecond = 2000000/(int)(end.getTime()-start.getTime());
    }

    public int getHashesPerSecond(){return this.hashespersecond;}

    public void addBlock(Block newblock) throws NoSuchAlgorithmException{
        newblock.setPreviousHash(this.getLatestBlock().proofOfWork());
        this.blocks.add(newblock);
        this.chain_hash = newblock.proofOfWork();

    }

    public String toString(){
        // need to get org.json.* library from https://github.com/stleary/JSON-java
        // referenced from https://www.studytonight.com/java-examples/how-to-convert-string-to-json-and-vice-versa
        JSONObject jsonObject = new JSONObject();
        ArrayList<String> block_string = new ArrayList<>();
        for (Block b: blocks){
            block_string.add(b.toString());
        }
        jsonObject.put("ds_chain", block_string);
        jsonObject.put("chainHash", this.getChainHash());
        return jsonObject.toString();
    }

    public Block getBlock(int i){
        return blocks.get(i);
    }

    public int getTotalDifficulty(){
        int totaldifficulty = 0;
        for (Block b: blocks){
            totaldifficulty += b.getDifficulty();
        }
        return totaldifficulty;
    }

    public double getTotalExpetedHashes() throws NoSuchAlgorithmException {
        double expected_hashes = 1;
        for (Block b: blocks){
            b.proofOfWork();
            expected_hashes = expected_hashes*Math.pow((1/16),b.getNonce().intValue());
        }
        return 1/expected_hashes;
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

                if (i+1 < this.getChainSize()){
                    next_previoushash = this.getBlock(i+1).getPreviousHash();
                }
                if (!((current_difficulty == current_pow_zeros) && current_pow.equals(next_previoushash))){

                    //referenced from https://stackoverflow.com/questions/24908736/java-fast-generate-string-with-zeros
                    char[] c = new char[this.getBlock(i).getDifficulty()];
                    Arrays.fill(c, '0');
                    String starting_zeros = new String(c);

                    return "Improper hash on node "+String.valueOf(i)+". Does not begin with "+starting_zeros;
                }
            }
            if(this.getChainHash().equals(this.getBlock(-1).proofOfWork())){
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
            current_difficulty = this.getBlock(i).getDifficulty();

            //referenced from https://stackoverflow.com/questions/42712736/get-count-leading-zeros-in-a-string
            current_pow_zeros = current_pow.length() - current_pow.replaceAll("^0+", "").length();

            if (i+1 < this.getChainSize()){
                next_previoushash = this.getBlock(i+1).getPreviousHash();
            }
            if (!((current_difficulty == current_pow_zeros) && current_pow.equals(next_previoushash))){
                this.getBlock(i+1).setPreviousHash(current_pow);
            }
        }
        this.chain_hash = this.getBlock(-1).proofOfWork();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException{
        BlockChain bc = new BlockChain();
        Block genesis_block = new Block(0, bc.getTime(), "", 2);
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
                System.out.println("Expected total hashes required for the whole chain: "+bc.getTotalExpetedHashes());
                //The computed nonce for most recent block.
                System.out.println("Nonce for most recent block: "+bc.getLatestBlock().getNonce());
                //The chain hash (hash of the most recent block).
                System.out.println("Chain hash: "+bc.getChainHash());

            } else if (option.equals("1")){

            } else if (option.equals("2")){

            } else if (option.equals("3")){

            } else if (option.equals("4")){

            } else if (option.equals("5")){

            } else if (option.equals("6")){
                break;
            }


        }
        readinput.close();
    }

}
