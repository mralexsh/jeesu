package ru.tusur.udo.sensors.emulator;

public class DigitalSensorStrategy implements EmulationStrategy {

    private int ticks = 0;
    private int counter;

    @Override
    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    @Override
    public void doEmulate(FakeSensor sensor) {
        counter ++;
        if (counter == ticks) {
            counter = 0;
            if (sensor.getValue() == 0) {
                sensor.setValue(1);
            } else {
                sensor.setValue(0);
            }
        }
    }
}
