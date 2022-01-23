package edu.cmu.andrew.yying2;

//2022-01-23 | yying2@ | Lab 1: https://github.com/CMU-Heinz-95702/Lab1-InstallationAndRaft

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest input_m = MessageDigest.getInstance("SHA-256");
        String input = "Hello World";
        byte[] digest = input_m.digest(input.getBytes(StandardCharsets.UTF_8));
        String output = String.format("%064x", new BigInteger(1, digest));
        System.out.println(output);
    }
}

//Reference on MessageDigest: https://stackoverflow.com/questions/3103652/hash-string-via-sha-256-in-java


/**
 *Exercise 5
 Explain why the request is not committed on the peers (with dark edges surrounding the term) until after the leader visits twice - once with the request and then a follow up confirmation.

 wait confirmation of entry replication on other servers to the leader

 *Exercise 6
 Explain what happened and why.

  The resumed follower will be forced to match the logs of the leader

 *Exercise 7
 Since the leader cannot receive the confirmed log replication from the majority of severs, changes were not applied to local machines

 **/
