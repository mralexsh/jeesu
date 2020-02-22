package ru.tusur.udo.sensors.emulator;

import ru.tusur.udo.sensors.interfaces.Sensor;

public class FakeSensor implements Sensor {

	private EmulationStrategy emulationStrategy;
	private int value;
	private String name;
	
	public Sensor toPureSensor() {
		return new Sensor() {

			@Override
			public int getValue() {			
				return value;
			}

			@Override
			public String getName() {
				return name;
			}
			
		};
	}
	
	
	public void setValue(int value) {
		 this.value = value;
	}
	
	public EmulationStrategy getEmulationStrategy() {
		return emulationStrategy;
	}

	public void setEmulationStrategy(EmulationStrategy emulationStrategy) {
		this.emulationStrategy = emulationStrategy;
	}

	public void emulate() {		
		emulationStrategy.doEmulate(this);
	}

	public int getValue() {
		return value;
	}

	public String getName() {		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
