package ru.tusur.udo.ejbs.camel;

import javax.ejb.Stateless;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

@Stateless
public class SensorsCamelContext extends DefaultCamelContext {

	private ProducerTemplate apiControllerTemplate;
	private ConsumerTemplate wsConsumerTemplate;
	
	
	public SensorsCamelContext() {
		apiControllerTemplate = createProducerTemplate();
		wsConsumerTemplate = createConsumerTemplate();
	}

	public ProducerTemplate getApiControllerTemplate() {
		return apiControllerTemplate;
	}

	public ConsumerTemplate getWsConsumerTemplate() {
		return wsConsumerTemplate;
	}
	
	
}
