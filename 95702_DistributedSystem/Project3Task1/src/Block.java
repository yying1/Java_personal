//Frank Yue Ying | yying2@andrew.cmu.edu

import org.json.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Arrays;


public class Block extends Object {

    //setting up class variables
     int index;
     Timestamp timestamp;
     String data;
     String previousHash;
     BigInteger nonce;
     int difficulty;

    //initialize block
    public Block(int index, Timestamp timestamp, String data, int difficulty) {
        this.setIndex(index);
        this.setTimestamp(timestamp);
        this.setData(data);
        this.setDifficulty(difficulty);
        this.nonce = BigInteger.valueOf(0);
    }

    public String calculateHash(){
        //concatenate string
        String concatenation = this.getIndex() +","+ this.getTimestamp() + ","+ this.getData()+","+this.getPreviousHash()+","+this.getNonce()+","+this.getDifficulty();
        StringBuffer sb = new StringBuffer();
        //Converting string to character array
        char ch[] = concatenation.toCharArray();
        for(int i = 0; i < ch.length; i++) {
            String hexString = Integer.toHexString(ch[i]);
            sb.append(hexString);
        }
        String result = sb.toString();
        return result;
    }

    public BigInteger getNonce(){return this.nonce;}

    public String proofOfWork() throws NoSuchAlgorithmException {
    // referenced from my lab 1 submission
        MessageDigest input_m = MessageDigest.getInstance("SHA-256");
        String original = this.calculateHash();
        byte[] digest = input_m.digest(original.getBytes(StandardCharsets.UTF_8));
        String output = String.format("%064x", new BigInteger(1, digest));

        //referenced from https://stackoverflow.com/questions/24908736/java-fast-generate-string-with-zeros
        char[] c = new char[this.getDifficulty()];
        Arrays.fill(c, '0');
        String starting_zeros = new String(c);

        while (!output.startsWith(starting_zeros)) {
            this.nonce = this.nonce.add(BigInteger.valueOf(1));
            original = this.calculateHash();
            digest = input_m.digest(original.getBytes(StandardCharsets.UTF_8));
            output = String.format("%064x", new BigInteger(1, digest));
        }
        return output;
    }

    public int getDifficulty(){return this.difficulty;}

    public void setDifficulty(int difficulty){this.difficulty = difficulty;}

    public String toString(){
        // need to get org.json.* library from https://github.com/stleary/JSON-java
        // referenced from https://www.studytonight.com/java-examples/how-to-convert-string-to-json-and-vice-versa
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("index", this.getIndex());
        jsonObject.put("time stamp", this.getTimestamp());
        jsonObject.put("Tx ", this.getData());
        jsonObject.put("PrevHash", this.getPreviousHash());
        jsonObject.put("nonce", this.getNonce());
        jsonObject.put("difficulty", this.getDifficulty());
        return jsonObject.toString();
    }

    public void setPreviousHash(String previousHash){this.previousHash = previousHash;}

    public String getPreviousHash(){return this.previousHash;}

    public int getIndex(){return this.index;}

    public void setIndex(int index){this.index = index;}

    public void setTimestamp (Timestamp timestamp){this.timestamp = timestamp;}

    public Timestamp getTimestamp(){return this.timestamp;}

    public String getData(){return this.data;}

    public void setData(String data){this.data = data;}

    public static void main(String[] args){
    }
}
