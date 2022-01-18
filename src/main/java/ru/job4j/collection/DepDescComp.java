package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] o1c = o1.split("/");
        String[] o2c = o2.split("/");
        int rsl = o2c[0].compareTo(o1c[0]);
        if (rsl == 0) {
            for (int i = 0; i < Math.min(o1c.length, o2c.length); i++) {
                rsl = o1c[i].compareTo(o2c[i]);
                if (rsl != 0) {
                    break;
                } else {
                    rsl = Integer.compare(o1c.length, o2c.length);
                }
            }
        }
        return rsl;
    }
}
