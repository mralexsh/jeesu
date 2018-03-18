package ru.tusur.udo.sensors.interfaces;

public interface SensorObserver {
	void register(SensorObservable o);
	void unregister(SensorObservable o);
	void notifyObservables();
}
