package edu.cmu.andrew.yying2;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
//FindThisNonce,4,19,Pink,Orange,002fdb16086d97e03613fa0caa87b280eca956216e61a35400408bdd3a449e45

    public static void main(String[] args) throws NoSuchAlgorithmException {
        int nonce = 0;
        MessageDigest input_m = MessageDigest.getInstance("SHA-256");
        String original = "4,19,Pink,Orange,002fdb16086d97e03613fa0caa87b280eca956216e61a35400408bdd3a449e45";
        String input = nonce +","+original;
        byte[] digest = input_m.digest(input.getBytes(StandardCharsets.UTF_8));
        String output = String.format("%064x", new BigInteger(1, digest));
        while(!output.startsWith("00")){
            nonce++;
            input = nonce +","+original;
            digest = input_m.digest(input.getBytes(StandardCharsets.UTF_8));
            output = String.format("%064x", new BigInteger(1, digest));
        }

        System.out.println(output+"\n"+input);
    }

/**
 Answers:
 005cb84ff9568e9c565ddae80498166a7ed7ead8373926125e06f6e1504afb0c
 535,4,19,Pink,Orange,002fdb16086d97e03613fa0caa87b280eca956216e61a35400408bdd3a449e45
 **/


}
