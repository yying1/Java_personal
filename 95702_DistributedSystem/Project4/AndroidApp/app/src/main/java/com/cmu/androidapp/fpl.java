//Frank Yue Ying | yying2@
package com.cmu.androidapp;

import android.app.Activity;
import android.os.StrictMode;

import java.io.*;
import java.net.*;
import java.util.*;

public class fpl {
    String servlet_base = "https://evening-castle-99753.herokuapp.com/fpl?";
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
                    servlet_String= servlet_String +"\r\n"+ inputLine;
                }
            }
            return servlet_String;
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

    private class BackgroundTask {

        private Activity activity; // The UI thread

        public BackgroundTask(Activity activity) {
            this.activity = activity;
        }

        private void startBackground() {
            new Thread(new Runnable() {
                public void run() {

                    doInBackground();
                    // This is magic: activity should be set to MainActivity.this
                    //    then this method uses the UI thread
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            onPostExecute();
                        }
                    });
                }
            }).start();
        }

        private void execute() {
            // There could be more setup here, which is why
            //    startBackground is not called directly
            startBackground();
        }
        private void doInBackground() {
        }

        // onPostExecute( ) will run on the UI thread after the background
        //    thread completes.
        // Implement this method to suit your needs
        public void onPostExecute() {

        }
    }

    public static void main(String[] args ){
        fpl FPL = new fpl();
//        String result = FPL.handle_search(138,false);
//        System.out.println(result);
    }
}
