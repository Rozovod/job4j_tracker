package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameActionTest {
    private final Output out = new StubOutput();
    private final MemTracker tracker = new MemTracker();
    private final FindByNameAction findByNameAction = new FindByNameAction(out);
    private final Item firstItem = new Item(1, "New First Item");
    private final Item secondItem = new Item(2, "New Second Item");

    @Test
    public void whenFound() {
        tracker.add(firstItem);
        tracker.add(secondItem);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("New Second Item");
        findByNameAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ===" + ln + secondItem + ln));
        assertThat(tracker.findByName("New Second Item"), is(List.of(secondItem)));
    }

    @Test
    public void whenNotFound() {
        tracker.add(firstItem);
        tracker.add(secondItem);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("New Third Item");
        findByNameAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ===" + ln
                + "Заявки с именем: " + "New Third Item" + " не найдены."+ ln));
        assertThat(tracker.findByName("New Third Item"), is(List.of()));
    }
}