package by.arabienko.onlineSchool.dao.mysql;

import by.arabienko.onlineSchool.dao.BaseDao;
import by.arabienko.onlineSchool.dao.Dao;
import by.arabienko.onlineSchool.dao.Transaction;
import by.arabienko.onlineSchool.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionImpl implements Transaction {
    private static final Logger LOGGER =
            LogManager.getLogger(TransactionImpl.class);

    private Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public final <Type extends Dao> Type createDao(Type type) {
        BaseDao baseDao = (BaseDao) type;
        baseDao.setConnection(connection);
        return (Type) baseDao;
    }

    @Override
    public void commit() throws PersistentException {
        try {
            connection.commit();
            LOGGER.debug("Commiting is good");
        } catch (SQLException e) {
            LOGGER.error("Committing is not possible. " + e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
            LOGGER.debug("rollback is good");
        } catch (SQLException e) {
            LOGGER.error("Rollback transaction is not possible. " + e);
            throw new PersistentException(e);
        }
    }
}
