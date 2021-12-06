package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println(active);
        System.out.println(status);
        System.out.println(message);
    }

    public static void main(String[] args) {
        Error object1 = new Error();
        Error object2 = new Error(false, 0, "Не ошибка");
        Error object3 = new Error(true, 1, "Ошибка");
        object1.printInfo();
        object2.printInfo();
        object3.printInfo();
    }
}
