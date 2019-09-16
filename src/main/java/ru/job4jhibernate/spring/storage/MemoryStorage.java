package ru.job4jhibernate.spring.storage;

import ru.job4jhibernate.spring.models.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class MemoryStorage implements Storage{

    private final static MemoryStorage MEMORY_STORE = new MemoryStorage();
    private final List<User> users = new CopyOnWriteArrayList();

    private MemoryStorage() {

    }

    public static MemoryStorage getInstance() {
        return MEMORY_STORE;
    }

    @Override
    public boolean addUser(User user) {
        this.users.add(user);
        return true;
    }
}
