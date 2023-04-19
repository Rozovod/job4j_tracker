package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class HbmTrackerTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @After
    public void wipeTable() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM Item").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        }
    }

    @Test
    public void whenReplaceThenNew() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item("item");
            Item newItem = new Item("newItem");
            tracker.add(item);
            tracker.replace(item.getId(), newItem);
            assertThat(tracker.findById(item.getId()).getName(), is(newItem.getName()));
        }
    }

    @Test
    public void whenDeleteThenEmpty() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item("test1");
            tracker.add(item);
            tracker.delete(item.getId());
            assertThat(tracker.findById(item.getId()), is(nullValue()));
        }
    }

    @Test
    public void whenFindAllThenList() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item itemFirst = new Item("test1");
            Item itemSecond = new Item("test2");
            tracker.add(itemFirst);
            tracker.add(itemSecond);
            assertThat(tracker.findAll(), is(List.of(itemFirst, itemSecond)));
        }
    }

    @Test
    public void whenFindByNameThenList() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item itemFirst = new Item("test1");
            Item itemSecond = new Item("test2");
            tracker.add(itemFirst);
            tracker.add(itemSecond);
            assertThat(tracker.findByName("test2"), is(List.of(itemSecond)));
        }
    }
}
