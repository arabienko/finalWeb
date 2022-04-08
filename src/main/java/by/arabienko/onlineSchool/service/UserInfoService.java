package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.UserInfo;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;

import java.util.List;

/**
 * User Info interface.
 */
public interface UserInfoService extends Service {

    /**
     * Find all users
     *
     * @return list info users
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<UserInfo> findAll() throws ExceptionService, PersistentException;

    /**
     * @param id user
     * @return user info
     * @throws ExceptionService
     * @throws PersistentException
     */
    UserInfo findEntityById(Long id) throws ExceptionService, PersistentException;

    /**
     * @param t user info
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean delete(UserInfo t) throws ExceptionService, PersistentException;

    /**
     * @param id user
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean delete(long id) throws ExceptionService, PersistentException;

    /**
     * @param t user info
     * @return boolean true if create transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean create(UserInfo t) throws ExceptionService, PersistentException;

    /**
     * @param t user info
     * @return boolean true if update transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean update(UserInfo t) throws ExceptionService, PersistentException;

    /**
     * @param patternName surname
     * @return user info
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<UserInfo> findUserBySurname(String patternName) throws ExceptionService, PersistentException;

    /**
     * @return list info users, who is the teacher
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<UserInfo> findUserTeacher() throws ExceptionService, PersistentException;

    /**
     * @return list info users, who is the student
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<UserInfo> findUserStudent() throws ExceptionService, PersistentException;

    /**
     * @return info user, who is the student
     * @throws ExceptionService
     * @throws PersistentException
     */
    UserInfo findUserAdmin() throws ExceptionService, PersistentException;
}
