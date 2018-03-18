package ru.tusur.udo.sensors.core;

import java.util.ArrayList;
import java.util.List;

import ru.tusur.udo.sensors.interfaces.SensorObservable;
import ru.tusur.udo.sensors.interfaces.SensorObserver;

public class Alarm implements SensorObserver {

	private List<SensorObservable> observables;
	private String testMessage;
	public Alarm() {
		this.testMessage = "";
		this.observables = new ArrayList<SensorObservable>();
	}
	public void register(SensorObservable o) {
		this.observables.add(o);
	}

	public void unregister(SensorObservable o) {
		this.observables.remove(o);
	}
	
	public void notifyObservables() {
		for(SensorObservable item: this.observables) {
			item.handle(this);
		}
	}
	
	public String getTestMessage() {
		return testMessage;
	}
	public void sendMessage(String message) {
		this.testMessage = message;
		this.notifyObservables();
		this.testMessage = "";
	}

}
