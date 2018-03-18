package ru.tusur.udo.sensors.core;

import ru.tusur.udo.sensors.interfaces.EmulationStrategy;
import ru.tusur.udo.sensors.interfaces.SensorEmulator;

public class AStrategy implements EmulationStrategy {
	private int interval;
	private int ticks;
	private int value;
	private int min;
	private int max;
	private int direction;
	public AStrategy() {
		this.ticks = 0;
		this.value = 0;
		this.direction = 1;
	}
	public AStrategy(int interval) {
		this.ticks = 0;
		this.value = 0;
		this.interval = interval;
		this.direction = 1;		
	}
	
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.value = min;
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public void doEmulate(SensorEmulator sensor) {
		this.ticks ++;
		if (this.ticks == this.interval) {
			this.ticks = 0;
			this.value += this.calcDirection();
		}
		sensor.setValue(this.value);
	}
	private int calcDirection() {
		if (this.value >= this.max) {
			this.direction = -1;
		}
		if (this.value <= this.min) {
			this.direction = 1;
		}
		return this.direction;
	}

}
