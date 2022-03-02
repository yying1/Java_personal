import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class BumperClient {
    public static void main(String args[]) throws Exception {
        BigInteger ctr = BigInteger.valueOf(0);
        BigInteger n = BigInteger.valueOf(10000);
        Bumper b  = (Bumper) Naming.lookup("//localhost/bumper");
        System.out.println("Found Bumper.");
        long start = System.currentTimeMillis();
        while(!ctr.equals(n)) {
            try {
                ctr = ctr.add(new BigInteger("1"));
                boolean bump = b.bump();
            }
            catch(RemoteException e) {
                System.out.println("allComments: " + e.getMessage());
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println("Value of the BigInteger held by server is "+b.get().toString());
        System.out.println("# of seconds for this operations is "+String.format("%.2f",(float)(stop-start)/1000));
    }
}