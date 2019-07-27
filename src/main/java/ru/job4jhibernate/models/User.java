package ru.job4jhibernate.models;

import java.sql.Timestamp;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class User {
    private int id;
    private String name;
    private Timestamp expired;

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }

}
