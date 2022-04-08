package by.arabienko.onlineSchool.dao;

import by.arabienko.onlineSchool.entity.Entity;
import by.arabienko.onlineSchool.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO is a class or interface that describes
 * a set of methods that will be used
 * when interacting with a table
 * or group of tables.
 * @param <K> key in table.
 * @param <T> Entity defines a common business entity
 *          from which all business entities
 *           of the system inherit
 */
public interface Dao<K, T extends Entity> {
    Logger LOGGER = LogManager.getLogger(Dao.class);

    List<T> findAll() throws DaoException, SQLException;

    T findEntityById(K id) throws DaoException;

    boolean delete(T t) throws DaoException;

    boolean delete(K id) throws DaoException;

    boolean create(T t) throws DaoException;

    boolean update(T t) throws DaoException;
/*
    default void close(Statement statement) {
        try {
            if (statement!=null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.debug("Statement close error: " + e.getSQLState());
        }
    }

    default void close(Connection connection) {
        try {
            if (connection!=null) {
                connection.close(); // or connection return code to the pool
            }
        } catch (SQLException e) {
            LOGGER.debug("Connection close error: " + e.getSQLState());
        }
    }
    public void setConnection(Connection connection)*/
}
