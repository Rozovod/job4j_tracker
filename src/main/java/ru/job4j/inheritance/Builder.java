package ru.job4j.inheritance;

public class Builder extends Engineer {
    private String materials;

    public Builder(String name, String surname, String education, String birthday, String project, String materials) {
        super(name, surname, education, birthday, project);
        this.materials = materials;
    }

    public String getMaterials() {
        return materials;
    }

    public void building() {
    }
}
