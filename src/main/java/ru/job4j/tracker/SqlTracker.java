package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {
    private Connection cn;

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement st = cn.prepareStatement("insert into items(name, created) values(?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, item.getName());
            st.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            st.execute();
            try (ResultSet genKeys = st.getGeneratedKeys()) {
                if (genKeys.next()) {
                    item.setId(genKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        try (PreparedStatement st = cn.prepareStatement(
                "update items set name = ?, created = ? where id = ?")) {
            st.setString(1, item.getName());
            st.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            st.setInt(3, id);
            result = st.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement st = cn.prepareStatement(
                "delete from items where id = ?")) {
            st.setInt(1, id);
            result = st.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement st = cn.prepareStatement("select * from items")) {
            try (ResultSet rsl = st.executeQuery()) {
                while (rsl.next()) {
                    items.add(new Item(
                            rsl.getInt("id"),
                            rsl.getString("name")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement st = cn.prepareStatement("select name from items where name like 'key%'")) {
            try (ResultSet rsl = st.executeQuery()) {
                while (rsl.next()) {
                    items.add(new Item(
                            rsl.getInt("id"),
                            rsl.getString("name")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = new Item();
        try (PreparedStatement st = cn.prepareStatement("select * from items where id = ?")) {
            try (ResultSet rsl = st.getResultSet()) {
                item.setId(rsl.getInt("id"));
                item.setName(rsl.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
