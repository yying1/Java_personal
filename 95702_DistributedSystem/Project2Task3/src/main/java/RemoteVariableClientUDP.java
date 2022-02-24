
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class RemoteVariableClientUDP {
	static int serverPort = 6789;
	//setup socket and request datagram
	static DatagramSocket aSocket = null;
	static String message;
	public static String ID;

    public static void main(String[] args) throws IOException {

		// Add console output indicating status
		System.out.println("The client is running.");

		//collecting port number
		System.out.println("Please enter server port:");
		Scanner readline = new Scanner(System.in);
		serverPort = Integer.parseInt(readline.nextLine().trim());
		while(true) {
			System.out.println("1. Add a value to your sum." + "\r\n" + "2. Subtract a value from your sum.\r\n" + "3. Get your sum.\r\n" + "4. Exit client");
			int option = Integer.valueOf(readline.nextLine());
			if (option == 4){
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
			System.out.println("Enter your ID:");
			ID = readline.nextLine();
			message = option + " " + ID + " " + value;
			int result = request(message);
			System.out.println("The result is " + result);
		}
	}

    public static int request(String i) {
		//create variables to be used as socket and buffer data later

		DatagramPacket reply = null;
		try {
			//setting IP address
			InetAddress aHost = InetAddress.getByName("localhost");

			aSocket = new DatagramSocket();

			//read input with BufferedReader
			byte[] m = i.getBytes();
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
