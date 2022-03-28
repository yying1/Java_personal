package com.cmu.edu.part1;

import org.json.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class API {
    String API_base = "https://fantasy.premierleague.com/api/";

    public JSONObject  get_data(String search_string){
        search_string = "bootstrap-static/";
        //search_string = "bootstrap-static/";
        JSONObject FPL_jsonObject;
        try {
        //Referenced from https://dzone.com/articles/how-to-parse-json-data-from-a-rest-api-using-simpl
            URL FPL_url = new URL(API_base+search_string);
            HttpURLConnection FPL_conn = (HttpURLConnection)FPL_url.openConnection();
            FPL_conn.setRequestMethod("GET");
            FPL_conn.connect();
            if(FPL_conn.getResponseCode() != 200)
                throw new RuntimeException("Error: "+FPL_conn.getResponseCode());
            else
            {
                BufferedReader API_in = new BufferedReader(new InputStreamReader(FPL_conn.getInputStream()));

                String API_String ="";
                String inputLine;
                while((inputLine = API_in.readLine()) != null){
                    API_String= API_String + inputLine;
                }
                FPL_jsonObject = new JSONObject(API_String);
            }
            return FPL_jsonObject;
        } catch (Exception e) {
            System.out.print("Get API data error: "+e);
            return null;
        }
    }

    public Hashtable<Integer, String> get_players (JSONObject FPLData){
        Hashtable<Integer, String> player_list = new Hashtable<Integer, String>();
        int element_id = 0;
        JSONArray elements = FPLData.getJSONArray("elements");
        System.out.println(elements.length());
        for (Object element: elements) {
            element_id++;
            String player_name = ((JSONObject) element).getString("web_name");
            player_list.put(element_id,player_name);
        }
        return player_list;
    }

    public static void main(String args[]){
        API FPL_API = new API();
        JSONObject FPLdoc = FPL_API.get_data("");
        Hashtable<Integer, String> players = FPL_API.get_players(FPLdoc);
        System.out.println(players.size());
    }


}
