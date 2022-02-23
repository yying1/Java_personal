import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class RemoteVariableClientUDP {
	static int serverPort = 6789;
	//setup socket and request datagram
	static DatagramSocket aSocket = null;
	static String nextLine;

    public static void main(String args[]) throws IOException {

	// Add console output indicating status
	System.out.println("The client is running.");

	//collecting port number
	System.out.println("Please enter server port:");
	Scanner readline = new Scanner(System.in);
	serverPort = Integer.parseInt(readline.nextLine().trim());
	BufferedReader typed = new BufferedReader(new InputStreamReader(System.in));
	while ((nextLine = typed.readLine()) != null) {
		if (nextLine.equals("halt!")) {
			System.out.println("Client side quitting");
			break;
		}else {
			int result = add(Integer.parseInt(nextLine));
			System.out.println("The server returned: " + result);
		}
	}
    }

    public static int add(int i) {
		//create variables to be used as socket and buffer data later

		DatagramPacket reply = null;
		try {
			//setting IP address
			InetAddress aHost = InetAddress.getByName("localhost");

			aSocket = new DatagramSocket();

			//read input with BufferedReader
			byte[] m = String.valueOf(i).getBytes();
			DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);

			//send request
			aSocket.send(request);
			byte[] buffer = new byte[1000];

			//collect echo reply
			reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);
			//exception handling and cleanup
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null) aSocket.close();
		}

		//adding stop mechanism with keyword halt!
		String replyString = new String(reply.getData()).substring(0, reply.getLength());
		return Integer.parseInt(replyString);
	}
}
