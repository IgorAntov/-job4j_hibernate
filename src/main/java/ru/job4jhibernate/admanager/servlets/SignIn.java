package ru.job4jhibernate.admanager.servlets;

import ru.job4jhibernate.admanager.dao.CarsStore;
import ru.job4jhibernate.admanager.models.AdUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!resp.isCommitted()) {
            req.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        AdUser adUser = CarsStore.getInstance().findAdUserByNamePass(name, password);
        if (adUser != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", adUser.getName());
            if (!resp.isCommitted()) {
                req.setAttribute("carsList", CarsStore.getInstance().findAllCars());
                req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "Credential invalid.");
            doGet(req, resp);
        }
    }
}
