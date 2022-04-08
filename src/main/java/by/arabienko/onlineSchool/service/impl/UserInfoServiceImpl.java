package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.Transaction;
import by.arabienko.onlineSchool.dao.TransactionFactory;
import by.arabienko.onlineSchool.dao.UserInfoDao;
import by.arabienko.onlineSchool.dao.mysql.TransactionFactoryImpl;
import by.arabienko.onlineSchool.entity.UserInfo;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.UserInfoService;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserInfoServiceImpl implements UserInfoService {
    private static final Logger LOGGER =
            LogManager.getLogger(UserInfoServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    UserInfoDao userInfoDao = daoFactory.getUserInfoDAO();
    TransactionFactory factory =
            TransactionFactoryImpl.getInstance();
    Transaction transaction;

    @Override
    public List<UserInfo> findAll()
            throws ExceptionService, PersistentException {
        List<UserInfo> result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userInfoDao);
            result = userInfoDao.findAll();
            transaction.commit();
        } catch (DaoException | PersistentException | SQLException e) {
            LOGGER.debug("Service error findAll" + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public UserInfo findEntityById(Long id)
            throws ExceptionService, PersistentException {
        UserInfo result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userInfoDao);
            result = userInfoDao.findEntityById(id);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findEntityById " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public boolean delete(UserInfo t)
            throws ExceptionService, PersistentException {
        boolean result;
        if (t==null) {
            LOGGER.debug("User entity equals null");
            throw new ExceptionService("User entity equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userInfoDao);
            result = userInfoDao.delete(t);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error delete " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public boolean delete(long id)
            throws ExceptionService, PersistentException {
        boolean result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userInfoDao);
            result = userInfoDao.delete(id);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error delete " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public boolean create(UserInfo t)
            throws ExceptionService, PersistentException {
        boolean result;
        if (t==null) {
            LOGGER.debug("User entity equals null");
            throw new ExceptionService("User entity equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userInfoDao);
            result = userInfoDao.create(t);
            transaction.commit();
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
    public boolean update(UserInfo info)
            throws ExceptionService, PersistentException {
        boolean result;
        if (info==null) {
            LOGGER.debug("User entity equals null");
            throw new ExceptionService("User entity equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userInfoDao);
            result = userInfoDao.update(info);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error update " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public List<UserInfo> findUserBySurname(String patternName)
            throws ExceptionService, PersistentException {
        if (!DataValidator.isNameValid(patternName)
                || Objects.equals(patternName, "")) {
            LOGGER.debug("Name is not valid.");
            throw new ExceptionService("Name is not valid.");
        }
        List<UserInfo> result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userInfoDao);
            result = userInfoDao.findUserBySurname(patternName);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findUserBySurname " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        }finally {
            factory.close();
        }
        return result;
    }

    @Override
    public List<UserInfo> findUserTeacher()
            throws ExceptionService, PersistentException {
        List<UserInfo> result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userInfoDao);
            result = userInfoDao.findUserTeacher();
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findUserTeacher " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        }finally {
            factory.close();
        }
        return result;
    }

    @Override
    public List<UserInfo> findUserStudent()
            throws ExceptionService, PersistentException {
        List<UserInfo> result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userInfoDao);
            result = userInfoDao.findUserStudent();
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findUserStudent " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        }finally {
            factory.close();
        }
        return result;
    }

    @Override
    public UserInfo findUserAdmin()
            throws ExceptionService, PersistentException {
        UserInfo result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(userInfoDao);
            result = userInfoDao.findUserAdmin();
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findUserAdmin " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        }finally {
            factory.close();
        }
        return result;
    }
}
