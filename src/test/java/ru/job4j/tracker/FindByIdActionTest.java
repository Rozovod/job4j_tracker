package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByIdActionTest {
    private final Output out = new StubOutput();
    private final MemTracker tracker = new MemTracker();
    private final FindByIdAction findByIdAction = new FindByIdAction(out);
    private final Item firstItem = new Item(1, "New First Item");
    private final Item secondItem = new Item(2, "New Second Item");

    @Test
    public void whenFound() {
        tracker.add(firstItem);
        tracker.add(secondItem);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        findByIdAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ===" + ln + firstItem + ln));
        assertThat(tracker.findById(1), is(firstItem));
    }

    @Test
    public void whenNotFound() {
        tracker.add(firstItem);
        tracker.add(secondItem);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(3);
        findByIdAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ===" + ln
                + "Заявка с введенным id: " + 3 + " не найдена." + ln));
        assertThat(tracker.findById(3), is(nullValue()));
    }
}