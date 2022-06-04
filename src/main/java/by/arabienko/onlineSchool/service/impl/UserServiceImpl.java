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
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl extends ServiceImpl implements UserService {
    private static final Logger LOGGER =
            LogManager.getLogger(UserServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    UserDao userDao = daoFactory.getUserDAO();
    TransactionFactory factory =
            TransactionFactoryImpl.getInstance();
    Transaction transaction;


    @Override
    public boolean isLoginUnique(String patternLogin)
            throws ExceptionService, PersistentException {
        boolean result = false;
        try {
            if (DataValidator.isLoginValid(patternLogin)) {
                LOGGER.info("Login is unique..");
                transaction = factory.createTransaction();
                transaction.createDao(userDao);
                result = userDao.isLoginUnique(patternLogin);
                transaction.commit();
            }
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error isLoginUnique: " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public User findById(Long id) throws ExceptionService, PersistentException {
        User result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userDao);
            result = userDao.findEntityById(id);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findById " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public boolean save(User user)
            throws ExceptionService, PersistentException {
        boolean result = false;
        if (user == null) {
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
        LOGGER.debug("User creation is completed: " +result);
        return result;
    }

    @Override
    public List<User> findAll()
            throws ExceptionService, PersistentException {
        List<User> result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userDao);
            result = userDao.findAllEntity();
            transaction.commit();
        } catch (DaoException | PersistentException | SQLException e) {
            LOGGER.debug("Service error findAll " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public User findUserByLogin(String patternName)
            throws ExceptionService, PersistentException {
        User result = null;
        try {
            if (DataValidator.isLoginValid(patternName)) {
                transaction = factory.createTransaction();
                transaction.createDao(userDao);
                result = userDao.findUserByLogin(patternName);
                transaction.commit();
            }
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findById " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public User findEntityByParam(String login, String password)
            throws ExceptionService, PersistentException {
        User result;
        try {
                transaction = factory.createTransaction();
                transaction.createDao(userDao);
                result = userDao.findEntityByParam(login,password);
                transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findEntityByParam " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        LOGGER.debug("The user with login {} exists." , login);
        return result;
    }

    @Override
    public User findStudentById(Long id)
            throws ExceptionService, PersistentException {
        User result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userDao);
            result = userDao.findStudentById(id);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findStudent " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public User findTeacherById(Long id)
            throws ExceptionService, PersistentException {
        User result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userDao);
            result = userDao.findTeacherById(id);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error find teacher by ID " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public User findAdmin()
            throws PersistentException, ExceptionService {
        User result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userDao);
            result = userDao.findAdmin();
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error find admin " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }
}
