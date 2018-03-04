package ru.tusur.udo.sensors;

import java.util.List;

public class SensorEmulationRuntime extends Thread implements SensorObserver {
	private List<PseudoSensor> pseudoSensors;
	private List<SensorObservable> listeners;
	private int refreshTicks;
	SensorEmulationRuntime() {
		this.refreshTicks = 50;
	}
	public List<PseudoSensor> getPseudoSensors() {
		return pseudoSensors;
	}
	public void setPseudoSensors(List<PseudoSensor> pseudoSensors) {
		this.pseudoSensors = pseudoSensors;
	}
	public List<SensorObservable> getListeners() {
		return listeners;
	}
	public void setListeners(List<SensorObservable> listeners) {
		this.listeners = listeners;
	}
	public int getRefreshTicks() {
		return refreshTicks;
	}
	public void setRefreshTicks(int refreshTicks) {
		this.refreshTicks = refreshTicks;
	}
	public void run() {
		while(true) {
			try {
				if (this.pseudoSensors != null) {
					for (PseudoSensor sensor: this.pseudoSensors) {
						sensor.doEmulate();
					}	
				}
				sleep(this.refreshTicks);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void register(SensorObservable o) {
		this.listeners.add(o);
		
	}
	public void unregister(SensorObservable o) {
		this.listeners.remove(o);
		
	}
	public void notifyObservables() {
		for (SensorObservable item: this.listeners) {
			item.handle(this);
		}
	}
}
