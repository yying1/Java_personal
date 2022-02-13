package com.example.project1task3;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "CollectAnswer", urlPatterns = {"/submit", "/getResults"})
public class CollectAnswer extends HttpServlet {
    public ArrayList<String> selections  = new ArrayList<>();

    public void main(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(selections,request,response);
    }


    public void doPost(ArrayList<String> selections,HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String option_A = "false";
        String option_B = "false";
        String option_C = "false";
        String option_D = "false";
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        for (String s:selections){
            if (s != null) {
                if (s.equals("A")) {
                    option_A = "true";
                    countA++;
                } else if (s.equals("B")) {
                    option_B = "true";
                    countB++;
                } else if (s.equals("C")) {
                    option_C = "true";
                    countC++;
                } else if (s.equals("D")) {
                    option_D = "true";
                    countD++;
                }
            }
        }
        System.out.println(countA+countB+countC+countD);
        request.setAttribute("option_A", option_A);
        request.setAttribute("option_B", option_B);
        request.setAttribute("option_C", option_C);
        request.setAttribute("option_D", option_D);
        request.setAttribute("count_A", String.valueOf(countA));
        request.setAttribute("count_B", String.valueOf(countB));
        request.setAttribute("count_C", String.valueOf(countC));
        request.setAttribute("count_D", String.valueOf(countD));
        RequestDispatcher view = request.getRequestDispatcher("getResults.jsp");
        view.forward(request, response);
        selections  = new ArrayList<>();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String urlPattern = request.getServletPath();
        // determine what type of device our user is
        String ua = request.getHeader("User-Agent");

        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }
        if (urlPattern.contains("/submit")){
            //System.out.println("success");
            response.setContentType("text/html");
            String current_option = request.getParameter("option");
            request.setAttribute("selected_option", current_option);
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
            selections.add(current_option);
        } else {
            doPost(selections,request,response);
        }
    }

    public void destroy() {
    }
}