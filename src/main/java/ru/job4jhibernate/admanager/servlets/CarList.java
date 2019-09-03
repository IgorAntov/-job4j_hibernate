package ru.job4jhibernate.admanager.servlets;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4jhibernate.admanager.dao.CarsStore;
import ru.job4jhibernate.admanager.filters.FilterList;
import ru.job4jhibernate.admanager.models.Cars;
import ru.job4jhibernate.admanager.services.CarsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CarList extends HttpServlet {

    public CarList() {
        CarsService.getInstance().init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!resp.isCommitted()) {
            req.setAttribute("brand", CarsStore.getInstance().findAllBrand());
            req.setAttribute("carsList", CarsStore.getInstance().findAllCars());
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonParser jsonParser = mapper.getFactory().createParser(req.getInputStream());
        FilterList filterList = mapper.readValue(jsonParser, FilterList.class);
        List<Cars> cars = CarsStore.getInstance().filtersCars(filterList);
        if (filterList.getFoto().equals("true")) {
            cars = cars.stream()
                    .filter(car -> !car.getImageSet().isEmpty())
                    .collect(Collectors.toList());
        }
        resp.setContentType("text/json; charset=utf-8");
        Writer wr = resp.getWriter();
        mapper.writeValue(wr, cars);
    }
}
