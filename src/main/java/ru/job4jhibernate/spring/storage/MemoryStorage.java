package ru.job4jhibernate.spring.storage;

import org.springframework.stereotype.Component;
import ru.job4jhibernate.spring.models.User;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Component(value = "memory")
public class MemoryStorage implements Storage{

    @Override
    public String addUser(User user) {
        String value = "Saved in MemoryStorage";
        return value;
    }
}
