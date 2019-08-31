package ru.job4jhibernate.todolist.servlets;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4jhibernate.todolist.models.Item;
import ru.job4jhibernate.todolist.store.DbActionList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ListControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Writer wr = resp.getWriter();
        mapper.writeValue(wr, DbActionList.getInstance().getValues());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonParser jsonParser = mapper.getFactory().createParser(req.getInputStream());
        Item item = mapper.readValue(jsonParser, Item.class);
        if (item.getId() != 0) {
            Item tempItem = DbActionList.getInstance().findItem(String.valueOf(item.getId()));
            tempItem.setDone(item.isDone());
            DbActionList.getInstance().updateItem(tempItem);
        } else {
            item.setCreated(new Timestamp(System.currentTimeMillis()));
            DbActionList.getInstance().addItem(item);
            resp.setContentType("text/json");
            Writer wr = resp.getWriter();
            mapper.writeValue(wr, item);
        }
    }
}
