package ru.job4jhibernate.admanager.dao;

import ru.job4jhibernate.admanager.models.AdUser;


/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DaoAdUser {

    boolean addAdUser(AdUser adUser);

    AdUser findAdUserByNamePass(String name, String password);

    AdUser findAdUserByName(String name);
}
