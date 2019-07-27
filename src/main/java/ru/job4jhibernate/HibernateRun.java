package ru.job4jhibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4jhibernate.models.User;

import java.sql.Timestamp;
import java.util.List;


/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class HibernateRun {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        User user = new User();
        user.setName("User1");
        user.setExpired(new Timestamp(System.currentTimeMillis()));
        saveUser(factory, user);
        User user2 = findUser(factory, 1);
        user2.setName("User2");
        updateUser(factory, user2);
        deleteUser(factory, user);
        List<User> users = findAll(factory);
        factory.close();
    }

    private static void saveUser(SessionFactory factory, User user) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    private static User findUser(SessionFactory factory, int id) {
        Session session = factory.openSession();
        return session.get(User.class, id);

    }

    private static void updateUser(SessionFactory factory, User user) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    private static void deleteUser(SessionFactory factory, User user) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    private static List<User> findAll(SessionFactory factory) {
        Session session = factory.openSession();
        return (List<User>) session.createQuery("From User").list();
    }
}
