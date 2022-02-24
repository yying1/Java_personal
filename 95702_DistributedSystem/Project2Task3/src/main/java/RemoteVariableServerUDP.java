import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Hashtable;

public class RemoteVariableServerUDP {
	public static Hashtable<String, Integer> sum_table = new Hashtable<String, Integer>();
	public static void main(String args[]) throws IOException {
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

			//break string into different parts
			String[] requestString_array = requestString.split(" ");
			String ID = requestString_array[1];
			int option = Integer.parseInt(requestString_array[0]);
			int value = Integer.parseInt(requestString_array[2]);
			String operation = "Add";
			if (option ==2){
				operation = "Subtract";
			} else if (option ==3){
				operation = "Get";
			}
			//do calculation
			perform(option, ID,value);
			System.out.println("ID: "+ID+", "+"Operation: "+operation+", Replied: "+sum_table.get(ID));

			//echo new value
					//conversion from int to byte array referenced from https://stackoverflow.com/questions/2183240/java-integer-to-byte-array
			buffer = String.valueOf(sum_table.get(ID)).getBytes();
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
			aSocket.send(reply);
			}
	//exception handling and cleanup
	}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
	}catch (IOException e) {System.out.println("IO: " + e.getMessage());
	}finally {if(aSocket != null) aSocket.close();}
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


