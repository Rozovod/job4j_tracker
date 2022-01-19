package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] o1c = o1.split("/");
        String[] o2c = o2.split("/");
        int rsl = o2c[0].compareTo(o1c[0]);
        return rsl != 0 ? rsl : o1.compareTo(o2);
    }
}
