import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class VerifyingServerTCP {

    //set up static parameter
    public static Hashtable<String, Integer> sum_table = new Hashtable<String, Integer>();
    public static Socket clientSocket = null;

    // Create a new server socket
    static ServerSocket listenSocket;

    public static void main(String args[]) {
        try {
            int serverPort = 6789; // the server port we are using
            listenSocket = new ServerSocket(serverPort);
            listenSocket.setSoTimeout(0);
            clientSocket = listenSocket.accept();
            //clientSocket.setKeepAlive(true);
            Scanner in;
            // Set up "in" to read from the client socket
            in = new Scanner(clientSocket.getInputStream());
            PrintWriter out;
            // Set up "out" to write to the client socket
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));

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

                    //Verify both signature and ID
                    String message = decrypt_verify(requestString);
                    if (message.equals("Error in request")){
                        System.out.println("Error in request");
                        out.println(message);
                        out.flush();
                    } else {
                        //break string into different parts
                        String[] requestString_array = message.split(" ");
                        String ID = requestString_array[1];
                        int option = Integer.parseInt(requestString_array[0]);
                        int value = Integer.parseInt(requestString_array[2]);
                        String operation = "Add";
                        if (option == 2) {
                            operation = "Subtract";
                        } else if (option == 3) {
                            operation = "Get";
                        }
                        //do calculation
                        perform(option, ID, value);
                        System.out.println("ID: " + ID + ", " + "Operation: " + operation + ", Replied: " + sum_table.get(ID));
                        out.println(sum_table.get(ID));
                        out.flush();
                    }
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
    //add method for separation of concern
    public static void perform(int option, String ID, int i) {
        if (!sum_table.containsKey(ID)){
            sum_table.put(ID, 0);
        }
        if (option == 1){
            sum_table.put(ID,sum_table.get(ID)+i);
        }else if (option == 2){
            sum_table.put(ID,sum_table.get(ID)-i);
        }
    }

    public static String decrypt_verify(String m){
        //setup check points
        boolean signature_match = false;
        boolean ID_match = false;

        //collecting n and e from the public channel
        BigInteger p_n; // n is the modulus for both the private and public keys
        BigInteger p_e; // e is the exponent of the public key
        //System.out.println("start");
        p_n = new BigInteger(m.split(",")[0].split(" ")[1]);
        p_e = new BigInteger(m.split(",")[0].split(" ")[0]);
        System.out.println("Client public key is ("+p_e+","+p_n+")");
        //referenced from ShortMessageVerify.java in project 2 for signature verification
        BigInteger encrypted_m = new BigInteger(m.split(",")[2]);
        BigInteger decrypted_m = encrypted_m.modPow(p_e,p_n);

        try{
            // compute the digest of the message with SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            //load message
            byte[] messageToCheckDigest = md.digest(m.split(",")[1].getBytes("UTF-8"));

            // messageToCheckDigest is a full SHA-256 digest
            // take two bytes from SHA-256 and add a zero byte
            byte[] extraByte_message = new byte[messageToCheckDigest.length+1];
            extraByte_message[0] = 0;
            for (int i = 0; i <messageToCheckDigest.length; i++){
                extraByte_message[i+1] = messageToCheckDigest[i];
            }

            // Make it a big int
            BigInteger bigIntegerToCheck = new BigInteger(extraByte_message);

            // complete signature check
            if(bigIntegerToCheck.compareTo(decrypted_m) == 0) {
                signature_match = true;
                System.out.println("Signature verified: "+bigIntegerToCheck.toString());
            }
            // complete ID check
            String public_key = String.valueOf(p_e) + String.valueOf(p_n);
            md.update(public_key.getBytes());
            byte[] rawbytes = md.digest();
            rawbytes = Arrays.copyOfRange(rawbytes, rawbytes.length - 20, rawbytes.length);
            BigInteger Calculated_ID = new BigInteger(rawbytes);

            if (Calculated_ID.toString().equals(m.split(",")[1].split(" ")[0])){
                ID_match = true;
                System.out.println("ID matched: "+ Calculated_ID.toString());
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //continue only if checks are completed
        if (ID_match == true && signature_match == true){
            return m.split(",")[1].split(" ")[3]+" "+m.split(",")[1].split(" ")[0]+" "+m.split(",")[1].split(" ")[4];
        }else{
            return "Error in request";
        }
    }
}