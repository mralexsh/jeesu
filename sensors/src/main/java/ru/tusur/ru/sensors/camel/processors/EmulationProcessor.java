package ru.tusur.ru.sensors.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import ru.tusur.udo.sensors.core.SensorEmulationRuntime;

@Component
public class EmulationProcessor implements Processor {

	private SensorEmulationRuntime sensorEmulationRuntime;
	public SensorEmulationRuntime getSensorEmulationRuntime() {
		return sensorEmulationRuntime;
	}
	public void setSensorEmulationRuntime(SensorEmulationRuntime sensorEmulationRuntime) {
		this.sensorEmulationRuntime = sensorEmulationRuntime;
	}
	@Override
	public void process(Exchange exchange) throws Exception {
		
		this.sensorEmulationRuntime.executeEmulation();
		exchange.getIn().setBody(this.sensorEmulationRuntime.getPseudoSensors());

	}
}
