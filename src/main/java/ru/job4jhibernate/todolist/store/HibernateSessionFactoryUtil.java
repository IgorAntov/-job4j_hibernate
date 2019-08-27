package ru.job4jhibernate.todolist.store;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4jhibernate.carsstorage.models.Car;
import ru.job4jhibernate.carsstorage.models.CarBody;
import ru.job4jhibernate.carsstorage.models.Engine;
import ru.job4jhibernate.carsstorage.models.Transmission;

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
                configuration.addAnnotatedClass(CarBody.class);
                configuration.addAnnotatedClass(Engine.class);
                configuration.addAnnotatedClass(Transmission.class);
                configuration.addAnnotatedClass(Car.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
        return sessionFactory;
    }
}
