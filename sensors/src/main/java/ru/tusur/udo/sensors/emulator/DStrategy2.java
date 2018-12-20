package ru.tusur.udo.sensors.emulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.tusur.udo.sensors.App;

public class DStrategy2 implements EmulationStrategy {
	
	private static Logger log = LoggerFactory.getLogger(DStrategy2.class);
	
	private int counter;
	private int value;
	private int tick;
	
	
	public DStrategy2() {
		this.value = 0;
		this.tick = 1;
		this.counter = 5;
	}
	
	
	@Override
	public void doEmulate(SensorSetter sensor) {
		if (this.tick % this.counter == 0 ) {
			if (this.value == 0) {
				this.value = 1;
			} else {
				this.value = 0;
			}
			this.tick = 1;
		} else {
			this.tick ++;
		}
		sensor.setValue(value);
	}

}
