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
    public int refuel(int fuel) {
        int sum = 0;
        int price = 50;
        if (fuel > 0) {
            sum = price * fuel;
        }
        return sum;
    }
}
