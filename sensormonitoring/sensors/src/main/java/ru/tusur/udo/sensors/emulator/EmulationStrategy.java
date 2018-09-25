package ru.tusur.udo.sensors.emulator;

public interface EmulationStrategy {
	void doEmulate(SensorSetter sensor);
	void setTicksCounter(int counter);
}
