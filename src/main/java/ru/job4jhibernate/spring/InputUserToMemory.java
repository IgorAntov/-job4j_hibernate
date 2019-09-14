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
@Component("memoryStorage")
public class InputUserToMemory extends InputUser {

    private final Storage storage;
    @Autowired
    public InputUserToMemory(@Qualifier("memory") Storage storage ) {
        this.storage = storage;
    }

    @Override
    public String add(User user) {
        return storage.addUser(user);
    }
}
