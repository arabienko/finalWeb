package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.dao.mysql.StudentCourseDaoImpl;

import java.sql.SQLException;

public class TestSC {
    public static void main(String[] args) throws DaoException, SQLException {
        StudentCourseDaoImpl studentCourseDao = new StudentCourseDaoImpl();
        System.out.println(studentCourseDao.findAllEntity());
    }
}
