package com.cmu.edu.part1;

import org.json.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class API {
    String API_base = "https://fantasy.premierleague.com/api/";

    public JSONObject get_data(String search_string){
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
        int element_id;
        JSONArray elements = FPLData.getJSONArray("elements");
        System.out.println(elements.length());
        for (Object element: elements) {
            element_id = ((JSONObject) element).getInt("id");
            String player_name = ((JSONObject) element).getString("web_name");
            player_list.put(element_id,player_name);
        }
        return player_list;
    }

    public void write_players (Hashtable<Integer, String> players){
        //Write palyer hashtable to .txt file for reference
        //Referenced from https://www.geeksforgeeks.org/write-hashmap-to-a-text-file-in-java/
        String outputFilePath = "C:\\Users\\yingy\\Desktop\\Java_personal\\95702_DistributedSystem\\Project4\\Players.txt";
        File file = new File(outputFilePath);
        BufferedWriter bf = null;
        try {
            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file));
            // iterate map entries
            for (Map.Entry<Integer, String> entry :
                    players.entrySet()) {
                // put key and value separated by a colon
                bf.write(entry.getKey() + ":" + entry.getValue());
                // new line
                bf.newLine();
            }
            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> get_player_overall (Integer ID, JSONObject FPLdoc){
        String [] player_metrics = {"now_cost","total_points","selected_by_percent"};
        ArrayList<String> player_data = new ArrayList<>();
        int element_type_id;
        JSONArray allplayers = FPLdoc.getJSONArray("elements");
        for (Object element: allplayers) {
            if (((JSONObject) element).getInt("id") == ID){
                element_type_id = ((JSONObject) element).getInt("element_type");
                player_data.add(((JSONObject) element).getString("web_name"));
                switch (element_type_id) {
                    case 1:
                        player_data.add("Goalkeeper");
                        break;
                    case 2:
                        player_data.add("Defender");
                        break;
                    case 3:
                        player_data.add("Midfielder");
                        break;
                    case 4:
                        player_data.add("Forward");
                        break;
                }
                for (String query: player_metrics){
                    if (query.equals("selected_by_percent")){
                        player_data.add(((JSONObject) element).getString(query));
                    } else {
                        player_data.add(String.valueOf(((JSONObject) element).getInt(query)));
                    }
                }
            }

        }
        return player_data;
    }

    public Hashtable<String, ArrayList<Integer>> get_player_recent (Integer ID){
        JSONObject player_object = this.get_data("element-summary/"+String.valueOf(ID)+"/");
        JSONArray player_history = player_object.getJSONArray("history");
        String [] player_metrics = {"round","minutes","total_points","selected"};
        int data_point;
        Hashtable<String, ArrayList<Integer>> player_last10rounds = new Hashtable<String, ArrayList<Integer>>();
        for (int i = 1; i < (Integer.min(11,player_history.length())); i++){
            for (String pm: player_metrics){
                data_point = player_history.getJSONObject(player_history.length()-i).getInt(pm);
                if (player_last10rounds.get(pm) == null){
                    player_last10rounds.put(pm,new ArrayList<Integer>());
                }
                player_last10rounds.get(pm).add(data_point);
            }
        }
        return player_last10rounds;
    }

    public String compare_players(Integer ID1, Integer ID2, JSONObject FPLdoc){
        String result = "Comparing Two Players \r\n";
        ArrayList<String> player1 = get_player_overall(ID1,FPLdoc);
        ArrayList<String> player2 = get_player_overall(ID2,FPLdoc);
        result = result + String.format("%-12s%15d%15d", "ID", ID1, ID2)+"\n";
        result = result + String.format("%-12s%15s%15s", "Name", player1.get(0), player2.get(0))+"\n";
        result = result + String.format("%-12s%15s%15s", "Position", player1.get(1), player2.get(1))+"\n";
        result = result + String.format("%-12s%15s%15s", "Cost", player1.get(2), player2.get(2))+"\n";
        result = result + String.format("%-12s%15s%15s", "TotalScore", player1.get(3), player2.get(3))+"\n";
        result = result + String.format("%-12s%15s%15s", "selected%", player1.get(4), player2.get(4))+"\n";
        double player1_sc = Double.parseDouble(player1.get(3)) / Integer.parseInt(player1.get(2));
        double player2_sc = Double.parseDouble(player2.get(3)) / Integer.parseInt(player2.get(2));
        result = result + String.format("%-12s%15.3f%15.3s", "Score/Cost", player1_sc, player2_sc)+"\n";
        if (player1_sc>player2_sc){
            result = result + "Select Player "+ID1;
        } else if (player1_sc<player2_sc) {
            result = result + "Select Player "+ID2;
        } else {
            result = result + "Select either";
        }
        return result;
    }


    public static void main(String args[]){
        API FPL_API = new API();
        JSONObject FPLdoc = FPL_API.get_data("bootstrap-static/");
        Hashtable<Integer, String> players = FPL_API.get_players(FPLdoc);
        //FPL_API.write_players(players);
        Hashtable<String, ArrayList<Integer>> player_recent = FPL_API.get_player_recent(138);
        ArrayList<String> player_overall = FPL_API.get_player_overall(138,FPLdoc);
        System.out.println(player_overall.toString());
//        for (Map.Entry<String, ArrayList<Integer>> entry :
//                player_recent.entrySet()) {
//            // put key and value separated by a colon
//            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
//        }
        String result = FPL_API.compare_players(138,137,FPLdoc);
        System.out.println(result);
    }


}
