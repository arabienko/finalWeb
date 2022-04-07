package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.UserInfoDao;
import by.arabienko.onlineSchool.entity.UserInfo;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.UserInfoService;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class UserInfoServiceImpl implements UserInfoService {
    private static final Logger LOGGER =
            LogManager.getLogger(UserInfoServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    UserInfoDao userInfoDao = daoFactory.getUserInfoDAO();

    @Override
    public List<UserInfo> findAll()
            throws ExceptionService {
        try {
            return userInfoDao.findAll();
        } catch (DaoException e) {
            LOGGER.debug("Service error findAll" + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public UserInfo findEntityById(Long id)
            throws ExceptionService {
        try {
            return userInfoDao.findEntityById(id);
        } catch (DaoException e) {
            LOGGER.debug("Service error findEntityById " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean delete(UserInfo t)
            throws ExceptionService {
        if (t==null){
            LOGGER.debug("User entity equals null");
            throw new ExceptionService("User entity equals null");
        }
        try {
            return userInfoDao.delete(t);
        } catch (DaoException e) {
            LOGGER.debug("Service error delete " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean delete(long id)
            throws ExceptionService {
        try {
            return userInfoDao.delete(id);
        } catch (DaoException e) {
            LOGGER.debug("Service error delete " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean create(UserInfo t)
            throws ExceptionService {
        if (t==null){
            LOGGER.debug("User entity equals null");
            throw new ExceptionService("User entity equals null");
        }
        try {
            return userInfoDao.create(t);
        } catch (DaoException e) {
            LOGGER.debug("Service error create " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean update(UserInfo info)
            throws ExceptionService {
        if (info==null){
            LOGGER.debug("User entity equals null");
            throw new ExceptionService("User entity equals null");
        }
        try {
            return userInfoDao.update(info);
        } catch (DaoException e) {
            LOGGER.debug("Service error update " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public List<UserInfo> findUserBySurname(String patternName)
            throws ExceptionService {
        if(!DataValidator.isNameValid(patternName)
                || Objects.equals(patternName, "")){
                LOGGER.debug("Name is not valid.");
                throw new ExceptionService("Name is not valid.");
        }
        try {
            return userInfoDao.findUserBySurname(patternName);
        } catch (DaoException e) {
            LOGGER.debug("Service error findUserBySurname " + e);
            throw new ExceptionService(e);
        }    }

    @Override
    public List<UserInfo> findUserTeacher()
            throws ExceptionService {
        try {
            return userInfoDao.findUserTeacher();
        } catch (DaoException e) {
            LOGGER.debug("Service error findUserTeacher " + e);
            throw new ExceptionService(e);
        }    }

    @Override
    public List<UserInfo> findUserStudent()
            throws ExceptionService {
        try {
            return userInfoDao.findUserStudent();
        } catch (DaoException e) {
            LOGGER.debug("Service error findUserStudent " + e);
            throw new ExceptionService(e);
        }    }

    @Override
    public UserInfo findUserAdmin()
            throws ExceptionService {
        try {
            return userInfoDao.findUserAdmin();
        } catch (DaoException e) {
            LOGGER.debug("Service error findUserAdmin " + e);
            throw new ExceptionService(e);
        }    }
}
