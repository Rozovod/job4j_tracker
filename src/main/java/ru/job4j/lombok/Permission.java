package ru.job4j.lombok;

import lombok.*;

import java.util.List;

@Data
@Builder
public class Permission {
    private int id;
    private String name;
    @Singular
    private List<String> rules;
}
