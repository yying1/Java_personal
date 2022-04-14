package ds.web;

import jakarta.annotation.Resource;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@MessageDriven(mappedName = "jms/myQueue3", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})

@WebServlet(name = "FetchResponse", urlPatterns = {"/fetch"})
public class FetchResponse extends HttpServlet {
    // The following 5 variables should be instance variables:

    // Lookup the ConnectionFactory using resource injection and assign to cf
    @Resource(mappedName = "jms/myConnectionFactory")
    private ConnectionFactory cf;

    // Lookup the Queue using resource injection and assign to q
    @Resource(mappedName = "jms/myQueue3")
    private Queue q;

    private Connection con;
    private Session session;
    private MessageConsumer reader;

    public void init() {

        try {
            // Within the Servlet's init(), initialize the remaining 3 instance variable
            // With the ConnectionFactory, establish a Connection
            con = cf.createConnection();
            // Establish a Session on that Connection
            session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Be sure to start to connection
            con.start();
            // Create a MessageConsumer that will read from q
            reader = session.createConsumer(q);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.init();
        // We are going to do a simple response to the browser.  We are not using MVC in this example
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean empty = true;
        /*
         * You can now try to receive a message from q.  If you give a
         * time argument to receive, it will time out in that many milliseconds.
         * In this way you can receive until there are no more messages to be read
         * at this time from the q.
         */
        TextMessage tm = null;

        try {
            System.out.println("test");
            System.out.println(reader.receive(1000));
            while (((tm = (TextMessage) reader.receive(1000)) != null)) {
                // Do something with tm.getText() to add it to the HTTP response
                out.println("<HTML><BODY><H1>got " + tm.getText() + " to queue</H1>");
                out.println("</BODY></HTML>");
                System.out.println("Fetch got " + tm.getText() + " from jms/myQueue3");
                empty = false;
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

        if (empty == true) {
            out.println("<HTML><BODY><H1>got nothing from myQueue3</H1>");
            out.println("</BODY></HTML>");
        }
        out.close();
    }

}
