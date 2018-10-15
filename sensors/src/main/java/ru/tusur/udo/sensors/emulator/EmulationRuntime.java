package ru.tusur.udo.sensors.emulator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.tusur.udo.sensors.core.Sensor;
import ru.tusur.udo.sensors.core.SensorRuntime;


public class EmulationRuntime extends Thread implements SensorRuntime {
	private static Logger log = LoggerFactory.getLogger(EmulationRuntime.class);
	private List<Sensor> sensors;
	
	@Value("${runtime.interval}")
	private int runtimeInterval;
		
	public List<Sensor> getSensors() {
		return  this.sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

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
		this.sensors
			.stream()
			.forEach(sensor -> {				
				if (sensor instanceof FakeSensor) {
					FakeSensor s = (FakeSensor) sensor;
					s.emulate();
					//log.info("INTERVAL:" + Integer.toString(this.runtimeInterval));
					//log.info(s.getImei() + " = " + s.getValue());
				}				
			});		
	}	
	
}
