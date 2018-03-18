package ru.tusur.udo.sensors.core;

import ru.tusur.udo.sensors.interfaces.EmulationStrategy;
import ru.tusur.udo.sensors.interfaces.SensorEmulator;

public class DStrategy implements EmulationStrategy {

	private int interval;
	private int ticks;
	private int value;
	public DStrategy() {
		this.ticks = 0;
		this.value = 0;				
	}
	public DStrategy(int interval) {
		this.ticks = 0;
		this.value = 0;
		this.interval = interval;		
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public void doEmulate(SensorEmulator sensor) {
		this.ticks ++;
		if (this.ticks == this.interval) {
			this.ticks = 0;
			if (this.value == 0) {
				this.value = 1;
			} else {
				this.value = 0;
			}
			sensor.setValue(this.value);
		}
		
	}

	
}
