package ru.job4jhibernate.todolist.store;

import ru.job4jhibernate.todolist.models.Item;

import java.util.Collection;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface DAO {
    /**
     * Load Item list from DB
     * @return Item list
     */
    Collection<Item> getValues();

    /**
     * Add new Item to DB
     * @param item new Item
     * @return operation status
     */
    boolean addItem(Item item);

    /**
     * Fins Item in DB
     * @param id Item id
     * @return Foгnd Item
     */
    Item findItem(String id);

    /**
     * Updates Item in DB
     * @param item updated Item
     * @return operation status
     */
    boolean updateItem(Item item);
}
