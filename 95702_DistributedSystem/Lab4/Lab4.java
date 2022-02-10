//Frank Yue Ying 2022-02-09 https://github.com/CMU-Heinz-95702/lab4-http-server

import java.net.*;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;

public class Lab4 {

    public static void main(String args[]) {
        Socket clientSocket = null;
        try {
            int serverPort = 7777; // the server port we are using

            // Create a new server socket
            ServerSocket listenSocket = new ServerSocket(serverPort);

            /*
             * Block waiting for a new connection request from a client.
             * When the request is received, "accept" it, and the rest
             * the tcp protocol handshake will then take place, making
             * the socket ready for reading and writing.
             */
            clientSocket = listenSocket.accept();
            // If we get here, then we are now connected to a client.

            // Set up "inFromSocket" to read from the client socket
            Scanner inFromSocket;
            inFromSocket = new Scanner(clientSocket.getInputStream());

            String line = inFromSocket.nextLine();
            inFromSocket.nextLine();
            inFromSocket.reset();
            String file_path = "";
            file_path = line.split(" ")[1].replace("/","");
            boolean indicate_file = false;
            System.out.println(line);

            // Set up "outToSocket" to write to the client socket
            PrintWriter outToSocket;
            outToSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
            outToSocket.flush();
            /*
             * Forever,
             *   read a line from the socket
             *   print it to the console
             *   echo it (i.e. write it) back to the client
             */
            boolean file_exist = true;
            if (file_path != "" && line.toLowerCase(Locale.ROOT).contains("get")){
                indicate_file = true;
                System.out.println("File Path is: /"+file_path);
                File pathfile = new File(file_path);
                file_exist = pathfile.exists();
            } else {
                outToSocket.write("HTTP/1.1 405 Method Not Allowed\n\n");
            }
            while (indicate_file) {
                String data_all = "HTTP/1.1 200 OK\n\n";
                String data = "";
                try {
                    BufferedReader in = new BufferedReader(new FileReader(file_path));
                    if (!file_exist){throw new Exception();};
                    while ((data = in.readLine()) != null) {
                        System.out.println("Echoing: " + data);
                        data_all = data_all+data+ "\n\n";
                    }
                    indicate_file = false;
                    in.close();
                } catch (Exception e) {
                    System.out.println("Exception: HTTP/1.1 404 File Not Found");
                    data_all = "HTTP/1.1 404 File Not Found\n\n";
                    indicate_file = false;
                }
                outToSocket.write(data_all);
                outToSocket.flush();
                outToSocket.close();
                inFromSocket.close();
            }

            // Handle exceptions
        } catch (IOException e) {
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

}
