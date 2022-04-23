package org.myorg;


import java.util.StringTokenizer;

public class test {
    public static void main(String[] args) throws Exception {
        String a= "This is a test";
        a = a.replace("", " ").trim();
        StringTokenizer tokenizer = new StringTokenizer(a);
        while(tokenizer.hasMoreTokens())
        {
            System.out.println(tokenizer.nextToken());
        }

    }
}
