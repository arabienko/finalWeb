package by.arabienko.onlineSchool.dao;

import java.sql.Connection;

public abstract class BaseDao {

    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
