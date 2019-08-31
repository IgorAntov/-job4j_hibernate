package ru.job4jhibernate.admanager.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.job4jhibernate.admanager.utils.HibernateSessionFactoryUtil;


import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class DaoWrepper {

    public <T> T tx(Function<Session, T> command) {
        final Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T result = command.apply(session);
            transaction.commit();
            return result;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void txUpdate(Consumer<Session> command) {
        final Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            command.accept(session);
            transaction.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
