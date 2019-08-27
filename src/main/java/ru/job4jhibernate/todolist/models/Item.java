package ru.job4jhibernate.todolist.models;

import java.sql.Timestamp;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Item {
    private int id;
    private String desc;
    private Timestamp created;
    private boolean done;

    public Item() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
