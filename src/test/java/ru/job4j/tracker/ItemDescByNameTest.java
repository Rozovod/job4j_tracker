package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemDescByNameTest {

    @Test
    public void whenDescSortByName() {
        List<Item> items = Arrays.asList(
                new Item(1, "item1"),
                new Item(2, "item10"),
                new Item(3, "item100"),
                new Item(4, "item1000")
        );
        Collections.sort(items, new ItemDescByName());
        List<Item> expected = Arrays.asList(
                new Item(4, "item1000"),
                new Item(3, "item100"),
                new Item(2, "item10"),
                new Item(1, "item1")
        );
        assertThat(items, is(expected));
    }
}