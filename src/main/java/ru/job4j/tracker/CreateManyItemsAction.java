package ru.job4j.tracker;

public class CreateManyItemsAction implements UserAction {
    private final Output out;

    public CreateManyItemsAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add many items";
    }

    @Override
    public boolean execute(Input input, Store store) {
        out.println("=== Create many Items ===");
        int quantity = input.askInt("Enter quantity: ");
        for (int i = 1; i <= quantity; i++) {
            store.add(new Item(String.format("item_%s", i)));
        }
        out.println(String.format("Created %s items", quantity));
        return true;
    }
}
