package by.arabienko.onlineSchool.dao.mysql;

import by.arabienko.onlineSchool.dao.BaseDao;
import by.arabienko.onlineSchool.dao.Transaction;
import by.arabienko.onlineSchool.dao.TransactionFactory;
import by.arabienko.onlineSchool.dao.pool.ConnectionPool;
import by.arabienko.onlineSchool.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl extends BaseDao implements TransactionFactory {
    private static final Logger LOGGER =
            LogManager.getLogger(TransactionFactoryImpl.class);
    Connection connection;

    private static final TransactionFactoryImpl instance = new TransactionFactoryImpl();
    public  static TransactionFactoryImpl getInstance(){
        return instance;
    }

    @Override
    public Transaction createTransaction() throws PersistentException {
        this.connection = ConnectionPool.getInstance().getConnection();
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("Autocommit is not impossible for DB. " + e);
            throw new PersistentException(e);
        }
        return new TransactionImpl(connection);
    }

    @Override
    public void close() {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Closing connection is not impossible for DB."+ e);
        }
    }
}
