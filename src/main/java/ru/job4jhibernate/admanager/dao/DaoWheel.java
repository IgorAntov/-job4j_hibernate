package ru.job4jhibernate.admanager.dao;

import ru.job4jhibernate.admanager.models.Wheel;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DaoWheel {

    boolean addWheel(Wheel wheel);

    Wheel findWheelById(String id);

    List<Wheel> findAllWheel();
}
