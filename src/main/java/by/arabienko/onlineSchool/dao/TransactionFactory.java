package by.arabienko.onlineSchool.dao;

import by.arabienko.onlineSchool.exception.PersistentException;

public interface TransactionFactory {
    Transaction createTransaction() throws PersistentException;

    void close();
}
