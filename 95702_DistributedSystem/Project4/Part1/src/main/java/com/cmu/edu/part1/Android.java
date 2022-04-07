package com.cmu.edu.part1;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "fpl", value = "/fpl")
public class Android extends HttpServlet{
    API FPLmodel =null;

    @Override
    public void init() {
        FPLmodel = new API();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String request_string = request.getQueryString();

    }

}
