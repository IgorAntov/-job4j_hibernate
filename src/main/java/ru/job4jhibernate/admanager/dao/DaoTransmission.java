package ru.job4jhibernate.admanager.dao;

import ru.job4jhibernate.admanager.models.Transmission;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DaoTransmission {

    boolean addTransmission(Transmission transmission);

    Transmission findTransmissionById(String id);

    List<Transmission> findAllTransmission();
}
