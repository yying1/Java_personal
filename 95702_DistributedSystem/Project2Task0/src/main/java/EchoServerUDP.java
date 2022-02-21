import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class EchoServerUDP{
	public static void main(String args[]){
	//Add console output indicating status
	System.out.println("The server is running.");

	//create variables to be used as socket and buffer data later
	DatagramSocket aSocket = null;
	byte[] buffer = new byte[1000];

	try{
		//collecting port number
		System.out.println("Enter listen port number:");
		Scanner readline = new Scanner(System.in);
		int serverPort = 6789;
		serverPort = Integer.parseInt(readline.nextLine());

		//setup socket and request datagram
		aSocket = new DatagramSocket(serverPort);
		DatagramPacket request = new DatagramPacket(buffer, buffer.length);

		//looping to receive requests
 		while(true){
 			// collect request
			aSocket.receive(request);

			//convert request data to string with substring
			String requestString = new String(request.getData()).substring(0,request.getLength());

			//echo back request message
			DatagramPacket reply = new DatagramPacket(request.getData(),
					request.getLength(), request.getAddress(), request.getPort());
			aSocket.send(reply);

			//adding stop mechanism with keyword halt!
			if (requestString.equals("halt!")) {
				System.out.println("Server side quitting");
				break;
			} else {
				System.out.println("Echoing: "+requestString);
			}
		}
	//exception handling and cleanup
	}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
	}catch (IOException e) {System.out.println("IO: " + e.getMessage());
	}finally {if(aSocket != null) aSocket.close();}
	}
}
