package org.myorg;


import java.util.StringTokenizer;

public class test {
    public static void main(String[] args) throws Exception {
        String a= "1348656.471\t399538.5342\t32874\t100 BONIFAY ST\tROBBERY\t1/1/90\t160600\t40.40865518\t-79.9760891";
//        a = a.replace("", " ").trim();
//        StringTokenizer tokenizer = new StringTokenizer(a);
//        while(tokenizer.hasMoreTokens())
//        {
//            System.out.println(tokenizer.nextToken());
//        }
        String log = a.split("\\s")[a.split("\\s").length-1];
        String lat = a.split("\\s")[a.split("\\s").length-2];
        System.out.println(log);
        System.out.println(lat);

    }
}
