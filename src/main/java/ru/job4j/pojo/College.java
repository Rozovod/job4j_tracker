package ru.job4j.pojo;

import java.util.Date;

public class College extends Student {
    public static void main(String[] args) {
        College college = new College();
        college.setFullName("Иванов Иван Иванович");
        college.setGroup("404b");
        college.setAdmission(new Date());

        System.out.println("ФИО студента: " + college.getFullName() + System.lineSeparator()
                            + "Группа студента: " + college.getGroup() + System.lineSeparator()
                            + "Дата поступления: " + college.getAdmission());
    }
}
