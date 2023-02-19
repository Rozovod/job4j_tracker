package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReplaceActionTest {
    private final Output out = new StubOutput();
    private final MemTracker tracker = new MemTracker();
    private final ReplaceAction rep = new ReplaceAction(out);

    @Test
    public void whenReplaced() {
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Edit item ===" + ln + "Заявка изменена успешно." + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }

    @Test
    public void whenNotReplaced() {
        tracker.add(new Item("Replaced item"));
        Input input = mock(Input.class);
        rep.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Edit item ===" + ln + "Ошибка замены заявки." + ln));
        assertThat(tracker.findAll().get(0).getName(), is("Replaced item"));
    }
}