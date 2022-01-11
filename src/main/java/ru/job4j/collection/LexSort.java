package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String[] leftDel = left.split("\\.", 2);
        String[] rightDel = right.split("\\.", 2);
        return Integer.compare(Integer.parseInt(leftDel[0]), Integer.parseInt(rightDel[0]));
    }
}
