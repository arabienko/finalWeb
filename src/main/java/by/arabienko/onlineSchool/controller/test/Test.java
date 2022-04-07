package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.dao.UserDao;
import by.arabienko.onlineSchool.dao.mysql.UserDaoImpl;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.impl.AdminServiceImpl;

public class Test {
    public static void main(String[] args) throws DaoException, ExceptionService {
        UserDao userDao = new UserDaoImpl();
        AdminServiceImpl adminService = new AdminServiceImpl();
        System.out.println(adminService.checkAdminByLoginPassword("1", "admin"));
    }
}
