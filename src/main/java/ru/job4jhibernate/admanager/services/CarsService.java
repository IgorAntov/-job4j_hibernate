package ru.job4jhibernate.admanager.services;

import ru.job4jhibernate.admanager.dao.CarsStore;
import ru.job4jhibernate.admanager.models.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CarsService {
    private static CarsService ourInstance = new CarsService();

    public static CarsService getInstance() {
        return ourInstance;
    }

    /**
     * DataBase SubTypes Initialisation.
     *
     * @return
     */
    public CarsService init() {
        bodyInit();
        brandInit();
        driveInit();
        engineInit();
        transmissionInit();
        wheelInit();
        return null;
    }

    /**
     * Body Types Init
     */
    private void bodyInit() {
        CarsStore.getInstance().addBody(new Body("Седан"));
        CarsStore.getInstance().addBody(new Body("Хэтчбэк"));
        CarsStore.getInstance().addBody(new Body("Универсал"));
    }

    /**
     * Brand Types Init
     */
    private void brandInit() {
        CarsStore.getInstance().addBrand(new Brand("Audi"));
        CarsStore.getInstance().addBrand(new Brand("Honda"));
        CarsStore.getInstance().addBrand(new Brand("BMV"));
    }

    /**
     * Drive Types Init
     */

    private void driveInit() {
        CarsStore.getInstance().addDrive(new Drive("Передний"));
        CarsStore.getInstance().addDrive(new Drive("Полный"));
        CarsStore.getInstance().addDrive(new Drive("Задний"));
    }

    /**
     * Engine Types Init
     */
    private void engineInit() {
        CarsStore.getInstance().addEngine(new Engine("Бензин"));
        CarsStore.getInstance().addEngine(new Engine("Дизель"));
    }

    /**
     * Transmission Types Init
     */
    private void transmissionInit() {
        CarsStore.getInstance().addTransmission(new Transmission("Механика"));
        CarsStore.getInstance().addTransmission(new Transmission("Автомат"));
    }

    /**
     * Wheel Types Init
     */
    private void wheelInit() {
        CarsStore.getInstance().addWheel(new Wheel("Левый"));
        CarsStore.getInstance().addWheel(new Wheel("Правый"));
    }
}
