package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.dao.mysql.ScheduleDaoImpl;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.impl.AdminServiceImpl;

public class TestS {
    public static void main(String[] args) throws DaoException, ExceptionService {
        ScheduleDaoImpl scheduleDao = new ScheduleDaoImpl();
        System.out.println(scheduleDao.findEntityById(5l));
        AdminServiceImpl adminService = new AdminServiceImpl();
        System.out.println(adminService.checkAdminByLoginPassword("admin", "admin"));
    }
}
