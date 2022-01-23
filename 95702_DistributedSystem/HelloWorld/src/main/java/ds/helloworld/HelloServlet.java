package ds.helloworld;

//2022-01-23 | yying2@ | Lab 1

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        MessageDigest input_m = null;
        try {
            input_m = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String input = "Hello World";
        byte[] digest = input_m.digest(input.getBytes(StandardCharsets.UTF_8));
        message = String.format("%064x", new BigInteger(1, digest));
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}