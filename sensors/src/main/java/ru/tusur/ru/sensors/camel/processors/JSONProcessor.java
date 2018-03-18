package ru.tusur.ru.sensors.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class JSONProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(exchange.getIn().getBody());
		exchange.getIn().setBody("JSON");
		
	}

}
