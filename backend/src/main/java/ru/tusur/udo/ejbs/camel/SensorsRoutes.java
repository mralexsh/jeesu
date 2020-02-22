package ru.tusur.udo.ejbs.camel;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import ru.tusur.udo.ejbs.dto.SensorNode;
import ru.tusur.udo.ejbs.dto.SensorsSnapshot;

@Stateless
public class SensorsRoutes extends RouteBuilder {
	
	@Inject
	@Named("sensorsAggregationStrategy")
	AggregationStrategy sensorsAggregationStrategy;
	
	@Override
	public void configure() throws Exception {

		from("seda:apiController")
		.unmarshal().json(JsonLibrary.Jackson, SensorNode.class)
		.aggregate(simple("true"), sensorsAggregationStrategy)
		.completionInterval(1000)
		.to("direct:frontendHandler");
		
		
		from("direct:frontendHandler")
		.marshal().json(JsonLibrary.Jackson, SensorsSnapshot.class)
		.log("+++++++++++++++++++++++")
		.log("${body}")
		.log("+++++++++++++++++++++++");
		
		
		
	    
		
	}

}