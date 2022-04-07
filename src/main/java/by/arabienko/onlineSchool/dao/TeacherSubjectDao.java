package by.arabienko.onlineSchool.dao;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.entity.TeacherSubject;

import java.util.List;

public interface TeacherSubjectDao extends Dao<Long, TeacherSubject> {
    List<TeacherSubject> findTeacherSubjectBySurnameTeacher(String namePattern) throws DaoException;

    List<TeacherSubject> findTeacherSubjectBySubject(String namePattern) throws DaoException;
}
