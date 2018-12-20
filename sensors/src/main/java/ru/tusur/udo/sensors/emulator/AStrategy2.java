package ru.tusur.udo.sensors.emulator;

public class AStrategy2 implements EmulationStrategy {

	private int counter;
	private int ticks;
	private int value;
	private int direction;
	private int max;
	private int min;
	public  AStrategy2() {
		this.min = 0;
		this.max = 100;
		this.counter = 5;
	} 

	@Override
	public void doEmulate(SensorSetter sensor) {
		this.ticks ++;
		if (this.ticks == this.counter) {
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
