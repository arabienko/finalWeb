package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.dao.Transaction;
import by.arabienko.onlineSchool.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract public class ServiceImpl implements Service {
    private static final Logger LOGGER =
            LogManager.getLogger(ServiceImpl.class);

    protected Transaction transaction = null;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
