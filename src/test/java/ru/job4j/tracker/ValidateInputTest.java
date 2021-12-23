package ru.job4j.tracker;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"one", "1"});
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Select: ");
        assertThat(selected, is(1));
    }

    @Test
    public void whenValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"2"});
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Select: ");
        assertThat(selected, is(2));
    }

    @Test
    public void whenMultipleValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"1", "2", "3"});
        ValidateInput input = new ValidateInput(out, in);
        int selected1 = input.askInt("Select: ");
        assertThat(selected1, is(1));
        int selected2 = input.askInt("Select: ");
        assertThat(selected2, is(2));
        int selected3 = input.askInt("Select: ");
        assertThat(selected3, is(3));
    }

    @Test
    public void whenNegativeNumberInput() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"-1"});
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Select: ");
        assertThat(selected, is(-1));
    }
}