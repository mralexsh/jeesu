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
	Processor dataBaseProcessor;
		
	
	
	@Override
	public void configure() throws Exception {
		from("timer://timer?period=1000")		
		.process(this.dataBaseProcessor)
		.bean(SensorsAccumulator.class);	
	}

}