package ru.tusur.udo.sensors.emulator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.tusur.udo.sensors.interfaces.Sensor;
import ru.tusur.udo.sensors.interfaces.SensorRuntime;

@Component
public class SensorsEmulationRuntime extends Thread
		implements SensorRuntime {
	
	private static Logger log = 
			LoggerFactory.getLogger(SensorsEmulationRuntime.class);
	
	@Value("${runtime.interval}")
	private int runtimeInterval;
	
	@Autowired
	private Map<String, FakeSensor> fakeSensors;
			
	public List<Sensor> getSensors() {								
						
		
		return fakeSensors
			   .values()
			   .stream()
			   .map(fakeSensor -> fakeSensor.toPureSensor())
			   .collect(Collectors.toList());
	}
	
	
	public void run() {
	
		while(true) {			
			emulate();
			try {
				sleep(runtimeInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void emulate() {
		fakeSensors.forEach((key, s) -> {
			s.emulate();
			//log.info("Sensor: name=" + s.getName() + " value=" + s.getValue());			
		});	
	}

}
