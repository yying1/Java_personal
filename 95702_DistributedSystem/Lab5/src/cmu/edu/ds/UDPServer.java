package cmu.edu.ds;

import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;

/*
Based on Coulouris UDP socket code
 */
public class UDPServer {
    private DatagramSocket socket = null;
    private InetAddress inetAddress = null;
    private int port;
    public static int g = 5;
    public static final BigInteger b = new BigInteger(2046, new Random());
    public static String p_l = "29455831888140518076474747925200735831996087523515089351305710049596033526238163973239362438299187714861164059458306537966923189121483309380193812391176324371821404328306009372066904964918195671218905191626038217661724017471173451035247796271257458369077948625384652200912648231914498423025647630580939224343513672606007162748159635064224151355895492579269319645649832605784649395525556834728089381127209558678357734944513106656109663590831330308952641905250879634739131347332611006943303916994576338027395880915575015414772552163574891795233906609342414029668068533356545578107870365635398276428848740477292742280559";
    public static BigInteger p = new BigInteger(p_l);
    //public static int b = 8;
    //public static int p = 23;

    public static void main(String[] args) {
        UDPServer udpServer = new UDPServer();
        udpServer.init(7272);
        BigInteger value = udpServer.receive();
        System.out.println("Bob received: " + value);
        String message = String.valueOf(BigInteger.valueOf(g).modPow(b,p));
        //System.out.println(b);
        udpServer.send(message);
        BigInteger shared_secret = value.modPow(b,p);
        System.out.println("Shared secrete: " + shared_secret);
        //System.out.println(b);
        udpServer.close();
    }

    private void init(int portnumber) {
        try {
            socket = new DatagramSocket(portnumber);
            System.out.println("Server socket created");
        } catch (SocketException e) {
            System.out.println("Socket error " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error " + e.getMessage());
        }
    }

    private void send(String message) {
        byte[] buffer = new byte[100000];
        buffer = message.getBytes();
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length, inetAddress, port);
        try {
            socket.send(reply);
        } catch (SocketException e) {
            System.out.println("Socket error " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error " + e.getMessage());
        }

    }

    private BigInteger receive() {
        byte[] buffer = new byte[100000];
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);

        try {
            socket.receive(request);
            inetAddress = request.getAddress();
            port = request.getPort();
        } catch (SocketException e) {
            System.out.println("Socket error " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error " + e.getMessage());
        }
        String result = new String(request.getData(), 0, request.getLength());
        return new BigInteger(result);
    }

    private void close() {
        if (socket != null) socket.close();
    }
}
