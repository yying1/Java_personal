//Frank Yue Ying | yying2@andrew.cmu.edu

import org.json.JSONObject;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class RequestMessage {

    //setting static parameters
    static int serverPort = 6789;
    static Socket clientSocket = null;
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String args[]) throws IOException {
        // Add console output indicating status
        System.out.println("The client is running.");

        //collecting port number
        clientSocket = new Socket("localhost", serverPort);
        Scanner readline = new Scanner(System.in);

        //Setup socket communications for in and out
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));

        //setup request and response json objects
        JSONObject request_jsonObject = new JSONObject();
        JSONObject response_jsonObject;

        //iteration operations
        while (true) {
            //collect options and responses
            System.out.println("0. View basic blockchain status.\n1. Add a transaction to the blockchain.\n2. Verify the blockchain.\n3. View the blockchain.\n4. Corrupt the chain.\n5. Hide the corruption by repairing the chain.\n6. Exit.");
            int selection = Integer.valueOf(readline.nextLine());
            request_jsonObject.put("selection",String.valueOf(selection));
            if (selection == 1) {
                //the program will prompt for and then read the difficulty level for this block.
                System.out.println("Enter difficulty > 0");
                String difficulty = readline.nextLine();

                //It will then prompt for and then read a line of data from the user (representing a transaction).
                System.out.println("Enter transaction");
                String data = readline.nextLine();
                request_jsonObject.put("difficulty",difficulty);
                request_jsonObject.put("data",data);
            } else if (selection == 4) {
                //If the user selects option 4, she wants to corrupt the chain.
                System.out.println("corrupt the Blockchain");
                // Ask her for the block index (0..size-1)
                System.out.println("Enter block ID of block to corrupt");
                String index = readline.nextLine();
                // and ask her for the new data that will be placed in the block.
                System.out.println("Enter new data for block "+index);
                String new_block_data = readline.nextLine();
                request_jsonObject.put("id",index);
                request_jsonObject.put("data",new_block_data);
            } else if (selection == 6){
                break;
            }
            //send information and wait for reply
            String result = request(request_jsonObject.toString());
            response_jsonObject = new JSONObject(result);
            System.out.println(response_jsonObject.getString("response"));
        }
        readline.close();
        out.close();
    }
    public static String request(String i) {
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
        return replyString;
    }


}
