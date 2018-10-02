package ru.tusur.udo.wildfly.ejbs;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.BeanInject;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

@Stateless
public class SensorRoutes extends RouteBuilder {
	
	@Inject
	@Named("dataBaseProcessor")
	Processor dataBaseProcessor;
	
	@Inject
	@Named("jsonToObjectProcessor")
	Processor jsonToObjectProcessor;
	
	@Inject
	@Named("sensorAccumulatorProcessor")
	Processor sensorAccumulatorProcessor;
		
	
	
	@Override
	public void configure() throws Exception {
		
		from("seda:sensorendpoint")		
		.process(this.dataBaseProcessor)
		.to("direct:jsonToObject");
		
		from("direct:jsonToObject")		
		.process(this.jsonToObjectProcessor)
		.to("direct:mergeNodes");
					
		from("direct:mergeNodes")
		.process(this.sensorAccumulatorProcessor);	
	}

}