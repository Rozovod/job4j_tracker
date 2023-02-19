package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

public class DeleteActionTest {
    private final Output out = new StubOutput();
    private final MemTracker tracker = new MemTracker();
    private final DeleteAction deleteAction = new DeleteAction(out);

    @Test
    public void whenDeleted() {
        tracker.add(new Item("Deleted item"));
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        deleteAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete item ===" + ln + "Заявка удалена успешно." + ln));
        assertThat(tracker.findById(1), is(nullValue()));
    }

    @Test
    public void whenNotDeleted() {
        tracker.add(new Item("New item"));
        Input input = mock(Input.class);
        deleteAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete item ===" + ln + "Ошибка удаления заявки." + ln));
        assertThat(tracker.findAll().get(0).getName(), is("New item"));
    }
}