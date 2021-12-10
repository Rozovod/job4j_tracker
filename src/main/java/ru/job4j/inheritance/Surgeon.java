package ru.job4j.inheritance;

public class Surgeon extends Doctor {
    private String organ;

    public Surgeon(String name, String surname, String education, String birthday, String patient, String organ) {
        super(name, surname, education, birthday, patient);
        this.organ = organ;
    }

    public String getOrgan() {
        return organ;
    }

    public void operation() {
    }
}
