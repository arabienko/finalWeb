package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.UserDao;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.entity.User;
import by.arabienko.onlineSchool.service.UserService;
import by.arabienko.onlineSchool.valid.DataValidator;
import by.arabienko.onlineSchool.exception.ExceptionService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Teacher service.
 */
public class TeacherServiceImpl implements UserService {
    private static final Logger LOGGER =
            LogManager.getLogger(TeacherServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    UserDao teacher = daoFactory.getUserDAO();

    @Override
    public Optional<User> checkUserByLoginPassword(
            String login, String password) throws ExceptionService {
        if (!DataValidator.isLoginValid(login)
                || !DataValidator.isPasswordValid(password)
                || Objects.equals(login, "")
                || password.equals("")){
            LOGGER.debug("The data is not valid.");
            throw new ExceptionService("The data is not valid.");
        }
        try {
            return teacher.findEntityByParam(login, DigestUtils.sha512Hex(password));
        } catch (DaoException e) {
            LOGGER.debug("Service error checkUserByLoginPassword "+e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean isLoginUnique(String patternLogin) throws ExceptionService {
        if (!DataValidator.isLoginValid(patternLogin)
                || Objects.equals(patternLogin, "")){
            LOGGER.debug("The login data is not valid.");
            throw new ExceptionService("The login data is not valid.");
        }
        try {
            return teacher.isLoginUnique(patternLogin);
        } catch (DaoException e) {
            LOGGER.debug("Service error isLoginUnique "+e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean registerUser(User user) throws ExceptionService {
        if (user==null){
            LOGGER.debug("User entity equals null");
            throw new ExceptionService("User entity equals null");
        }
        try {
            return teacher.registerUser(user);
        } catch (DaoException e) {
            LOGGER.debug("Service error registerUser "+e);
            throw new ExceptionService(e);
        }

    }

    @Override
    public User findById(Long id) throws ExceptionService {
        try {
            return teacher.findEntityById(id);
        } catch (DaoException e) {
            LOGGER.debug("Service error findById "+e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean save(User user) throws ExceptionService {
        if (user==null){
            LOGGER.debug("User entity equals null");
            throw new ExceptionService("User entity equals null");
        }
        try {
            return teacher.create(user);
        } catch (DaoException e) {
            LOGGER.debug("Service error save "+e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public List<User> findAll() throws ExceptionService {
        try {
            return teacher.findAll();
        } catch (DaoException e) {
            LOGGER.debug("Service error findAll "+e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public User findByEntityId(long id) throws ExceptionService {
        try {
            return teacher.findTeacherById(id);
        } catch (DaoException e) {
            LOGGER.debug("Service error findByEntityId "+e);
            throw new ExceptionService(e);
        }
    }
}
