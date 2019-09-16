package ru.job4jhibernate.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.job4jhibernate.spring.storage.JDBCStorage;
import ru.job4jhibernate.spring.storage.MemoryStorage;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@Configuration
public class AppConfig {

    @Bean(name = "memoryStorage")
    public InputUserToMemory getInputUserToMemory() {
        return new InputUserToMemory(MemoryStorage.getInstance());
    }

    @Bean(name = "jdbcStorage")
    public InputUserToDb getInputUserToDB() {
        return new InputUserToDb(JDBCStorage.getInstance());
    }
}
