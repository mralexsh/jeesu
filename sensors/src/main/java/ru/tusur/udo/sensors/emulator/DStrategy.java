package ru.tusur.udo.sensors.emulator;

public class DStrategy implements EmulationStrategy {

    private int ticks = 0;
    private int value = 0;

    private int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
        clearState();
    }

    @Override
    public void doEmulate(FakeSensor sensor) {
        this.ticks ++;
        if (this.ticks == this.counter) {
            this.ticks = 0;
            if (this.value == 0) {
                this.value = 1;
            } else {
                this.value = 0;
            }
        }
        sensor.setValue(this.value);
    }

    private void clearState() {
        this.ticks = 0;
        this.value = 0;
    }

}
