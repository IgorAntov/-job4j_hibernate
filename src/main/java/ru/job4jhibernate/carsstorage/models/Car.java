package ru.job4jhibernate.carsstorage.models;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Car {
    private int id;
    private String name;
    private Engine engine;
    private CarBody carBody;
    private Transmission transmission;

    public Car() {
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

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                Objects.equals(name, car.name) &&
                Objects.equals(engine, car.engine) &&
                Objects.equals(carBody, car.carBody) &&
                Objects.equals(transmission, car.transmission);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, engine, carBody, transmission);
    }
}
