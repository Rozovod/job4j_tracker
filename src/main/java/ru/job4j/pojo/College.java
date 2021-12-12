package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Иванов Иван Иванович");
        student.setGroup("404b");
        student.setAdmission(new Date());

        System.out.println("ФИО студента: " + student.getFullName() + System.lineSeparator()
                            + "Группа студента: " + student.getGroup() + System.lineSeparator()
                            + "Дата поступления: " + student.getAdmission());
    }
}
