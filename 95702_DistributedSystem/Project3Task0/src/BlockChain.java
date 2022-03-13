//Frank Yue Ying | yying2@andrew.cmu.edu

import java.sql.Timestamp;
import java.util.ArrayList;

public class BlockChain extends java.lang.Object {

    public static ArrayList<Block> blocks;
    public static byte[] chain_hash;
    public int hashespersecond;

    public BlockChain(){

    }

    public String getChainHash(){
        return String.valueOf(chain_hash);
    }

    public Timestamp getTime(){
        return new Timestamp(System.currentTimeMillis());
    }

    public Block getLatestBlock(){
        return this.blocks.get(-1);
    }

    public int getChainSize(){
        return blocks.size();
    }

    public void computeHashesPerSecond(){

    }

    public int getHashesPerSecond(){
        return ;
    }

    public void addBlock(Block newblock){


    }

    public String toString(){
        return blocks.toString();
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

    public double getTotalExpetedHashes(){

    }

    public String isChainValid(){

    }

    public void repairChain(){

    }

    public static void main(String[] args){

    }

}
