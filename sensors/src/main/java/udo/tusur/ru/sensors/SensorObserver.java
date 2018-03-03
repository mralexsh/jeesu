package udo.tusur.ru.sensors;

public interface SensorObserver {
	void register(SensorObservable o);
	void unregister(SensorObservable o);
	void notifyObservables();
}
