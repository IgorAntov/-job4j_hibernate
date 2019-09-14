package ru.job4jhibernate.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.job4jhibernate.spring.models.User;
import ru.job4jhibernate.spring.storage.Storage;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Component(value = "jdbcStorage")
public class InputUserToDb extends InputUser{
    private final Storage storage;

    @Autowired
    public InputUserToDb( @Qualifier("jdbc") Storage storage ) {
        this.storage = storage;
    }

    @Override
    public String add(User user) {
        return storage.addUser(user);
    }
}
