package ru.job4jhibernate.admanager.dao;

import ru.job4jhibernate.admanager.models.Cars;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DaoCars {

    boolean addCar(Cars cars);

    boolean updateCar(Cars cars);

    List<Cars> findAllCars();

    Cars findCarById(String id);
}
