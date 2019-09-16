package ru.job4jhibernate.spring.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4jhibernate.spring.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class JDBCStorage implements Storage{
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final JDBCStorage INSTANCE = new JDBCStorage();

    private JDBCStorage() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/dbstore");
        SOURCE.setUsername("pcontrol");
        SOURCE.setPassword("pcontrol");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        createTable();
    }
    public static JDBCStorage getInstance() {
        return INSTANCE;
    }

    /**
     * Create User DataBase
     */
    private  void createTable() {
        try (Connection connection = SOURCE.getConnection()) {
            final PreparedStatement ps = connection.prepareStatement(
                    "create table if not exists springusers(id serial primary key, name character(20))"
            );
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addUser(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO springusers(name) VALUES(?)")
        ) {
            st.setString(1, user.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
