package ru.job4jhibernate.admanager.dao;

import ru.job4jhibernate.admanager.models.Body;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DaoBody {

    boolean addBody(Body body);

    Body findBodyById(String id);

    List<Body> findAllBody();
}
