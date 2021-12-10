package ru.job4j.inheritance;

public class Programmer extends Engineer {
    private String data;

    public Programmer(String name, String surname, String education, String birthday, String project, String data) {
        super(name, surname, education, birthday, project);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void refactoring() {
    }
}
