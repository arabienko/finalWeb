package by.arabienko.onlineSchool.dao.pool;

import by.arabienko.onlineSchool.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

final public class ConnectionPool {
    private static final Logger LOGGER =
            LogManager.getLogger(ConnectionPool.class);

    private static final String PROPERTY_PATH = "property/DB.properties";
    Properties property = PropertyLoader.loadProperty(PROPERTY_PATH);
    private String url =
            property.getProperty("db.url");
    private String driver =
            property.getProperty("db.driver");
    private String user =
            property.getProperty("db.user");
    private String password =
            property.getProperty("db.password");
    private int maxSize = Integer.parseInt(property.getProperty("db.poolsize"));
    private int checkConnectionTimeout =
            Integer.parseInt(property.getProperty("db.timeout"));

    private BlockingQueue<PooledConnection> freeConnections =
            new LinkedBlockingQueue<>();
    private Set<PooledConnection> usedConnections =
            new ConcurrentSkipListSet<>();
    private static ReentrantLock lock = new ReentrantLock();

    private ConnectionPool() throws SQLException, PersistentException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
    }

    public Connection getConnection()
            throws PersistentException {
        lock.lock();
        PooledConnection connection = null;
        try {
            while (connection==null) {
                try {
                    if (!freeConnections.isEmpty()) {
                        connection = freeConnections.take();
                        if (!connection.isValid(checkConnectionTimeout)) {
                            try {
                                connection.getConnection().close();
                            } catch (SQLException e) {
                            }
                            connection = null;
                        }
                    } else if (usedConnections.size() < maxSize) {
                        connection = createConnection();
                    } else {
                        LOGGER.error("The limit of number of " +
                                "database connections is exceeded");
                        throw new PersistentException();
                    }
                } catch (InterruptedException | SQLException e) {
                    LOGGER.error("It is impossible " +
                            "to connect to a database", e);
                    throw new PersistentException(e);
                }
            }
            usedConnections.add(connection);
            LOGGER.debug(String.format("Connection was received from pool. " +
                            "Current pool size: %d used connections; " +
                            "%d free connection", usedConnections.size(),
                    freeConnections.size()));
        } finally {
            lock.unlock();
        }
        return connection;
    }

    void freeConnection(
            PooledConnection connection) {
        lock.lock();
        try {
            try {
                if (connection.isValid(checkConnectionTimeout)) {
                    connection.clearWarnings();
                    connection.setAutoCommit(true);
                    usedConnections.remove(connection);
                    freeConnections.put(connection);
                    LOGGER.debug(String.format("Connection was returned into pool. " +
                                    "Current pool size: %d used connections; " +
                                    "%d free connection", usedConnections.size(),
                            freeConnections.size()));
                }
            } catch (SQLException | InterruptedException e1) {
                LOGGER.warn("It is impossible " +
                        "to return database connection into pool", e1);
                try {
                    connection.getConnection().close();
                } catch (SQLException e2) {
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void init(
            String driverClass, String url, String user,
            String password, int startSize, int maxSize,
            int checkConnectionTimeout) throws PersistentException {
        lock.lock();
        try {
            destroy();
            Class.forName(driverClass);
            this.url = url;
            this.user = user;
            this.password = password;
            this.maxSize = maxSize;
            this.checkConnectionTimeout =
                    checkConnectionTimeout;
            for (int counter = 0; counter < startSize; counter++) {
                freeConnections.put(createConnection());
            }
        } catch (ClassNotFoundException |
                SQLException | InterruptedException e) {
            LOGGER.fatal("It is impossible " +
                    "to initialize connection pool", e);
            throw new PersistentException(e);
        } finally {
            lock.unlock();
        }
    }

    private static ConnectionPool instance;

    static {
        try {
            instance = new ConnectionPool();
        } catch (SQLException | PersistentException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    private PooledConnection createConnection()
            throws SQLException {
        return new PooledConnection(
                DriverManager.getConnection(url, user, password));
    }

    public void destroy() {
        lock.lock();
        try {
            usedConnections.addAll(freeConnections);
            freeConnections.clear();
            for (PooledConnection connection : usedConnections) {
                try {
                    connection.getConnection().close();
                } catch (SQLException e) {
                }
            }
            usedConnections.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }
}
