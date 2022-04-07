package by.arabienko.onlineSchool.dao;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.entity.Subject;

public interface SubjectDao extends Dao<Long, Subject> {
    Subject findSubjectByName(String patternName) throws DaoException;
    boolean isUnique(String pattern) throws DaoException;
}
