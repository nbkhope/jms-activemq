package nbk;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;

    public Receiver() {

    }

    public void receiveMessage() {
        try {
            System.out.println("Trying...");
            factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
            System.out.println("Factory set");
            connection = factory.createConnection();
            connection.start();

            System.out.println("Creating session");
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("SAMPLEQUEUE");
            consumer = session.createConsumer(destination);
            
            System.out.println("Setting message");
            Message message = consumer.receive();

            // System.out.println("Message is ...");
            // System.out.println(message);
            if (message instanceof TextMessage) {
                TextMessage text = (TextMessage) message;
                System.out.println("Message is : " + text.getText());
            }
            System.out.println("End");
            connection.close();
        } catch (JMSException e) {
            System.out.println("Receiver failed");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Some other error occurred!");
        }
    }
}
