package ru.job4j.collection;

import java.util.HashSet;

public class UniqueText {
    public static boolean isEquals(String originText, String duplicateText) {
        boolean rsl = true;
        String[] origin = originText.split(" ");
        String[] text = duplicateText.split(" ");
        HashSet<String> check = new HashSet<>();
        for (String word : origin) {
            check.add(word);
        }
        HashSet<String> checkTxt = new HashSet<>();
        for (String line : text) {
            if (check.contains(line)) {
                checkTxt.add(line);
            }
        }
        rsl = check.equals(checkTxt);
        return rsl;
    }
}
