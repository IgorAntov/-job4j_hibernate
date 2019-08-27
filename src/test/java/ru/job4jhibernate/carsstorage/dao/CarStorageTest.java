package ru.job4jhibernate.carsstorage.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.job4jhibernate.carsstorage.models.Car;
import ru.job4jhibernate.carsstorage.models.CarBody;
import ru.job4jhibernate.carsstorage.models.Engine;
import ru.job4jhibernate.carsstorage.models.Transmission;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CarStorageTest {

    @Test
    public void whenAddEngineToDBThenSaveCarObjectToDB() {
        DaoCar carStorage = new DaoCar();
        Engine engine = new Engine();
        engine.setName("diesel");
        carStorage.addEngine(engine);
        Assert.assertThat(carStorage.findEngineByName("diesel").getName().equals(engine.getName()), is(true));
    }

    @Test
    public void whenAddCarBodyToDBThenSaveCarBodyToDB() {
        DaoCar carStorage = new DaoCar();
        CarBody carBody = new CarBody();
        carBody.setName("sedan");
        carStorage.addCarBody(carBody);
        Assert.assertThat(carStorage.findCarBodyByName("sedan").getName().equals(carBody.getName()), is(true));
    }

    @Test
    public void whenAddTransmissionToDBThenSaveTransmissionToDB() {
        DaoCar carStorage = new DaoCar();
        Transmission transmission = new Transmission();
        transmission.setName("auto");
        carStorage.addTransmission(transmission);
        Assert.assertThat(carStorage.findTransmissionByName("auto").getName().equals(transmission.getName()), is(true));
    }

    @Test
    public void whenAddCarToDBThenSaveCarToDB() {
        DaoCar carStorage = new DaoCar();
        Car car = new Car();
        Engine engine = new Engine();
        engine.setName("gasoline");
        CarBody carBody = new CarBody();
        carBody.setName("hatchback");
        Transmission transmission = new Transmission();
        transmission.setName("mechanical");
        carStorage.addCarBody(carBody);
        carStorage.addEngine(engine);
        carStorage.addTransmission(transmission);
        engine = carStorage.findEngineByName("gasoline");
        carBody = carStorage.findCarBodyByName("hatchback");
        transmission = carStorage.findTransmissionByName("mechanical");
        car.setName("Skoda");
        car.setCarBody(carBody);
        car.setEngine(engine);
        car.setTransmission(transmission);
        carStorage.addCar(car);
        Car result = carStorage.findCarByName("Skoda");
        Assert.assertThat(result.getName().equals(car.getName()), is(true));
        Assert.assertThat(result.getEngine().getName().equals(engine.getName()), is(true));
        Assert.assertThat(result.getCarBody().getName().equals(carBody.getName()), is(true));
        Assert.assertThat(result.getTransmission().getName().equals(transmission.getName()), is(true));
    }

    @Test
    public void whenUpdateEngineThenGetUpdatedEngineFromDB() {
        DaoCar carStorage = new DaoCar();
        Engine engine = new Engine();
        engine.setName("engine");
        carStorage.addEngine(engine);
        engine = carStorage.findEngineByName("engine");
        engine.setName("engineNew");
        carStorage.updateEngine(engine);
        engine = carStorage.findEngineByName("engineNew");
        Assert.assertThat(engine, is(notNullValue()));
    }

    @Test
    public void whenUpdateCarBodyThenGetUpdatedCarBodyFromDB() {
        DaoCar carStorage = new DaoCar();
        CarBody carBody = new CarBody();
        carBody.setName("carBody");
        carStorage.addCarBody(carBody);
        carBody = carStorage.findCarBodyByName("carBody");
        carBody.setName("carBodyNew");
        carStorage.updateBody(carBody);
        carBody = carStorage.findCarBodyByName("carBodyNew");
        Assert.assertThat(carBody, is(notNullValue()));
    }

    @Test
    public void whenUpdateTransmissionThenGetUpdatedTransmissionFromDB() {
        DaoCar carStorage = new DaoCar();
        Transmission transmission = new Transmission();
        transmission.setName("transmission");
        carStorage.addTransmission(transmission);
        transmission = carStorage.findTransmissionByName("transmission");
        transmission.setName("transmissionNew");
        carStorage.updateTransmission(transmission);
        transmission = carStorage.findTransmissionByName("transmissionNew");
        Assert.assertThat(transmission, is(notNullValue()));
    }

    @Test
    public void whenUpdateCarThenGetUpdatedCarFromDB() {
        DaoCar carStorage = new DaoCar();
        Car car = new Car();
        car.setName("car");
        carStorage.addCar(car);
        car = carStorage.findCarByName("car");
        car.setName("carNew");
        carStorage.updateCar(car);
        car = carStorage.findCarByName("carNew");
        Assert.assertThat(car, is(notNullValue()));
    }

    @Test
    public void whenRemoveEngineThenRemoveEngineFromDB() {
        DaoCar carStorage = new DaoCar();
        Engine engine = new Engine();
        engine.setName("engineR");
        carStorage.addEngine(engine);
        engine = carStorage.findEngineByName("engineR");
        Assert.assertThat(engine, is(notNullValue()));
        carStorage.removeEngine(engine);
        engine = carStorage.findEngineByName("engineR");
        Assert.assertThat(engine, is(nullValue()));
    }

    @Test
    public void whenRemoveCarBodyThenRemoveCarBodyFromDB() {
        DaoCar carStorage = new DaoCar();
        CarBody carBody = new CarBody();
        carBody.setName("carBodyR");
        carStorage.addCarBody(carBody);
        carBody = carStorage.findCarBodyByName("carBodyR");
        Assert.assertThat(carBody, is(notNullValue()));
        carStorage.removeCarBody(carBody);
        carBody = carStorage.findCarBodyByName("carBodyR");
        Assert.assertThat(carBody, is(nullValue()));
    }

    @Test
    public void whenRemoveTransmissionThenRemoveTransmissionFromDB() {
        DaoCar carStorage = new DaoCar();
        Transmission transmission = new Transmission();
        transmission.setName("transmissionR");
        carStorage.addTransmission(transmission);
        transmission = carStorage.findTransmissionByName("transmissionR");
        Assert.assertThat(transmission, is(notNullValue()));
        carStorage.removeTransmission(transmission);
        transmission = carStorage.findTransmissionByName("transmissionR");
        Assert.assertThat(transmission, is(nullValue()));
    }

    @Test
    public void whenRemoveCarThenRemoveCarFromDB() {
        DaoCar carStorage = new DaoCar();
        Car car = new Car();
        car.setName("carR");
        carStorage.addCar(car);
        car = carStorage.findCarByName("carR");
        Assert.assertThat(car, is(notNullValue()));
        carStorage.removeCar(car);
        car = carStorage.findCarByName("carR");
        Assert.assertThat(car, is(nullValue()));
    }
}