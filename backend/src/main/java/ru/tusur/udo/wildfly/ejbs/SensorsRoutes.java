package ru.tusur.udo.wildfly.ejbs;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

@Stateless
public class SensorsRoutes extends RouteBuilder {

	private static final Logger log = 
			Logger.getLogger(SensorsMonitoringService.class.toString());

	@Inject
	@Named("jsonToObjectProcessor")
	Processor jsonToObjectProcessor;

	@Inject
	@Named("sensorsAccumulatorProcessor")
	Processor sensorAccumulatorProcessor;



	@Override
	public void configure() throws Exception {
		
		from("seda:sensorsStartPoint")
				.to("direct:jsonToObject");

		from("direct:jsonToObject")
				.process(this.jsonToObjectProcessor)
				.to("direct:mergeNodes");

		from("direct:mergeNodes")
				.process(this.sensorAccumulatorProcessor);
		
		
	}

}
