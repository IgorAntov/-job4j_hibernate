package ru.job4jhibernate.carsstorage.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4jhibernate.carsstorage.models.Car;
import ru.job4jhibernate.carsstorage.models.CarBody;
import ru.job4jhibernate.carsstorage.models.Engine;
import ru.job4jhibernate.carsstorage.models.Transmission;
import ru.job4jhibernate.todolist.store.HibernateSessionFactoryUtil;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class DaoCar implements DAO {

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
    public boolean addEngine(Engine engine) {
        this.tx(session -> session.save(engine));
        return true;
    }

    @Override
    public boolean addCarBody(CarBody carBody) {
        this.tx(session -> session.save(carBody));
        return true;

    }

    @Override
    public boolean addTransmission(Transmission transmission) {
        this.tx(session -> session.save(transmission));
        return true;

    }

    @Override
    public boolean addCar(Car car) {
        this.tx(session -> session.save(car));
        return true;
    }

    @Override
    public boolean updateEngine(Engine engine) {
        this.txUpdate(session -> session.update(engine));
        return true;
    }

    @Override
    public boolean updateBody(CarBody carBody) {
        this.txUpdate(session -> session.update(carBody));
        return true;

    }

    @Override
    public boolean updateTransmission(Transmission transmission) {
        this.txUpdate(session -> session.update(transmission));
        return true;
    }

    @Override
    public boolean updateCar(Car car) {
        this.txUpdate(session -> session.update(car));
        return true;
    }

    @Override
    public boolean removeEngine(Engine engine) {
        this.txUpdate(session -> session.delete(engine));
        return true;
    }

    @Override
    public boolean removeCarBody(CarBody carBody) {
        this.txUpdate(session -> session.delete(carBody));
        return true;
    }

    @Override
    public boolean removeTransmission(Transmission transmission) {
        this.txUpdate(session -> session.delete(transmission));
        return true;
    }

    @Override
    public boolean removeCar(Car car) {
        this.txUpdate(session -> session.delete(car));
        return true;
    }

    @Override
    public Engine findEngineByName(String enginName) {
        return this.tx(session -> {
            Engine engine = null;
            final Query query = session.createQuery("FROM Engine as engine where engine.name = :engineName");
            var rsl = query.setParameter("engineName", enginName).list();
            if (!rsl.isEmpty()) {
                engine = (Engine) rsl.get(0);
            }
            return engine;
        });
    }

    @Override
    public CarBody findCarBodyByName(String name) {
        return this.tx(session -> {
            CarBody carBody = null;
            final Query query = session.createQuery("FROM CarBody as carbody where carbody.name = :carbodyName");
            var rsl = query.setParameter("carbodyName", name).list();
            if (!rsl.isEmpty()) {
                carBody = (CarBody) rsl.get(0);
            }
            return carBody;
        });
    }

    @Override
    public Transmission findTransmissionByName(String name) {
        return this.tx(session -> {
            Transmission transmission = null;
            final Query query = session.createQuery("FROM Transmission as transmission where transmission.name = :transmissionName");
            var rsl = query.setParameter("transmissionName", name).list();
            if (!rsl.isEmpty()) {
                transmission = (Transmission) rsl.get(0);
            }
            return transmission;
        });
    }

    @Override
    public Car findCarByName(String name) {
        return this.tx(session -> {
            Car car = null;
            final Query query = session.createQuery("FROM Car as car where car.name = :carName");
            var rsl = query.setParameter("carName", name).list();
            if (!rsl.isEmpty()) {
                car = (Car) rsl.get(0);
            }
            return car;
        });
    }
}
