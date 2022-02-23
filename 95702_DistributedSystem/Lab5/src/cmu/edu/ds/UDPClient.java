package cmu.edu.ds;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;
import java.util.Scanner;
import java.math.BigInteger;

/*
Based on Coulouris UDP socket code
 */
public class UDPClient {
    private DatagramSocket socket = null;
    private InetAddress host = null;
    private int port;
    public static int g = 5;
    public static final BigInteger a = new BigInteger(2046, new Random());
    public static String p_l = "29455831888140518076474747925200735831996087523515089351305710049596033526238163973239362438299187714861164059458306537966923189121483309380193812391176324371821404328306009372066904964918195671218905191626038217661724017471173451035247796271257458369077948625384652200912648231914498423025647630580939224343513672606007162748159635064224151355895492579269319645649832605784649395525556834728089381127209558678357734944513106656109663590831330308952641905250879634739131347332611006943303916994576338027395880915575015414772552163574891795233906609342414029668068533356545578107870365635398276428848740477292742280559";
    public static BigInteger p = new BigInteger(p_l);
    //public static int a = 2;
    //public static int p = 23;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        BigInteger value;
        //System.out.print("Enter an integer: ");
        //int value = Integer.parseInt(keyboard.nextLine());
        //System.out.println("value = " + value);
        UDPClient udpClient = new UDPClient();
        udpClient.init("localhost", 7272);
        //udpClient.send(Integer.toString(value));
        //System.out.println(BigInteger.valueOf(g).modPow(a,p));
        udpClient.send(String.valueOf(BigInteger.valueOf(g).modPow(a,p)));
        //System.out.println(a);
        value = udpClient.receive();
        System.out.println("Bob responded: " + value);
        BigInteger shared_secret = value.modPow(a,p);
        System.out.println("Shared secrete: " + shared_secret);
        udpClient.close();
        //System.out.println(a);
    }

    private void init(String hostname, int portNumber) {
        try {
            host = InetAddress.getByName(hostname);
            port = portNumber;
            socket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println("Socket error " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error " + e.getMessage());
        }
    }

    private void send(String message) {
        byte[] m = message.getBytes();
        DatagramPacket packet = new DatagramPacket(m, m.length, host, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            System.out.println("IO error " + e.getMessage());
        }
    }

    private BigInteger receive() {
        byte[] answer = new byte[10000];
        DatagramPacket reply = new DatagramPacket(answer, answer.length);
        try {
            socket.receive(reply);
        } catch (IOException e) {
            System.out.println("IO error " + e.getMessage());
        }
        String result = new String(reply.getData(), 0, reply.getLength());
        return new BigInteger(result);

    }

    private void close() {
        if (socket != null) socket.close();
    }
}
