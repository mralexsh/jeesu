package ru.tusur.udo.ejbs.camel;

import javax.ejb.Stateless;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

@Stateless
public class SensorsCamelContext extends DefaultCamelContext {

	private ProducerTemplate apiControllerTemplate;
	
	public SensorsCamelContext() {
		apiControllerTemplate = createProducerTemplate();	
	}

	public ProducerTemplate getApiControllerTemplate() {
		return apiControllerTemplate;
	}
	
	
}
