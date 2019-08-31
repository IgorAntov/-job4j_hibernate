package ru.job4jhibernate.admanager.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4jhibernate.admanager.models.*;

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
                Configuration configuration = new Configuration().configure("admanager.cfg.xml");
                configuration.addAnnotatedClass(AdUser.class);
                configuration.addAnnotatedClass(Brand.class);
                configuration.addAnnotatedClass(Transmission.class);
                configuration.addAnnotatedClass(Engine.class);
                configuration.addAnnotatedClass(Drive.class);
                configuration.addAnnotatedClass(Wheel.class);
                configuration.addAnnotatedClass(Body.class);
                configuration.addAnnotatedClass(Image.class);
                configuration.addAnnotatedClass(Cars.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Ad Manager Error: " + e);
            }
        }
        return sessionFactory;
    }
}
