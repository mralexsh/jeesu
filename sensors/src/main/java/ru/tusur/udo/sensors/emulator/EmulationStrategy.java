package ru.tusur.udo.sensors.emulator;

public interface EmulationStrategy {	
	
	void setTicks(int ticks);
	void doEmulate(FakeSensor sensor);
	
}
