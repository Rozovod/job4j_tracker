package ru.job4j.oop;

public class Bus implements Vehicle {
    @Override
    public void move() {
        System.out.println("Автобус передвигается по трассам.");
    }

    @Override
    public void fuel() {
        System.out.println("Автобус заправляется газом или дизельным топливом.");
    }
}
