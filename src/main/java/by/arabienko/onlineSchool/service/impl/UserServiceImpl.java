package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.Transaction;
import by.arabienko.onlineSchool.dao.TransactionFactory;
import by.arabienko.onlineSchool.dao.UserDao;
import by.arabienko.onlineSchool.dao.mysql.TransactionFactoryImpl;
import by.arabienko.onlineSchool.entity.User;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER =
            LogManager.getLogger(UserServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    UserDao userDao = daoFactory.getUserDAO();
    TransactionFactory factory =
            TransactionFactoryImpl.getInstance();
    Transaction transaction;

    @Override
    public Optional<User> checkUserByLoginPassword(String login, String password) throws ExceptionService, PersistentException {
        return Optional.empty();
    }

    @Override
    public boolean isLoginUnique(String patternLogin) throws ExceptionService, PersistentException {
        return false;
    }

    @Override
    public boolean registerUser(User user) throws ExceptionService, PersistentException {
        return false;
    }

    @Override
    public User findById(Long id) throws ExceptionService, PersistentException {
        return null;
    }

    @Override
    public boolean save(User user) throws ExceptionService, PersistentException {
        boolean result = false;
        if (user==null) {
            LOGGER.debug("User entity equals null");
            throw new ExceptionService("User entity equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userDao);
            if (userDao.isLoginUnique(user.getLogin())) {
                result = userDao.create(user);
                transaction.commit();
            }
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error create " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public List<User> findAll() throws ExceptionService, PersistentException {
        return null;
    }

    @Override
    public User findByEntityId(long id) throws ExceptionService, PersistentException {
        return null;
    }
}
