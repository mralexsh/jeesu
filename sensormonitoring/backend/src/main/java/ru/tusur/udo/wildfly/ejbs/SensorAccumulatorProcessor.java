package ru.tusur.udo.wildfly.ejbs;

import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Stateless
@Named("sensorAccumulatorProcessor")
public class SensorAccumulatorProcessor  implements Processor{

	@Inject
	SensorMonitoringService monitoringService;
	
	@Override
	public void process(Exchange msg) throws Exception {
		SensorNodeDTO nodeSensors = msg.getIn().getBody(SensorNodeDTO.class);
		SensorsAccumulator accumulator = this.monitoringService.getSensorAccumulator();
		
		accumulator.getNodes()
		.stream()
		.filter(n -> n.getNode() != nodeSensors.getNode()).collect(Collectors.toList())
		.add(nodeSensors);		
	}

}
