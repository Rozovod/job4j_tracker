package ru.job4j.oop;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PointTest {

    @Test
    public void distance3d() {
        Point a = new Point(1, 2, 3);
        Point b = new Point(7, 8, 9);
        double rsl = a.distance3d(b);
        assertThat(rsl, closeTo(10.392, 0.001));
    }

}