package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.UserDao;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.entity.User;
import by.arabienko.onlineSchool.service.AdminService;
import by.arabienko.onlineSchool.valid.DataValidator;
import by.arabienko.onlineSchool.exception.ExceptionService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * The type Admin service.
 */
public class AdminServiceImpl implements AdminService {
    private static final Logger LOGGER =
            LogManager.getLogger(AdminServiceImpl.class);
    DAOFactory daoFactory = DAOFactory.getInstance();
    UserDao admin = daoFactory.getUserDAO();

    @Override
    public boolean checkAdminByLoginPassword(
            String login, String password) throws ExceptionService {
        if (!DataValidator.isLoginValid(login)
                || !DataValidator.isPasswordValid(password)
                || Objects.equals(login, "")
                || password.equals("")){
            LOGGER.debug("The data is not valid.");
            throw new ExceptionService("The data is not valid.");
        }
        User userAdmin;
        try {
            userAdmin = admin.findAdmin();
            if (!userAdmin.getPassword().equals(DigestUtils.sha512Hex(password))
                    || !userAdmin.getLogin().equals(login)) {
                LOGGER.debug("User is not admin");
                return false;
            }
            return true;
        } catch (DaoException e) {
            LOGGER.debug("Service error checkAdminByLoginPassword" + e);
            throw new ExceptionService(e);
        }
    }
}
