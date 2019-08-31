package ru.job4jhibernate.admanager.servlets;

import ru.job4jhibernate.admanager.dao.CarsStore;
import ru.job4jhibernate.admanager.models.Cars;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class AdsUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Cars cars = CarsStore.getInstance().findCarById(id);
        cars.setStatus("sold");
        CarsStore.getInstance().updateCar(cars);
        resp.sendRedirect(String.format("%s/showads?id=%s", req.getContextPath(), id));
    }
}
