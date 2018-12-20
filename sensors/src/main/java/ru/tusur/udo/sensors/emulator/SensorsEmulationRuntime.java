package ru.tusur.udo.sensors.emulator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ru.tusur.udo.sensors.core.Sensor;
import ru.tusur.udo.sensors.core.SensorsRuntime;

@Component
public class SensorsEmulationRuntime extends Thread
	implements SensorsRuntime {
	private static Logger log = LoggerFactory.getLogger(SensorsEmulationRuntime.class);
	
	@Value("${runtime.interval}")
	private int runtimeInterval;
	
	@Autowired
	private Map<String, FakeSensor> fakeSensors;
	
	private List<Sensor> sensors;
	
	public void run() {
		while(true) {
			this.emulate();
			try {
				sleep(this.runtimeInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private void emulate() {
		this.fakeSensors.forEach((str, value) -> {
			value.emulate();
		});
	}


	@Override
	public List<Sensor> getSensors() {
		
		return this.fakeSensors
			.values()
			.stream()
			.map(fakeSensor ->	fakeSensor.toPureSensor())
			.collect(Collectors.toList());
	}
	
	
}
