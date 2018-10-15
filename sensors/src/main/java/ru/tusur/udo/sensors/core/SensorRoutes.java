package ru.tusur.udo.sensors.core;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class SensorRoutes extends RouteBuilder {
	
	@Value("${sensor.polling.interval}")
	private String sensorPollingInterval;
	
	@Value("${app.server.endpoint}")
	private String appServerEndpoint;
	
	@Autowired
	private JSONProcessor jsonProcessor;

	@Autowired
	private SensorRuntimeProcessor sensorRuntimeProcessor;

	@Override
	public void configure() throws Exception {

		from("timer://timer?period=" + this.sensorPollingInterval)
			.process(this.sensorRuntimeProcessor)
			.to("direct:json");			
								
		
		from("direct:json")
			.process(this.jsonProcessor)
			.to("direct:testJSON");
		
		
		from("direct:testJSON")
			.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					log.info(exchange.getIn().getBody().toString());
					
				}
			})
			.setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http4.HttpMethods.POST))
			.to("http4:" + this.appServerEndpoint);
		
		
	}
}
