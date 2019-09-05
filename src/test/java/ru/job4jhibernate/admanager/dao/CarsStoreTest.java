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
public class CarsStoreTest {

    private final CarsStore carsStore = CarsStore.getInstance();

    @Test
    public void whenAddNewUserThenGetThisUserFromDB() {
        AdUser adUser = new AdUser("test", "test");
        carsStore.addAdUser(adUser);
        assertThat(adUser.getName(), is(carsStore.findAdUserByName("test").getName()));
    }

    @Test
    public void whenAddBodyToDBThenGetThisBodyFromDB() {
        Body body = new Body("test");
        carsStore.addBody(body);
        assertThat(body.getName(), is(carsStore.findBodyById("1").getName()));
    }

    @Test
    public void whenAddBrandToDBThenGetThisBrendFromDB() {
        Brand brand = new Brand("test");
        carsStore.addBrand(brand);
        assertThat(brand.getName(), is(carsStore.findBrandById("1").getName()));
    }

    @Test
    public void whenAddDriveToDBThenGetThisDriveFromDB() {
        Drive drive = new Drive("test");
        carsStore.addDrive(drive);
        assertThat(drive.getName(), is(carsStore.findDriveById("1").getName()));
    }

    @Test
    public void whenAddEngineToDBThenGetThisEngineFromDB() {
        Engine engine = new Engine("test");
        carsStore.addEngine(engine);
        assertThat(engine.getName(), is(carsStore.findEngineById("1").getName()));
    }

    @Test
    public void whenAddTransmissionToDBThenGetThisTransmissionFromDB() {
        Transmission transmission = new Transmission("test");
        carsStore.addTransmission(transmission);
        assertThat(transmission.getName(), is(carsStore.findTransmissionById("1").getName()));
    }

    @Test
    public void whenAddWheelToDBThenGetThisWheelFromDB() {
        Wheel wheel = new Wheel("test");
        carsStore.addWheel(wheel);
        assertThat(wheel.getName(), is(carsStore.findWheelById("1").getName()));
    }
}