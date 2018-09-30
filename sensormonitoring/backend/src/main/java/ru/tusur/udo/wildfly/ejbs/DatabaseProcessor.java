package ru.tusur.udo.wildfly.ejbs;

import javax.ejb.Stateless;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Stateless
public class DatabaseProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getOut().setBody("TEST MESSAGE TO WEBSOCKET");
		
	}

}
