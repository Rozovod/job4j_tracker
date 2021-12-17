package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        boolean wheelsTurning = true;
    }

    @Override
    public void passengers(int numbPass) {
        Bus bus = new Bus();
        if (numbPass > 0) {
            bus.drive();
        }
    }

    @Override
    public double refuel(double fuel) {
        double sum = 0.0;
        double price = 50.0;
        if (fuel > 0.0) {
            sum = price * fuel;
        }
        return sum;
    }
}
