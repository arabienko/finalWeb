package by.arabienko.onlineSchool.dao;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.entity.UserInfo;

import java.util.List;

public interface UserInfoDao extends Dao<Long, UserInfo> {
    List<UserInfo> findUserBySurname(String patternName) throws DaoException;
    List<UserInfo> findUserTeacher() throws DaoException;
    List<UserInfo> findUserStudent() throws DaoException;
    UserInfo findUserAdmin() throws DaoException;
}
