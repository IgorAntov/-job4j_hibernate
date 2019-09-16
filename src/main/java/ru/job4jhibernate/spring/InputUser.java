package ru.job4jhibernate.spring;

import ru.job4jhibernate.spring.models.User;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public abstract class InputUser {

    public abstract boolean add(User user);
}
