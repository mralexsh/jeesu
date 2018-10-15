package ru.tusur.udo.wildfly.ejbs;

import javax.ejb.Stateless;
import javax.inject.Named;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Stateless
@Named("dataBaseProcessor")
public class DatabaseProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getOut().setBody(exchange.getIn().getBody());
		
	}

}
