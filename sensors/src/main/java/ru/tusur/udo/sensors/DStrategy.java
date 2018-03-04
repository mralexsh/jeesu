package ru.tusur.udo.sensors;

public class DStrategy implements EmulationStrategy {

	private int interval;
	private int ticks;
	private int value;
	DStrategy() {
		this.ticks = 0;
		this.value = 0;				
	}
	DStrategy(int interval) {
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
