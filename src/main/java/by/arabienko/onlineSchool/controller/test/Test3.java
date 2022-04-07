package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.dao.mysql.SubjectDaoImpl;
import by.arabienko.onlineSchool.entity.Subject;

public class Test3 {
    public static void main(String[] args) throws DaoException {
        SubjectDaoImpl subjectDao = new SubjectDaoImpl();
        String pattern = "mathematics";
        Subject subject = subjectDao.findSubjectByName(pattern);
        System.out.println(subject);

    }
}
