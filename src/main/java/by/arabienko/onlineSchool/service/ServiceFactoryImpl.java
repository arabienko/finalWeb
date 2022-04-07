package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.dao.Transaction;
import by.arabienko.onlineSchool.dao.TransactionFactory;
import by.arabienko.onlineSchool.dao.mysql.TransactionFactoryImpl;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.impl.ServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceFactoryImpl implements ServiceFactory {
    private static final Logger LOGGER =
            LogManager.getLogger(ServiceFactoryImpl.class);

    private static final ServiceFactory instance =
            new ServiceFactoryImpl();

    private static TransactionFactory transactionFactory;

    public static ServiceFactory getInstance() {
        transactionFactory = TransactionFactoryImpl.getInstance();
        return instance;
    }

    @Override
    public <Type extends Service> Type getService(Type type) throws PersistentException {
        Transaction transaction = transactionFactory.createTransaction();
        ServiceImpl service = (ServiceImpl) type;
        service.setTransaction(transaction);
        return (Type) service;
    }
}
