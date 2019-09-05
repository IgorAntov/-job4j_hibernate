package ru.job4jhibernate.admanager.dao;

import org.junit.Test;
import ru.job4jhibernate.admanager.models.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class AddCarToStoreTest {

    private final CarsStore carsStore = CarsStore.getInstance();

    @Test
    public void whenAddCarToDBThenGetThisCarFromDB() {
        AdUser adUser = new AdUser("name", "pass");
        carsStore.addAdUser(adUser);
        Body body = new Body("body");
        carsStore.addBody(body);
        Brand brand = new Brand("drand");
        carsStore.addBrand(brand);
        Drive drive = new Drive("drive");
        carsStore.addDrive(drive);
        Engine engine = new Engine("engine");
        carsStore.addEngine(engine);
        Transmission transmission = new Transmission("transmission");
        carsStore.addTransmission(transmission);
        Wheel wheel = new Wheel("wheel");
        carsStore.addWheel(wheel);
        Cars car = new Cars();
        car.setTitle("title");
        car.setAddesc("description");
        car.setAduser(adUser);
        car.setBody(body);
        car.setBrand(brand);
        car.setDrive(drive);
        car.setEngine(engine);
        car.setTransmission(transmission);
        car.setWheel(wheel);
        carsStore.addCar(car);
        assertThat(car.getTitle(), is(carsStore.findCarById("1").getTitle()));
        assertThat(car.getAddesc(), is(carsStore.findCarById("1").getAddesc()));
        assertThat(car.getAduser().getName(), is(carsStore.findCarById("1").getAduser().getName()));
        assertThat(car.getBody().getName(), is(carsStore.findCarById("1").getBody().getName()));
        assertThat(car.getBrand().getName(), is(carsStore.findCarById("1").getBrand().getName()));
        assertThat(car.getDrive().getName(), is(carsStore.findCarById("1").getDrive().getName()));
        assertThat(car.getEngine().getName(), is(carsStore.findCarById("1").getEngine().getName()));
        assertThat(car.getTransmission().getName(), is(carsStore.findCarById("1").getTransmission().getName()));
        assertThat(car.getWheel().getName(), is(carsStore.findCarById("1").getWheel().getName()));
    }
}
