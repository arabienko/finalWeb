package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.User;
import by.arabienko.onlineSchool.exception.ExceptionService;

import java.util.List;
import java.util.Optional;

/**
 * The interface Client service.
 */
public interface UserService extends Service{
    /**
     * Check user by login password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ExceptionService the service exception
     */
    Optional<User> checkUserByLoginPassword(String login, String password) throws ExceptionService;

    /**
     * Is login unique boolean.
     *
     * @param patternLogin the pattern login
     * @return the boolean
     * @throws ExceptionService the service exception
     */
    boolean isLoginUnique(String patternLogin) throws ExceptionService;

    /**
     * Register user boolean.
     *
     * @param user the user.
     * @return the boolean
     * @throws ExceptionService the service exception
     */
    boolean registerUser(User user) throws ExceptionService;

    /**
     * Find by id.
     *
     * @param id the id
     * @return the user
     * @throws ExceptionService the service exception
     */
    User findById(Long id) throws ExceptionService;


    /**
     * Save long.
     *
     * @param user the user
     * @return boolean
     * @throws ExceptionService the service exception
     */
    boolean save(User user) throws ExceptionService;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ExceptionService the service exception
     */
    List<User> findAll() throws ExceptionService;

    /**
     * Find entity by id.
     *
     * @param id the id
     * @return the optional
     * @throws ExceptionService the service exception
     */
    User findByEntityId(long id) throws ExceptionService;
}