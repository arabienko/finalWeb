package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.dao.mysql.UserInfoDaoImpl;

public class TestUser {
    public static void main(String[] args) throws DaoException {
        UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
        System.out.println(userInfoDao.findUserBySurname("Green"));
    }
}
