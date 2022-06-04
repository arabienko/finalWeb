package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.dao.Transaction;
import by.arabienko.onlineSchool.dao.TransactionFactory;
import by.arabienko.onlineSchool.dao.mysql.TransactionFactoryImpl;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.Service;
import by.arabienko.onlineSchool.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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


    private static final List<Service> SERVICES = new CopyOnWriteArrayList<>();
    static {
        SERVICES.add(new UserServiceImpl());
        SERVICES.add(new TeacherSubjectServiceImpl());
        SERVICES.add(new TeacherCourseServiceImpl());
        SERVICES.add(new SubjectsServiceImpl());
        SERVICES.add(new StudentCourseServiceImpl());
        SERVICES.add(new ScheduleServiceImpl());
    }



    @Override
    public <Type extends Service> Type getService(Type type) throws PersistentException {
        Transaction transaction = transactionFactory.createTransaction();
        ServiceImpl service = null;
        if (SERVICES.contains(type)) {
            int id = SERVICES.indexOf(type);
            service = (ServiceImpl) SERVICES.get(id);
            service.setTransaction(transaction);
        }
        return (Type) service;
    }

    public void close(){
        transactionFactory.close();
    }
}

