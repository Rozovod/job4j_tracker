package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        int generatedId = (int) session.save(item);
        item.setId(generatedId);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        int affectedRows = session.createQuery(
                "UPDATE Item SET name = :iName, created = :iCreated WHERE id = :iId")
                .setParameter("iName", item.getName())
                .setParameter("iCreated", item.getCreated())
                .setParameter("iId", item.getId())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return affectedRows > 0;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        int affectedRows = session.createQuery("DELETE Item WHERE id = :iId")
                .setParameter("iId", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return affectedRows > 0;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        List<Item> result = session.createQuery("from Item", Item.class).getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        List<Item> result = session.createQuery("from Item as i where i.name = :iKey", Item.class)
                .setParameter("iKey", key)
                .getResultList();
        session.close();
        return result;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        Item item = session.get(Item.class, id);
        session.close();
        return item;
    }
}
