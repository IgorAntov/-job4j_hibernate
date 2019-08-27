package ru.job4jhibernate.carsstorage.models;

import java.util.Objects;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Engine {
    private int id;
    private String name;

    public Engine() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return id == engine.id &&
                Objects.equals(name, engine.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}