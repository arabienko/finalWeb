package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.dao.UserDao;
import by.arabienko.onlineSchool.dao.mysql.UserDaoImpl;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;

public class Test {
    public static void main(String[] args) throws DaoException, ExceptionService {
        UserDao userDao = new UserDaoImpl();
    }
}
