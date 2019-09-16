package ru.job4jhibernate.spring;

import ru.job4jhibernate.spring.models.User;
import ru.job4jhibernate.spring.storage.Storage;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class InputUserToMemory extends InputUser {

    private final Storage storage;
    public InputUserToMemory(Storage storage ) {
        this.storage = storage;
    }

    @Override
    public boolean add(User user) {
        return storage.addUser(user);
    }
}
