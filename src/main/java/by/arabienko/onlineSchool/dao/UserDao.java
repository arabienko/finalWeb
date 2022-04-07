package by.arabienko.onlineSchool.dao;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<Long, User> {
    User findUserByLogin(String patternName) throws DaoException;
    Optional<User> findEntityByParam(String login, String patternName) throws DaoException;
    boolean isLoginUnique(String patternLogin) throws DaoException;
    boolean registerUser(User user) throws DaoException;
    User findStudentById(final Long id) throws DaoException;
    User findTeacherById(final Long id) throws DaoException;
    User findAdmin() throws DaoException;
}
