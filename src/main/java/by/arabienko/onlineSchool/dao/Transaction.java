package by.arabienko.onlineSchool.dao;

import by.arabienko.onlineSchool.exception.PersistentException;

public interface Transaction {
    <Type extends Dao> void createDao(Type type, Type... types) throws PersistentException;

    void commit() throws PersistentException;

    void rollback() throws PersistentException;
}
