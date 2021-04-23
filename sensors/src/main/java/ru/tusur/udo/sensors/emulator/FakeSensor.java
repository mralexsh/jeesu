package ru.tusur.udo.sensors.emulator;

import ru.tusur.udo.sensors.interfaces.Sensor;

public class FakeSensor implements Sensor {

    private int value;
    private String type;
    private String name;
    private EmulationStrategy emulationStrategy;

    public EmulationStrategy getEmulationStrategy() {
        return emulationStrategy;
    }

    public void setEmulationStrategy(EmulationStrategy emulationStrategy) {
        this.emulationStrategy = emulationStrategy;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void emulate() {
        emulationStrategy.doEmulate(this);
    }
}
