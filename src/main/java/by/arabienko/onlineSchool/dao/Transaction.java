package by.arabienko.onlineSchool.dao;

import by.arabienko.onlineSchool.exception.PersistentException;

public interface Transaction {
    <Type extends Dao> Type createDao(Type type);

    void commit() throws PersistentException;

    void rollback() throws PersistentException;
}
