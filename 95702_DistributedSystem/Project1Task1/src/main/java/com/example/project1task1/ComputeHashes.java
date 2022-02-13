package com.example.project1task1;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ComputeHashes", value = "/getComputeHashes")
public class ComputeHashes extends HttpServlet {
    public String message;
    public String method;

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public String encrpt_base64 (String message, String method) throws NoSuchAlgorithmException {
        MessageDigest input_m = MessageDigest.getInstance(method);
        input_m.update(message.getBytes());
        return Base64.getEncoder().encodeToString(input_m.digest());
    }

    public String encrpt_hex (String message, String method) throws NoSuchAlgorithmException {
        MessageDigest input_m = MessageDigest.getInstance(method);
        input_m.update(message.getBytes());
        return bytesToHex(input_m.digest());
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        message = request.getParameter("input");
        method = request.getParameter("hash_function");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>The hash method is: " + method + "</h1>");
        try {
            out.println("<h1>Hash value (hexadecimal): " + encrpt_hex(message,method) + "</h1>");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            out.println("<h1>Hash value (base 64): " + encrpt_base64(message,method) + "</h1>");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}