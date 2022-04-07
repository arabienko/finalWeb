package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.dao.mysql.TeacherSubjectDaoImpl;

public class TestTeacherSubject {
    public static void main(String[] args) throws DaoException {
        TeacherSubjectDaoImpl teacherSubjectDao = new TeacherSubjectDaoImpl();
        System.out.println(teacherSubjectDao.findTeacherSubjectBySubject("java"));
    }
}
