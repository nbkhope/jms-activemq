package nbk;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;

    public Sender() {
    }

    public void sendMessage() {

        try {
            System.out.println("Creating factory");
            factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

            connection = factory.createConnection();
            connection.start();
            System.out.println("Made connection start");

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            destination = session.createQueue("SAMPLEQUEUE");

            // Create a MessageProducer from the Session to the Topic or Queue
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Create message
            TextMessage message = session.createTextMessage();
            message.setText("This is a message from the Sender :)");
            System.out.println("Message to be sent...");

            // Send message
            producer.send(message);
            System.out.println("Sent: " + message.getText());

            // Clean up
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
            System.out.println("Some error occurred");
        }
    }
}
