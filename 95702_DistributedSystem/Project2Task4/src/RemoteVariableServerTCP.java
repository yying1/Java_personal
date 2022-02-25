import java.net.*;
import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

public class RemoteVariableServerTCP {

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

                    //break string into different parts
                    String[] requestString_array = requestString.split(" ");
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
}