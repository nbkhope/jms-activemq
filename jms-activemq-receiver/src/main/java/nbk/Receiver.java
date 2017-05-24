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
    
    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageConsumer consumer;

    public Receiver() {
    }

    public void receiveMessage() {
        try {
            // Create a ConnectionFactory
            factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
            System.out.println("Factory set");

            // Create a Connection
            connection = factory.createConnection();
            connection.start();

            System.out.println("Creating session");
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            destination = session.createQueue("SAMPLEQUEUE");

            // Create a MessageConsumer from the Session to the Topic or Queue
            consumer = session.createConsumer(destination);
            
            System.out.println("Waiting for message...");
            Message message = consumer.receive();

            // System.out.println("Message is ...");
            // System.out.println(message);
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("Message is : " + textMessage.getText());
            }

            // Clean up
            consumer.close();
            session.close();
            connection.close();

            System.out.println("-- End --");
        } catch (JMSException e) {
            System.out.println("Receiver failed");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Some other error occurred!");
            System.out.println(e);
            // e.printStackTrace();
        }
    }
}
