package ru.job4jhibernate.todolist.store;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return sessionFactory;
    }
}
