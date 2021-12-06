package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
       return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int b) {
        return b - x;
    }

    public int divide(int c) {
        return c / x;
    }

    public int sumAllOperation(int d) {
        return sum(d) + multiply(d) + minus(d) + divide(d);
    }

    public static void main(String[] args) {
        int result = sum(10);
        System.out.println(result);
        Calculator calculator = new Calculator();
        int rsl = calculator.multiply(5);
        System.out.println(rsl);
        int rsl1 = minus(15);
        System.out.println(rsl1);
        Calculator calc = new Calculator();
        int rsl2 = calc.divide(25);
        System.out.println(rsl2);
        Calculator calcSumAll = new Calculator();
        int rsl3 = calcSumAll.sumAllOperation(10);
        System.out.println(rsl3);
    }
}
