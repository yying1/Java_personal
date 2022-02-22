
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class EavesdropperUDP {

    public static void main(String args[]) {
        //Add console output indicating status
        System.out.println("The Eavesdropper is running.");

        try {
        //create variables to be used as socket and buffer data later
        DatagramSocket listenSocket = null;
        DatagramSocket aSocket = null;
        byte[] buffer = new byte[1000];
        InetAddress aHost = InetAddress.getByName("localhost");
        //collecting two port number
        System.out.println("Enter listen port number for eavesdropper:");
        Scanner readline = new Scanner(System.in);
        int ListenPort;
        ListenPort = Integer.parseInt(readline.nextLine());
        System.out.println("Enter sever port number for eavesdropper:");
        int serverPort = 6789;
        serverPort = Integer.parseInt(readline.nextLine());
        //setup socket and request datagram for listening to the client
        listenSocket = new DatagramSocket(ListenPort);
        DatagramPacket listenrequest = new DatagramPacket(buffer, buffer.length);

        //setup socket and request to server
        aSocket = new DatagramSocket();
        String listenrequestString = "";

        while (true) {
            // collect request
            listenSocket.receive(listenrequest);

            //convert request data to string with substring
            listenrequestString = new String(listenrequest.getData()).substring(0, listenrequest.getLength());
            System.out.println("From Client: " + listenrequestString);

            //setup request to the actual server
            byte[] m = listenrequestString.getBytes();
            DatagramPacket serverrequest = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(serverrequest);

            //collect echo reply from server
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            String serverreplyString = new String(reply.getData()).substring(0, reply.getLength());
            System.out.println("From Server: " + serverreplyString);

            //send back to client
            reply = new DatagramPacket(reply.getData(),
                    reply.getLength(), listenrequest.getAddress(), listenrequest.getPort());
            listenSocket.send(reply);

        }
        //exception handling and cleanup
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}