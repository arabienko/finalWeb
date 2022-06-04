package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandResult;
import by.arabienko.onlineSchool.enumeration.CookieConst;
import by.arabienko.onlineSchool.enumeration.Page;
import by.arabienko.onlineSchool.enumeration.SessionAttributes;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * LogOut command:
 * deleted cookies and remove session.
 */
public class CommandLogoutImpl implements CommandAction {
    private static final Logger LOGGER =
            LogManager.getLogger(CommandLogoutImpl.class);
    @Override
    public CommandResult execute(
            HttpServletRequest request, HttpServletResponse response)
            throws PersistentException, ExceptionService {
        HttpSession session = request.getSession(false);
        Long id = (Long) session.getAttribute(SessionAttributes.ID);
        String login = (String) session.getAttribute(SessionAttributes.USER);
        session.removeAttribute(SessionAttributes.ROLE);
        session.removeAttribute(SessionAttributes.USER);
        session.removeAttribute(SessionAttributes.PROFILE_IMAGE);
        session.removeAttribute(SessionAttributes.ID);
        LOGGER.info("User with id = " + id
                + " and login = " + login + " session deleted");
        clearCookie(CookieConst.USER_LOGIN, request, response);
        session.invalidate();
        return new CommandResult(Page.LOGIN_PAGE, true);
    }

    /**
     * Clear cookie
     * @param userLogin login
     * @param request
     * @param response
     */
    private void clearCookie(String userLogin,
                             HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(userLogin)){
                cookie.setPath("/");
                cookie.setMaxAge(0);
                cookie.setValue(null);
                response.addCookie(cookie);
                break;
            }
        }
    }
}
