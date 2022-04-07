package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.dao.mysql.TeacherCourseDaoImpl;

public class TestTC {
    public static void main(String[] args) throws DaoException {
        TeacherCourseDaoImpl teacherCourseDao = new TeacherCourseDaoImpl();
        System.out.println(teacherCourseDao.findCourseBySubject("TypeScript"));
    }
}
