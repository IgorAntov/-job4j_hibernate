package ru.job4jhibernate.admanager.servlets;

import ru.job4jhibernate.admanager.dao.CarsStore;
import ru.job4jhibernate.admanager.models.Cars;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4jhibernate.admanager.models.Image;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class AddAds extends HttpServlet {
    private final static String SAVE_DIR = "uploadFiles";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("body", CarsStore.getInstance().findAllBody());
        req.setAttribute("brand", CarsStore.getInstance().findAllBrand());
        req.setAttribute("drive", CarsStore.getInstance().findAllDrive());
        req.setAttribute("engine", CarsStore.getInstance().findAllEngine());
        req.setAttribute("transmission", CarsStore.getInstance().findAllTransmission());
        req.setAttribute("wheel", CarsStore.getInstance().findAllWheel());
        req.getRequestDispatcher("/WEB-INF/views/addads.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cars car = new Cars();
        String appPath = req.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;
        Set<Image> imageSet = new HashSet<>();

        if(ServletFileUpload.isMultipartContent(req)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
                for(FileItem item : multiparts) {
                    if(!item.isFormField() && !item.getName().isEmpty()) {
                        String name = System.currentTimeMillis() + new File(item.getName()).getName();
                        item.write( new File(savePath + File.separator + name));
                        imageSet.add(new Image(name));
                    }else {
                        switch (item.getFieldName()) {
                            case "cost": car.setCost(item.getString()); break;
                            case "mileage": car.setMileage(item.getString()); break;
                            case "power": car.setPower(item.getString()); break;
                            case "title": car.setTitle(item.getString()); break;
                            case "addesc": car.setAddesc(item.getString()); break;
                            case "body": car.setBody(CarsStore.getInstance().findBodyById(item.getString())); break;
                            case "brand": car.setBrand(CarsStore.getInstance().findBrandById(item.getString())); break;
                            case "drive": car.setDrive(CarsStore.getInstance().findDriveById(item.getString())); break;
                            case "engine": car.setEngine(CarsStore.getInstance().findEngineById(item.getString())); break;
                            case "transmission": car.setTransmission(CarsStore.getInstance().findTransmissionById(item.getString())); break;
                            case "wheel": car.setWheel(CarsStore.getInstance().findWheelById(item.getString())); break;
                        }
                    }
                }
            } catch (Exception ex) {
                req.setAttribute("error", "File Upload Failed due to " + ex);
            }
        }else{
            req.setAttribute("error", "Sorry this Servlet only handles file upload request");
        }
        String username = (String) req.getSession().getAttribute("user");
        car.setAduser(CarsStore.getInstance().findAdUserByName(username));
        if (!imageSet.isEmpty()) {
            car.setImageSet(imageSet);
        }
        CarsStore.getInstance().addCar(car);
        resp.sendRedirect(String.format("%s/carslist", req.getContextPath()));
    }
}
