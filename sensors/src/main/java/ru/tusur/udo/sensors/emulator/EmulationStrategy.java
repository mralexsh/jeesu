package ru.tusur.udo.sensors.emulator;

public interface EmulationStrategy {	
	
	void setCounter(int ticks);
	void doEmulate(FakeSensor sensor);
	
}
