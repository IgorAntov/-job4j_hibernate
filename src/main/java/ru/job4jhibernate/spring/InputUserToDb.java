package ru.job4jhibernate.spring;

import ru.job4jhibernate.spring.models.User;
import ru.job4jhibernate.spring.storage.Storage;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class InputUserToDb extends InputUser {

    private final Storage storage;

    public InputUserToDb(Storage storage ) {
        this.storage = storage;
    }

    @Override
    public String add(User user) {
        return storage.addUser(user);
    }
}
