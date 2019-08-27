package ru.job4jhibernate.todolist.store;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.job4jhibernate.todolist.models.Item;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class DbActionList implements DAO {
    public static final DAO DB_ACTION_LIST = new DbActionList();

    public static DAO getInstance() {
        return DB_ACTION_LIST;
    }

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

    @Override
    public Collection<Item> getValues() {
        return this.tx(session -> session.createQuery("FROM Item item ORDER BY item.id").list());
    }

    @Override
    public boolean addItem(Item item) {
        this.tx(session -> session.save(item));
        return true;
    }

    @Override
    public boolean updateItem(Item item) {
        this.txUpdate(session -> session.update(item));
        return true;
    }

    @Override
    public Item findItem(String id) {
        return this.tx(session -> session.get(Item.class, Integer.parseInt(id)));
    }

}
