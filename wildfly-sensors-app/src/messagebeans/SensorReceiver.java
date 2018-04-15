package messagebeans;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;


/**
 * Message-Driven Bean implementation class for: SensorReceiver
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "/jms/queue/SensorAccumulatorQueue")
		})
public class SensorReceiver implements MessageListener {
	 private static final Logger LOGGER = Logger.getLogger(SensorReceiver.class.toString());
	 //@EndpointInject(uri = "direct:start")
	// private ProducerTemplate producerTemplate;
	
	 public SensorReceiver() {
        // TODO Auto-generated constructor stub
    }
	
	public void onMessage(Message message) {
    	  TextMessage msg = null;
          try {
              if (message instanceof TextMessage) {
                  msg = (TextMessage) message;
                  LOGGER.info("Received Message from queue: " + msg.getText());
               //   this.producerTemplate.sendBody(msg);
              } else {
                  LOGGER.warning("Message of wrong type: " + message.getClass().getName());
              }
          } catch (JMSException e) {
              throw new RuntimeException(e);
          }
        
    }

}
