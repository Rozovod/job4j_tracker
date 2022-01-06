package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> user = new HashMap<>();
        user.put("rozovod.store@mail.ru", "Stanislav Kurilenko Sergeevich");
        for (String key : user.keySet()) {
            String value = user.get(key);
            System.out.println(key + " = " + value);
        }
    }
}
