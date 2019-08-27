package ru.job4jhibernate.carsstorage.dao;

import ru.job4jhibernate.carsstorage.models.Car;
import ru.job4jhibernate.carsstorage.models.CarBody;
import ru.job4jhibernate.carsstorage.models.Engine;
import ru.job4jhibernate.carsstorage.models.Transmission;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DAO {

    boolean addEngine(Engine engine);

    boolean addCarBody(CarBody carBody);

    boolean addTransmission(Transmission transmission);

    boolean addCar(Car car);

    boolean updateEngine(Engine engine);

    boolean updateBody(CarBody carBody);

    boolean updateTransmission(Transmission transmission);

    boolean updateCar(Car car);

    boolean removeEngine(Engine engine);

    boolean removeCarBody(CarBody carBody);

    boolean removeTransmission(Transmission transmission);

    boolean removeCar(Car car);

    Engine findEngineByName(String engineName);

    CarBody findCarBodyByName(String name);

    Transmission findTransmissionByName(String name);

    Car findCarByName(String name);
}
