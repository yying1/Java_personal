//Frank Yue Ying | yying2@andrew.cmu.edu

import java.math.BigInteger;
import java.sql.Timestamp;


public class Block extends java.lang.Object {

    static int index;
    static Timestamp timestamp;
    static String data;
    static String previousHash;
    BigInteger nonce;
    static int difficulty;

    public Block(int index, Timestamp timestamp, String data, int difficulty){
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.difficulty = difficulty;

    }

    public String calculateHash(){

        return null;
    }

    public BigInteger getNonce(){

        return null;
    }

    public String proofOfWork(){

        return null;
    }

    public int getDifficulty(){


        return 0;
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }

    public String toString(){
        return this.data;
    }

    public void setPreviousHash(String previousHash){
        this.previousHash = previousHash;
    }

    public String getPreviousHash(){
        return this.previousHash;
    }

    public int getIndex(){
        return this.index;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public void setTimestamp (Timestamp timestamp){
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp(){
        return this.timestamp;
    }

    public String getData(){
        return this.data;
    }

    public void setData(String data){
        this.data = data;
    }

    public static void main(String[] args){

    }

}
