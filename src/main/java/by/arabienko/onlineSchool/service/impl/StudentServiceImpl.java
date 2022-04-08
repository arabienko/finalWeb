package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.Transaction;
import by.arabienko.onlineSchool.dao.TransactionFactory;
import by.arabienko.onlineSchool.dao.UserDao;
import by.arabienko.onlineSchool.dao.mysql.TransactionFactoryImpl;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.entity.User;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.UserService;
import by.arabienko.onlineSchool.valid.DataValidator;
import by.arabienko.onlineSchool.exception.ExceptionService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Client service.
 */
public class StudentServiceImpl implements UserService {
    private static final Logger LOGGER =
            LogManager.getLogger(StudentServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    UserDao student = daoFactory.getUserDAO();
    TransactionFactory factory = TransactionFactoryImpl.getInstance();
    Transaction transaction;

    @Override
    public Optional<User> checkUserByLoginPassword
            (String login, String password)
            throws ExceptionService, PersistentException {
        Optional<User> result;
        if (!DataValidator.isLoginValid(login)
                || !DataValidator.isPasswordValid(password)
                || Objects.equals(login, "")
                || password.equals("")){
            LOGGER.debug("The data is not valid.");
            throw new ExceptionService("The data is not valid.");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(student);
            result = student.findEntityByParam(login,
                    DigestUtils.sha512Hex(password));
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error checkUserByLoginPassword "+e);
            transaction.rollback();
            throw new ExceptionService(e);
        }finally {
            factory.close();
        }
        return result;
    }

    @Override
    public boolean isLoginUnique(String patternLogin)
            throws ExceptionService, PersistentException {
        boolean result;
        if (!DataValidator.isLoginValid(patternLogin)
                || Objects.equals(patternLogin, "")){
            LOGGER.debug("The login data is not valid.");
            throw new ExceptionService("The login data is not valid.");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(student);
            result = student.isLoginUnique(patternLogin);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error isLoginUnique "+e);
            transaction.rollback();
            throw new ExceptionService(e);
        }finally {
            factory.close();
        }
        return result;
    }

    @Override
    public boolean registerUser(User user)
            throws ExceptionService, PersistentException {
        boolean result;
        if (user==null){
            LOGGER.debug("User entity equals null");
            throw new ExceptionService("User entity equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(student);
            result = student.registerUser(user);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error registerUser "+e);
            transaction.rollback();
            throw new ExceptionService(e);
        }finally {
            factory.close();
        }
        return result;
    }

    @Override
    public User findById(Long id)
            throws ExceptionService, PersistentException {
        User result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(student);
            result = student.findEntityById(id);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error isLoginUnique "+e);
            transaction.rollback();
            throw new ExceptionService(e);
        }finally {
            factory.close();
        }
        return result;
    }


    @Override
    public boolean save(User user)
            throws ExceptionService, PersistentException {
        boolean result;
        if (user==null){
            LOGGER.debug("User entity equals null");
            throw new ExceptionService("User entity equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(student);
            result = student.create(user);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error save "+e);
            transaction.rollback();
            throw new ExceptionService(e);
        }finally {
            factory.close();
        }
        return result;
    }

    @Override
    public User findByEntityId(long id)
            throws ExceptionService, PersistentException {
        User result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(student);
            result = student.findStudentById(id);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findByEntityId "+e);
            transaction.rollback();
            throw new ExceptionService(e);
        }finally {
            factory.close();
        }
        return result;
    }

    @Override
    public List<User> findAll()
            throws ExceptionService, PersistentException {
        List<User> result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(student);
            result = student.findAll();
            transaction.commit();
        } catch (DaoException | PersistentException | SQLException e) {
            LOGGER.debug("Service error findAll "+e);
            transaction.rollback();
            throw new ExceptionService(e);
        }
        finally {
            factory.close();
        }
        return result;
    }
}
