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

//@MessageDriven(mappedName = "jms/myQueue3", activationConfig = {
//        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
//        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
//})

@WebServlet(name = "FetchResponse", urlPatterns = {"/fetch"})
public class FetchResponse extends HttpServlet /*implements MessageListener */{
    // The following 5 variables should be instance variables:

    // Lookup the ConnectionFactory using resource injection and assign to cf
    @Resource(mappedName = "jms/myConnectionFactory")
    private ConnectionFactory cf;

    // Lookup the Queue using resource injection and assign to q
    @Resource(mappedName = "jms/myQueue3")
    private Queue q;

    private static Connection con;
    private static Session session;
    private static MessageConsumer reader;
//    private static String tmt = "as";

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
            System.out.println(q.getQueueName());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // We are going to do a simple response to the browser.  We are not using MVC in this example
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("test do get");
//        if (!(tmt == null)){
//            try{
//                con = cf.createConnection();
//                // Establish a Session on that Connection
//                session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
//                // Be sure to start to connection
//                con.start();
//                // Create a MessageConsumer that will read from q
//                reader = session.createConsumer(q);
//
//
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
            /*
             * You can now try to receive a message from q.  If you give a
             * time argument to receive, it will time out in that many milliseconds.
             * In this way you can receive until there are no more messages to be read
             * at this time from the q.
             */
            TextMessage tm = null;
            try {
//                System.out.println(reader.receive(1000));
                while (((tm = (TextMessage) reader.receive(1000)) != null)) {
                    // Do something with tm.getText() to add it to the HTTP response
                    out.println("<HTML><BODY><H1>got " + tm.getText() + " to queue</H1>");
//                    out.println("<HTML><BODY><H1>previous " + tmt + " to queue</H1>");
                    out.println("</BODY></HTML>");
                    System.out.println("Fetch got " + tm.getText() + " from jms/myQueue3");
                }
                if (tm == null){
                    out.println("<HTML><BODY><H1>got nothing from myQueue3</H1>");
                    out.println("</BODY></HTML>");
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
//        } else {
//                out.println("<HTML><BODY><H1>got nothing from myQueue3</H1>");
//                out.println("</BODY></HTML>");
//        }
        out.close();
//        try {
//            con.close();
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
    }

//    @Override
//    public void onMessage(Message message) {
//        try {
//            // Since there can be different types of Messages, make sure this is the right type.
//            if (message instanceof TextMessage) {
//                TextMessage tm = (TextMessage) message;
//                tmt = tmt+"\n"+tm.getText();
//                System.out.println("onmessage");
//                System.out.println(tmt);
//            } else {
//                System.out.println("I don't handle messages of this type");
//            }
//        } catch (JMSException e) {
//            System.out.println("JMS Exception thrown" + e);
//        } catch (Throwable e) {
//            System.out.println("Throwable thrown" + e);
//        }
//    }
}
