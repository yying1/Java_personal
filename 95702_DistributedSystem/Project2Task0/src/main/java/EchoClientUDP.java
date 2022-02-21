import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoClientUDP{
    public static void main(String args[]){ 
	// args give message contents and server hostname

	// Add console output indicating status
	System.out.println("The client is running.");

	//create variables to be used as socket and buffer data later
	DatagramSocket aSocket = null;
	try {
		//collecting port number and IP address
		InetAddress aHost = InetAddress.getByName("localhost");
		System.out.println("Enter server side port number:");
		Scanner readline = new Scanner(System.in);
		int serverPort = 6789;
		serverPort = Integer.parseInt(readline.nextLine().trim());

		//setup socket and request datagram
		aSocket = new DatagramSocket();
		String nextLine;

		//read input with BufferedReader
		BufferedReader typed = new BufferedReader(new InputStreamReader(System.in));
		while ((nextLine = typed.readLine()) != null) {
		  byte [] m = nextLine.getBytes();
		  DatagramPacket request = new DatagramPacket(m,  m.length, aHost, serverPort);

  		//send request
  		aSocket.send(request);
  		byte[] buffer = new byte[1000];

  		//collect echo reply
  		DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
  		aSocket.receive(reply);

  		//adding stop mechanism with keyword halt!
  		String replyString = new String(reply.getData()).substring(0,reply.getLength());
		  if (replyString.equals("halt!")) {
			  System.out.println("Client side quitting");
			  return;
		  } else {
			  System.out.println("Reply: " + new String(reply.getData()));
		  }
    }

    //exception handling and cleanup
	}catch (SocketException e) {System.out.println("Socket: " + e.getMessage());
	}catch (IOException e){System.out.println("IO: " + e.getMessage());
	}finally {if(aSocket != null) aSocket.close();}
    }
}
