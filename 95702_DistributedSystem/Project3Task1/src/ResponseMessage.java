//Frank Yue Ying | yying2@andrew.cmu.edu

import org.json.JSONObject;
import java.math.BigInteger;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ResponseMessage {
    //set up static parameter
    public static Socket clientSocket = null;

    //set up blockchain and parameters
    public ArrayList<Block> blocks;
    public String chain_hash;
    public int hashespersecond;

    // Create a new server socket
    static ServerSocket listenSocket;

    public static void main(String args[]) throws NoSuchAlgorithmException {
        try {
            // setting the server port we are using
            int serverPort = 6789;
            listenSocket = new ServerSocket(serverPort);
            listenSocket.setSoTimeout(0);
            clientSocket = listenSocket.accept();
            System.out.println("Blockchain server running");

            //clientSocket.setKeepAlive(true);
            Scanner in;
            // Set up "in" to read from the client socket
            in = new Scanner(clientSocket.getInputStream());
            PrintWriter out;
            // Set up "out" to write to the client socket
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
            System.out.println("We have a visitor");

            //Setting up blockchain fundamentals
            ResponseMessage bc = new ResponseMessage();
            bc.BlockChain();
            bc.blocks = new ArrayList<Block>();
            Block genesis_block = new Block(0, bc.getTime(), "Genesis", 2);
            Timestamp start;
            Timestamp end;
            String chainvalidate;
            bc.addBlock(genesis_block);
            bc.computeHashesPerSecond();

            //iterating to receive requests
            while (true) {
                // If we get here, then we are now connected to a client.
                String requestString= "";
                int status;

                //detecting connection lost and reconnect
                if((status = clientSocket.getInputStream().read()) == -1){
                    clientSocket = listenSocket.accept();
                    in = new Scanner(clientSocket.getInputStream());
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
                    requestString= "";
                } else {
                    requestString = String.valueOf((char)status);
                }

                if (in.hasNext()){
                    //read message from client
                    requestString = requestString+in.nextLine();

                    //process requestString into json object
                    JSONObject request_jsonObject = new JSONObject(requestString);

                    //requestString format: option,text
                        // need to get org.json.* library from https://github.com/stleary/JSON-java
                        // referenced from https://www.studytonight.com/java-examples/how-to-convert-string-to-json-and-vice-versa
                    JSONObject response_jsonObject = new JSONObject();

                    //break string into different parts
                    if (request_jsonObject.getString("selection").equals("0")) {
                        //Response : {"selection":0,
                        // "size":1,"chainHash":"0026883909AA470264145129F134489316E6A38439240D0468D69AA9665D4993",
                        // "totalHashes":256,"totalDiff":2,"recentNonce":286,"diff":2,"hps":3231017}
                        response_jsonObject.put("selection","0");
                        response_jsonObject.put("size",bc.getChainSize());
                        response_jsonObject.put("chainHash",bc.getChainHash());
                        response_jsonObject.put("totalHashes",bc.getTotalExpectedHashes());
                        response_jsonObject.put("totalDiff",bc.getTotalDifficulty());
                        response_jsonObject.put("recentNonce",bc.getLatestBlock().getNonce());
                        response_jsonObject.put("diff",bc.getLatestBlock().getDifficulty());
                        response_jsonObject.put("hps",bc.getHashesPerSecond());
                        response_jsonObject.put("response",response_jsonObject.toString());
                        System.out.println("Response : "+response_jsonObject.toString());
                    } else if (request_jsonObject.getString("selection").equals("1")) {
                        System.out.println("Adding a block");
                        //The program will then add a block containing that transaction to the block chain.
                        start = bc.getTime();
                        bc.addBlock(new Block(bc.getChainSize(), bc.getTime(),request_jsonObject.getString("data"),Integer.parseInt(request_jsonObject.getString("difficulty"))));
                        end = bc.getTime();
                        System.out.println("Setting response to Total execution time to add this block was "+(end.getTime() - start.getTime())+" milliseconds");
                        response_jsonObject.put("selection","1");
                        response_jsonObject.put("response","Total execution time to add this block was "+(end.getTime() - start.getTime())+" milliseconds");
                        //...{"selection":1,"response":"Total execution time to add this block was 19 milliseconds"}
                        System.out.println("..."+response_jsonObject.toString());
                    } else if (request_jsonObject.getString("selection").equals("2")) {
                        System.out.println("Verifying entire chain");
                        //call the isChainValid method and display the results.
                        start = bc.getTime();
                        chainvalidate = bc.isChainValid();
                        end = bc.getTime();
                        if (chainvalidate.equals("TRUE")){
                            System.out.println("Chain verification: "+chainvalidate);
                        } else {
                            System.out.println("Chain verification: FALSE");
                            System.out.println(chainvalidate);
                        }
                        System.out.println("Total execution time required to verify the chain was "+(end.getTime() - start.getTime())+" milliseconds");
                        System.out.println("Setting response to Total execution time required to verify the chain was "+(end.getTime() - start.getTime())+" milliseconds");
                        response_jsonObject.put("selection","2");
                        response_jsonObject.put("response","Total execution time required to verify the chain was "+(end.getTime() - start.getTime())+" milliseconds");
                    } else if (request_jsonObject.getString("selection").equals("3")) {
                        System.out.println("View the Blockchain");
                        response_jsonObject.put("selection","3");
                        response_jsonObject.put("response",bc.toString());
                        System.out.println("Setting response to "+bc.toString());
                    } else if (request_jsonObject.getString("selection").equals("4")) {
                        System.out.println("Corrupt the Blockchain");
                        response_jsonObject.put("selection","4");
                        bc.getBlock(Integer.parseInt(request_jsonObject.getString("id"))).setData(request_jsonObject.getString("data"));
                        System.out.println("Block "+request_jsonObject.getString("id")+" now holds "+request_jsonObject.getString("data"));
                        System.out.println("Setting response to"+"Block "+request_jsonObject.getString("id")+" now holds "+request_jsonObject.getString("data"));
                        response_jsonObject.put("response","Block "+request_jsonObject.getString("id")+" now holds "+request_jsonObject.getString("data"));
                    } else if (request_jsonObject.getString("selection").equals("5")) {
                        start = bc.getTime();
                        chainvalidate = bc.isChainValid();
                        if (!chainvalidate.equals("TRUE")){
                            bc.repairChain();
                        }
                        end = bc.getTime();
                        System.out.println("Repairing the entire chain");
                        System.out.println("Setting response to Total execution time required to repair the chain was "+(end.getTime() - start.getTime())+" milliseconds");
                        response_jsonObject.put("selection","5");
                        response_jsonObject.put("response","Total execution time required to repair the chain was "+(end.getTime() - start.getTime())+" milliseconds");
                    } else if (request_jsonObject.getString("selection").equals("6")) {
                       break;
                    }
                    //flush output
                    out.println(response_jsonObject.toString());
                    out.flush();
                }
            }
            // Handle exceptions
        } catch (Exception e) {
            System.out.println("IO Exception:" + e.getMessage());
            // If quitting (typically by you sending quit signal) clean up sockets
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                // ignore exception on close
            }
        }
    }
    //blockchain methods
    public void BlockChain(){
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
}
