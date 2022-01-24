package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DiapasonFuncTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = DiapasonFunc.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunction() {
        List<Double> result = DiapasonFunc.diapason(2, 5, x -> x * x - 1);
        List<Double> expected = Arrays.asList(3D, 8D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExponentialFunction() {
        List<Double> result = DiapasonFunc.diapason(1, 4, x -> Math.pow(2, x) * 10);
        List<Double> expected = Arrays.asList(20D, 40D, 80D);
        assertThat(result, is(expected));
    }
}