package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.User;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;

import java.util.List;

/**
 * The interface Client service.
 */
public interface UserService extends Service {

    /**
     * Is login unique boolean.
     *
     * @param patternLogin the pattern login
     * @return the boolean
     * @throws ExceptionService the service exception
     */
    boolean isLoginUnique(String patternLogin) throws ExceptionService, PersistentException;

    /**
     * Find by id.
     *
     * @param id the id
     * @return the user
     * @throws ExceptionService the service exception
     */
    User findById(Long id) throws ExceptionService, PersistentException;


    /**
     * Save long.
     *
     * @param user the user
     * @return boolean
     * @throws ExceptionService the service exception
     */
    boolean save(User user) throws ExceptionService, PersistentException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ExceptionService the service exception
     */
    List<User> findAll() throws ExceptionService, PersistentException;

    /**
     * @param patternName
     * @return user
     * @throws ExceptionService
     * @throws PersistentException
     */
    User findUserByLogin(String patternName) throws ExceptionService, PersistentException;

    /**
     * @param login
     * @param patternName
     * @return user
     * @throws ExceptionService
     * @throws PersistentException
     */
    User findEntityByParam(String login, String patternName) throws ExceptionService, PersistentException;

    /**
     * @param id
     * @return student
     * @throws ExceptionService
     * @throws PersistentException
     */
    User findStudentById(final Long id) throws ExceptionService, PersistentException;

    /**
     * @param id
     * @return teacher
     * @throws ExceptionService
     * @throws PersistentException
     */
    User findTeacherById(final Long id) throws ExceptionService, PersistentException;

    /**
     * @return admin entity
     * @throws ExceptionService
     * @throws PersistentException
     */
    User findAdmin() throws PersistentException, ExceptionService;
}
