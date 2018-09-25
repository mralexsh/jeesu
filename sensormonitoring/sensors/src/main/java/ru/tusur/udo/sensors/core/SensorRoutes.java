package ru.tusur.udo.sensors.core;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class SensorRoutes extends RouteBuilder {

	@Autowired
	CollectSensorsProcessor collectSensorProcessor;
	
	@Override
	public void configure() throws Exception {

		from("timer://timer?period=1000")
			.process(this.collectSensorProcessor);
		
		
	}

	

}
