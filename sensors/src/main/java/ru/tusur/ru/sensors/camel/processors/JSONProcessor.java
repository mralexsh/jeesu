package ru.tusur.ru.sensors.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

@Component
public class JSONProcessor implements Processor{
	
	 private static final Logger logger = LogManager.getLogger(JSONProcessor.class);
	//@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		logger.error("test error");
		exchange.getIn().setBody("JSON");
		
	}

}
