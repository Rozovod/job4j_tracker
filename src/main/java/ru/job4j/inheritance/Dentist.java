package ru.job4j.inheritance;

public class Dentist extends Doctor {
    private String tooth;

    public Dentist(String name, String surname, String education, String birthday, String patient, String tooth) {
        super(name, surname, education, birthday, patient);
        this.tooth = tooth;
    }

    public String getTooth() {
        return tooth;
    }

    public void filling() {
    }
}
