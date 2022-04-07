package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.exception.ExceptionService;

/**
 * The interface Admin service.
 */
public interface AdminService extends Service{
    /**
     * Check admin by login password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ExceptionService the service exception
     */
    boolean checkAdminByLoginPassword(String login, String password) throws ExceptionService;
}
