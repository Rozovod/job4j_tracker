package ru.job4j.tracker;

import java.util.List;

public class DeleteAllItemsAction implements UserAction {
    private final Output out;

    public DeleteAllItemsAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete all items";
    }

    @Override
    public boolean execute(Input input, Store store) {
        List<Item> rsl = store.findAll();
        for (int i = 1; i <= rsl.size() + 1; i++) {
            store.delete(i);
        }
        out.println("All items removed");
        return true;
    }
}
