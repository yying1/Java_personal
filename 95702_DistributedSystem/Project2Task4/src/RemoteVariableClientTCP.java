
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class RemoteVariableClientTCP {

    //setting static parameters
    static int serverPort = 6789;
    static String message;
    public static String ID;
    static Socket clientSocket = null;
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String args[]) throws IOException {
        // Add console output indicating status
        System.out.println("The client is running.");

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

            //collect ID
            System.out.println("Enter your ID:");
            ID = readline.nextLine();

            //send information and wait for reply
            message = option + " " + ID + " " + value;
            int result = request(message);
            System.out.println("The result is " + result);
        }
        //readline.close();
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
}