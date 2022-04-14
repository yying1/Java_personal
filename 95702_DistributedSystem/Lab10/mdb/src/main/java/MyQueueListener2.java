import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;

/*
 * MyQueueListener is a Message Listener that will listen to jms/myQueue.  Whenever a message
 * is available in that Queue, the onMessage method will be called with the available message.
 */

// This creates the mapping of this MessageListener to the appropriate Queue
@MessageDriven(mappedName = "jms/myQueue2", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class MyQueueListener2 implements MessageListener {

    /*
     * When a message is available in jms/myQueue, onMessage is called.
     */
    public void onMessage(Message message) {
        try {
            // Since there can be different types of Messages, make sure this is the right type.
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                String tmt = tm.getText();
                System.out.println("MyQueueListener2 received: " + tmt);
            } else {
                System.out.println("I don't handle messages of this type");
            }
        } catch (JMSException e) {
            System.out.println("JMS Exception thrown" + e);
        } catch (Throwable e) {
            System.out.println("Throwable thrown" + e);
        }
    }
}

