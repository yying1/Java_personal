import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SigningClientTCP {

    //setting static parameters
    static int serverPort = 6789;
    static String message;
    public static BigInteger ID;
    static Socket clientSocket = null;
    static BufferedReader in;
    static PrintWriter out;

    //for hashing, reference from Lab 1
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    //Referenced from RSAExample.java in Project 2
    // Each public and private key consists of an exponent and a modulus
    static BigInteger n; // n is the modulus for both the private and public keys
    static BigInteger e; // e is the exponent of the public key
    static BigInteger d; // d is the exponent of the private key

    public static void main(String args[]) throws IOException {
        // Add console output indicating status
        System.out.println("The client is running.");

        //display keys
        generate_key();
        System.out.println("Client public key is ("+e+","+n+")");
        System.out.println("Client private key is ("+d+","+n+")");
        generate_ID();
        System.out.println("Client ID is "+ID);

        //collecting port number
        System.out.println("Please enter server port:");
        Scanner readline = new Scanner(System.in);
        serverPort = Integer.parseInt(readline.nextLine().trim());
        clientSocket = new Socket("localhost", serverPort);

        //Setup socket communications for in and out
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));

        //iteration operations
        while (true) {
            //collect options and responses
            System.out.println("1. Add a value to your sum." + "\r\n" + "2. Subtract a value from your sum.\r\n" + "3. Get your sum.\r\n" + "4. Exit client");
            int option = Integer.valueOf(readline.nextLine());
            if (option == 4) {
                System.out.println("Client side quitting. The remote variable server is still running.");
                break;
            }
            int value = 0;
            if (option == 1) {
                System.out.println("Enter your value to add:");
            } else if (option == 2) {
                System.out.println("Enter your value to subtract:");
            }
            if (!(option == 3)) {
                value = Integer.valueOf(readline.nextLine());
            }
            message = ID+" "+e+" "+n+" "+option+" "+value;

            //sign the message and put everything together
            String signature = encrypt(message);
            message = e+" "+n+","+message+","+signature;

            //send information and wait for reply
            int result = request(message);
            System.out.println("The result is " + result);
        }
        readline.close();
        out.close();
    }

    public static int request(String i) {
        //send request and collect feedback
        String replyString = "";
        try {
            out.println(i);
            out.flush();
            //System.out.println(i);
            replyString = in.readLine(); // read a line of data from the stream
        } catch (IOException e) {
            System.out.println("IO Exception:" + e.getMessage());
        }
        return Integer.parseInt(replyString);
    }

    public static void generate_key(){
        //Referenced from RSAExample.java in Project document

        /**
         *  RSA Algorithm from CLR
         *
         * 1. Select at random two large prime numbers p and q.
         * 2. Compute n by the equation n = p * q.
         * 3. Compute phi(n)=  (p - 1) * ( q - 1)
         * 4. Select a small odd integer e that is relatively prime to phi(n).
         * 5. Compute d as the multiplicative inverse of e modulo phi(n). A theorem in
         *    number theory asserts that d exists and is uniquely defined.
         * 6. Publish the pair P = (e,n) as the RSA public key.
         * 7. Keep secret the pair S = (d,n) as the RSA secret key.
         * 8. To encrypt a message M compute C = M^e (mod n)
         * 9. To decrypt a message C compute M = C^d (mod n)
         */


        Random rnd = new Random();

        // Step 1: Generate two large random primes.
        // We use 400 bits here, but best practice for security is 2048 bits.
        // Change 400 to 2048, recompile, and run the program again and you will
        // notice it takes much longer to do the math with that many bits.
        BigInteger p = new BigInteger(400, 100, rnd);
        BigInteger q = new BigInteger(400, 100, rnd);

        // Step 2: Compute n by the equation n = p * q.
        n = p.multiply(q);

        // Step 3: Compute phi(n) = (p-1) * (q-1)
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Step 4: Select a small odd integer e that is relatively prime to phi(n).
        // By convention the prime 65537 is used as the public exponent.
        e = new BigInteger("65537");

        // Step 5: Compute d as the multiplicative inverse of e modulo phi(n).
        d = e.modInverse(phi);

//        System.out.println(" e = " + e);  // Step 6: (e,n) is the RSA public key
//        System.out.println(" d = " + d);  // Step 7: (d,n) is the RSA private key
//        System.out.println(" n = " + n);  // Modulus for both keys
//
//        // Encode a simple message. For example the letter 'A' in UTF-8 is 65
//        BigInteger m = new BigInteger("65");
//
//        // Step 8: To encrypt a message M compute C = M^e (mod n)
//        BigInteger c = m.modPow(e, n);
//
//        // Step 9: To decrypt a message C compute M = C^d (mod n)
//        BigInteger clear = c.modPow(d, n);
//        System.out.println("Cypher text = " + c);
//        System.out.println("Clear text = " + clear); // Should be "65"
//
//        // Step 8 (reprise) Encrypt the string 'RSA is way cool.'
//        String s = "RSA is way cool.";
//        m = new BigInteger(s.getBytes()); // m is the original clear text
//        c = m.modPow(e, n);     // Do the encryption, c is the cypher text
//
//        // Step 9 (reprise) Decrypt...
//        clear = c.modPow(d, n); // Decrypt, clear is the resulting clear text
//        String clearStr = new String(clear.toByteArray());  // Decode to a string
//
//        System.out.println("Cypher text = " + c);
//        System.out.println("Clear text = " + clearStr);

    }

    public static void generate_ID(){
    try {
        String public_key = String.valueOf(e) + String.valueOf(n);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(public_key.getBytes());
        byte[] rawbytes = md.digest();
        rawbytes = Arrays.copyOfRange(rawbytes, rawbytes.length - 20, rawbytes.length);
        ID = new BigInteger(rawbytes);
    } catch (NoSuchAlgorithmException e){}
    }

    public static String encrypt(String m){
        //build token for hash
        String all_tokens = m;
        BigInteger hash_result = new BigInteger("0");
        //hash using sha-256, referenced from ShortMessageSign.java in project 2
        try {
            byte[] bytesOfMessage = all_tokens.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] token_bytes = md.digest(bytesOfMessage);
            byte[] messageDigest = new byte[token_bytes.length+1];
            messageDigest[0] = 0;   // most significant set to 0
            for (int i = 0; i <token_bytes.length; i++){
                messageDigest[i+1] = token_bytes[i];
            }
            hash_result = new BigInteger(messageDigest);
        } catch (NoSuchAlgorithmException e){} catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        BigInteger encrypted_m = hash_result.modPow(d, n);
        return encrypted_m.toString();
    }
}