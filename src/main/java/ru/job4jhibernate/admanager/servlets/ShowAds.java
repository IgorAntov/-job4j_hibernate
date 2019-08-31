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
public class ShowAds extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cars car = CarsStore.getInstance().findCarById(req.getParameter("id"));
        req.setAttribute("id", car.getId());
        req.setAttribute("title", car.getTitle());
        req.setAttribute("desc", car.getAddesc());
        req.setAttribute("cost", car.getCost());
        req.setAttribute("mileage", car.getMileage());
        req.setAttribute("power", car.getPower());
        req.setAttribute("body", car.getBody().getName());
        req.setAttribute("brand", car.getBrand().getName());
        req.setAttribute("drive", car.getDrive().getName());
        req.setAttribute("engine", car.getEngine().getName());
        req.setAttribute("transmission", car.getTransmission().getName());
        req.setAttribute("wheel", car.getWheel().getName());
        req.setAttribute("imageset", car.getImageSet());
        req.setAttribute("author", car.getAduser().getName());
        req.setAttribute("status", car.getStatus());
        req.getRequestDispatcher("/WEB-INF/views/showads.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
