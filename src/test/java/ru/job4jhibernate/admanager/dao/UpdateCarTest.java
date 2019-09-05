package ru.job4jhibernate.admanager.dao;

import org.junit.Test;
import ru.job4jhibernate.admanager.models.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class UpdateCarTest {

    private final CarsStore carsStore = CarsStore.getInstance();

    @Test
    public void whenCarUpdateStatusThenHaveUpdatedInDB() {
        Cars car = new Cars();
        car.setTitle("title");
        car.setStatus("status");
        carsStore.addCar(car);
        car = carsStore.findCarById("1");
        car.setStatus("newstatus");
        CarsStore.getInstance().updateCar(car);
        assertThat(car.getStatus(), is(carsStore.findCarById("1").getStatus()));
    }

}
