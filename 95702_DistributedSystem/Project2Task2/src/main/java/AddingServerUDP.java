import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class AddingServerUDP {
	static int sum = 0;
	public static void main(String args[]){
	//Add console output indicating status
	System.out.println("Server started.");

	//create variables to be used as socket and buffer data later
	DatagramSocket aSocket = null;
	byte[] buffer = new byte[1000];

	try{
		//setting port number
		int serverPort = 6789;

		//setup socket and request datagram
		aSocket = new DatagramSocket(serverPort);
		DatagramPacket request = new DatagramPacket(buffer, buffer.length);

		//looping to receive requests
 		while(true){
 			// collect request
			aSocket.receive(request);

			//convert request data to string with substring
			String requestString = new String(request.getData()).substring(0,request.getLength());

			//do calculation
			int i = 0;
			if (!requestString.equals("halt!")) {
				i = Integer.parseInt(requestString);
				System.out.println("Adding: "+requestString+" to "+String.valueOf(sum));
				perform(i);
			}

			//adding stop mechanism with keyword halt!
			if (requestString.equals("halt!")) {
				//echo back request message
				DatagramPacket reply = new DatagramPacket(request.getData(),
						request.getLength(), request.getAddress(), request.getPort());
				aSocket.send(reply);
			} else {
				//echo new value
					//conversion from int to byte array referenced from https://stackoverflow.com/questions/2183240/java-integer-to-byte-array
				buffer = String.valueOf(sum).getBytes();
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
				aSocket.send(reply);
				System.out.println("Returning sum of "+sum+" to client");
			}
		}
	//exception handling and cleanup
	}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
	}catch (IOException e) {System.out.println("IO: " + e.getMessage());
	}finally {if(aSocket != null) aSocket.close();}
	}

	//add method for separation of concern
	public static void perform(int i) {
		sum = sum+i;
	}
}


