package ru.job4jhibernate.spring.storage;

import ru.job4jhibernate.spring.models.User;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class MemoryStorage implements Storage{

    @Override
    public String addUser(User user) {
        String value = "Saved in MemoryStorage";
        return value;
    }
}
