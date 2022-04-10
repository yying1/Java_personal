//Frank Yue Ying | yying2@
package com.cmu.androidapp;

import org.json.JSONObject;
import java.io.*;
import java.net.*;

public class fpl {
    String servlet_base = "https://tranquil-citadel-78670.herokuapp.com//fpl?";
    //use this servlet_base for testing on local machine
//    String servlet_base = "http://10.0.2.2:8080/Part1_war_exploded/fpl?";

    fpl FPL = null;
    int ID1= 0;
    int ID2= 0;
    boolean overall = true;


    public String connection(String request){

        try {
            //Referenced from https://dzone.com/articles/how-to-parse-json-data-from-a-rest-api-using-simpl
            URL servlet_url = new URL(servlet_base+request);
            HttpURLConnection Servlet_conn = (HttpURLConnection)servlet_url.openConnection();
            Servlet_conn.setRequestMethod("GET");
            Servlet_conn.connect();
            String servlet_String ="";
            if(Servlet_conn.getResponseCode() != 200)
                throw new RuntimeException("Error: "+Servlet_conn.getResponseCode());
            else
            {
                BufferedReader servel_in = new BufferedReader(new InputStreamReader(Servlet_conn.getInputStream()));
                String inputLine;
                while((inputLine = servel_in.readLine()) != null){
                    servlet_String= servlet_String + inputLine;
                }
            }
            JSONObject response_jsonObject = new JSONObject(servlet_String);
            return response_jsonObject.getString("result");
        } catch (Exception e) {
            System.out.print("Get API data error: "+e);
            return "Error";
        }
    }

    public String handle_search(int ID, boolean overall){
        String request= "";
        this.FPL = FPL;
        this.ID1 = ID;
        this.overall = overall;
        if (this.overall == true){
            request = "action=1&player1="+this.ID1;
        } else {
            request = "action=2&player1="+this.ID1;
        }
//        new BackgroundTask(activity).execute();
        return this.connection(request);
    }

    public String handle_compare(int ID1, int ID2){
        String request= "";
        this.FPL = FPL;
        this.ID1 = ID1;
        this.ID2 = ID2;
        request = "player1="+this.ID1+"&player2="+this.ID2;
//        new BackgroundTask(activity).execute();
        return this.connection(request);
    }

    public static void main(String[] args ){
        fpl FPL = new fpl();
        String result = FPL.handle_compare(138,137);
        System.out.println(result);
    }
}
