package by.arabienko.onlineSchool.dao;

import by.arabienko.onlineSchool.entity.User;
import by.arabienko.onlineSchool.exception.DaoException;

public interface UserDao extends Dao<Long, User> {
    User findUserByLogin(String patternName) throws DaoException;
    User findEntityByParam(String login, String patternName) throws DaoException;
    boolean isLoginUnique(String patternLogin) throws DaoException;
    User findStudentById(final Long id) throws DaoException;
    User findTeacherById(final Long id) throws DaoException;
    User findAdmin() throws DaoException;
}
