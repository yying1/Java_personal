//Frank Yue Ying | yying2@

package com.cmu.edu.part1;

import com.mongodb.client.MongoCollection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.bson.Document;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

@WebServlet(name = "fpl",urlPatterns = {"/fpl"})
public class Android extends HttpServlet {
    API FPLmodel = null;
    db FPLDB = null;
    MongoCollection<Document> db_collection = null;
    Hashtable<String, String> record = new Hashtable<String, String>();

    @Override
    public void init() {
        FPLmodel = new API();
        FPLDB = new db();
        db_collection = FPLDB.setup_db();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String request_string = request.getQueryString();
        System.out.println(request_string);
        Enumeration<String> requests_names = request.getParameterNames();
        Timestamp start = new Timestamp(System.currentTimeMillis());
        int action = 0;
        int player1 = 0;
        int player2 = 0;
        String result = "";

        //Referenced from https://examples.javacodegeeks.com/enterprise-java/servlet/get-all-request-parameters-in-servlet/

        while (requests_names.hasMoreElements()) {
            String paramName = requests_names.nextElement();
            String paramValues = request.getParameter(paramName);
            switch (paramName) {
                case "action":
                    action = Integer.parseInt(paramValues);
                    break;
                case "player1":
                    player1 = Integer.parseInt(paramValues);
                    break;
                case "player2":
                    player2 = Integer.parseInt(paramValues);
                    break;
            }
        }
        JSONObject FPLdoc = FPLmodel.get_data("bootstrap-static/");
        this.record.put("time",start.toString());
        this.record.put("action","search");
        this.record.put("p1",String.valueOf(player1));
        this.record.put("p2","");
        if (action == 1) {
            ArrayList<String> player_overall = FPLmodel.get_player_overall(player1, FPLdoc);
            result = FPLmodel.get_player_overall_toString(player_overall);

        } else if (action == 2) {
            Hashtable<String, ArrayList<Integer>> player_recent = FPLmodel.get_player_recent(player1);
            result = FPLmodel.get_player_recent_toString(player_recent,player1,FPLdoc);
        } else {
            result = FPLmodel.compare_players(player1, player2, FPLdoc);
            this.record.put("action","compare");
            this.record.put("p2",String.valueOf(player2));
        }
        System.out.println(result);
        this.record.put("result",result);
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        out.write(result);
        this.record.put("duration",String.valueOf((int) (new Timestamp(System.currentTimeMillis()).getTime() - start.getTime())/1000));
        out.close();
        this.FPLDB.insert_collection(this.db_collection,this.record);
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        RequestDispatcher view = request.getRequestDispatcher("getResults.jsp");
        view.forward(request, response);

    }


}
