package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.dao.mysql.StudentCourseDaoImpl;

public class TestSC {
    public static void main(String[] args) throws DaoException {
        StudentCourseDaoImpl studentCourseDao = new StudentCourseDaoImpl();
        System.out.println(studentCourseDao.findAll());
    }
}
