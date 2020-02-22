package ru.tusur.udo.sensors;


import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class SensorsRoutes extends RouteBuilder {

	@Value("${polling.interval}")
	private int pollingInterval;
	
	@Value("${app.server.endpoint}")
	private String appServerEndpoint;
	
	@Autowired
	private SensorsRuntimeProcessor sensorsRuntimeProcessor;
	
	@Autowired
	private EnrichDataProcessor enrichDataProcessor;
	
	@Autowired
	private JsonConvertProcessor jsonConvertorProcessor;
	
	@Override
	public void configure() throws Exception {
			
		from("timer://sensorTimer?period=" + pollingInterval)
			.process(sensorsRuntimeProcessor)			
			.to("direct:enrichDataRoute");	
						
		from("direct:enrichDataRoute")
		.process(enrichDataProcessor)
		.to("direct:jsonRoute");
		
		from("direct:jsonRoute")
		.process(jsonConvertorProcessor)
		.to("direct:sendRoute");

		
		from("direct:sendRoute")
		.setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
		.doTry()
		.log("${body}")
		.to("http4:" + appServerEndpoint)		
		.doCatch(Exception.class)		
		.log(LoggingLevel.ERROR ,"Не согу соединиться с сервером");
		
		
		
		
	}

}
