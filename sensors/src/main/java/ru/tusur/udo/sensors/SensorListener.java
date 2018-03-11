package ru.tusur.udo.sensors;

import java.util.List;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;

public class SensorListener implements SensorObservable {
	@EndpointInject(uri="direct:start")
	ProducerTemplate producer;
	
	public void handle(SensorObserver o) {
		if (o instanceof SensorEmulationRuntime) {
			SensorEmulationRuntime runtime = (SensorEmulationRuntime) o;
			this.sendSensorsToCamel(runtime.getPseudoSensors());
		}
	}
	private void sendSensorsToCamel(List<PseudoSensor> sensors) {
		this.producer.sendBody(sensors);
	}
	/*
	private void log(SensorEmulationRuntime runtime) {
		for(PseudoSensor o: runtime.getPseudoSensors()) {
			System.out.println(o.getId() + " " + o.getValue());			
		}

	}
	*/

}
