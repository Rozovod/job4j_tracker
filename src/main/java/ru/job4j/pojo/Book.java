package ru.job4j.pojo;

public class Book {
    private String name;
    private int numbersOfPages;

    public Book(String name, int numbersOfPages) {
        this.name = name;
        this.numbersOfPages = numbersOfPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumbersOfPages() {
        return numbersOfPages;
    }

    public void setNumbersOfPages(int numbersOfPages) {
        this.numbersOfPages = numbersOfPages;
    }
}
