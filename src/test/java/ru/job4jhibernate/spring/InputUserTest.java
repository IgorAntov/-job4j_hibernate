package ru.job4jhibernate.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4jhibernate.spring.models.User;

import static org.hamcrest.Matchers.is;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 *
 * Annotation-based configuration Test
 */
public class InputUserTest {

    @Test
    public void whenAddUserSaveThisUserToMemoryStorage() {
        ApplicationContext context =  new ClassPathXmlApplicationContext("spring-context.xml");
        InputUser inputUser = context.getBean("memoryStorage", InputUser.class);
        Assert.assertThat(inputUser.add(new User()), is("Saved in MemoryStorage"));
    }

    @Test
    public void whenAddUserSaveThisUserToJDBCStorage() {
        ApplicationContext context =  new ClassPathXmlApplicationContext("spring-context.xml");
        InputUser inputUser = context.getBean("jdbcStorage", InputUser.class);
        Assert.assertThat(inputUser.add(new User()), is("Saved in JDBCStorage"));
    }
}