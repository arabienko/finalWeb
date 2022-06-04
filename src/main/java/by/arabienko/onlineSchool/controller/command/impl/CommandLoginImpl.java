package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandResult;
import by.arabienko.onlineSchool.entity.User;
import by.arabienko.onlineSchool.entity.UserInfo;
import by.arabienko.onlineSchool.enumeration.CookieConst;
import by.arabienko.onlineSchool.enumeration.JspNameParam;
import by.arabienko.onlineSchool.enumeration.Page;
import by.arabienko.onlineSchool.enumeration.SessionAttributes;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.UserInfoService;
import by.arabienko.onlineSchool.service.UserService;
import by.arabienko.onlineSchool.service.impl.UserInfoServiceImpl;
import by.arabienko.onlineSchool.service.impl.UserServiceImpl;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * LogIn command;
 * remember session
 * and save cookies.
 */
public class CommandLoginImpl implements CommandAction {
    private static final Logger LOGGER =
            LogManager.getLogger(CommandLoginImpl.class);
    private static UserService userService = new UserServiceImpl();
    private static UserInfoService userInfoService = new UserInfoServiceImpl();

    @Override
    public CommandResult execute(
            HttpServletRequest request, HttpServletResponse response)
            throws PersistentException, ExceptionService {
        LOGGER.debug("Start logIn command: " + request.getMethod());
        User user;
        UserInfo userInfo;
        String login = request.getParameter(JspNameParam.LOGIN);
        String password = request.getParameter(JspNameParam.PASSWORD);
        if (login==null) {
            LOGGER.info("invalid login format was received:" + login);
            request.setAttribute(JspNameParam.INVALID_LOGIN, true);
            return new CommandResult(Page.ERROR_PAGE, true);
        }
        if (password==null) {
            LOGGER.info("invalid password format was received:" + password);
            request.setAttribute(JspNameParam.INVALID_PASSWORD, true);
            return new CommandResult(Page.ERROR_PAGE, true);
        }
        boolean isRemember = Boolean.parseBoolean(
                request.getParameter(JspNameParam.REMEMBER_ME));
        HttpSession session = request.getSession();
        LOGGER.info("session= "+session.getId());
        if (userService.findEntityByParam(login, password)!=null) {
            LOGGER.info("login= "+login);
            LOGGER.info("password= "+password);
            user = userService.findUserByLogin(login);
            LOGGER.info("User: " + user);
            userInfo = userInfoService.findEntityById(user.getId());
            session.setAttribute(SessionAttributes.USER, user.getLogin());
            session.setAttribute(SessionAttributes.ID, user.getId());
            session.setAttribute(SessionAttributes.ROLE, user.getRole());
            session.setAttribute(
                    SessionAttributes.PROFILE_IMAGE, userInfo.getPathImage());
            if (isRemember) {
                Cookie cookie = new Cookie(CookieConst.USER_LOGIN, login);
                cookie.setMaxAge(CookieConst.EXPIRY);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            LOGGER.info("client with id = " + user.getId()
                    + " log in. RememberMe = " + isRemember);
            request.setAttribute("login", user.getLogin());
            return new CommandResult(Page.WELCOME_PAGE, true);
        } else {
            request.setAttribute(JspNameParam.WRONG_DATA, CommandLoginImpl.class);
            return new CommandResult(Page.ERROR_PAGE, true);
        }
    }
}
