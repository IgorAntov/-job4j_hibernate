package ru.job4jhibernate.spring.storage;

import ru.job4jhibernate.spring.models.User;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface Storage {

    String addUser(User user);
}
