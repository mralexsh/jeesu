package ru.tusur.udo.sensors.core;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class SensorsRoutes extends RouteBuilder {
	
	@Value("${app.server.endpoint}")
	private String appServerEndpoint;


	@Value("${polling.interval}")
	private int pollingInterval;
	
	@Autowired
	private SensorsRuntimeProcessor sensorsRuntimeProcessor;
	
	@Autowired
	private JSONProcessor jsonProcessor;
	
	@Override
	public void configure() throws Exception {
		
		from("timer://timer?period=" + this.pollingInterval)
			.process(this.sensorsRuntimeProcessor)
			.to("direct:json");
		
		from("direct:json")
			.process(this.jsonProcessor)
			.to("direct:sendJson");
		
		from("direct:sendJson")
			.setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http4.HttpMethods.POST))
			.to("http4:" + this.appServerEndpoint);
	}

	
}
