package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.UserInfo;
import by.arabienko.onlineSchool.exception.ExceptionService;

import java.util.List;

/**
 * User Info interface.
 */
public interface UserInfoService extends Service{

    /**
     * Find all users
     * @return list info users
     * @throws ExceptionService
     */
    List<UserInfo> findAll() throws ExceptionService;

    /**
     * @param id user
     * @return user info
     * @throws ExceptionService
     */
    UserInfo findEntityById(Long id) throws ExceptionService;

    /**
     * @param t user info
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     */
    boolean delete(UserInfo t) throws ExceptionService;

    /**
     * @param id user
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     */
    boolean delete(long id) throws ExceptionService;

    /**
     * @param t user info
     * @return boolean true if create transaction is good.
     * @throws ExceptionService
     */
    boolean create(UserInfo t) throws ExceptionService;

    /**
     * @param t user info
     * @return boolean true if update transaction is good.
     * @throws ExceptionService
     */
    boolean update(UserInfo t) throws ExceptionService;

    /**
     * @param patternName surname
     * @return user info
     * @throws ExceptionService
     */
    List<UserInfo> findUserBySurname(String patternName) throws ExceptionService;

    /**
     * @return list info users, who is the teacher
     * @throws ExceptionService
     */
    List<UserInfo> findUserTeacher() throws ExceptionService;

    /**
     * @return list info users, who is the student
     * @throws ExceptionService
     */
    List<UserInfo> findUserStudent() throws ExceptionService;

    /**
     * @return info user, who is the student
     * @throws ExceptionService
     */
    UserInfo findUserAdmin() throws ExceptionService;
}
