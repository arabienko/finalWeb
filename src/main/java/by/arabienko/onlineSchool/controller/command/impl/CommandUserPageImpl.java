package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.command.CommandAccess;
import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandResult;
import by.arabienko.onlineSchool.entity.UserInfo;
import by.arabienko.onlineSchool.enumeration.JspNameParam;
import by.arabienko.onlineSchool.enumeration.Page;
import by.arabienko.onlineSchool.enumeration.SessionAttributes;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.UserInfoService;
import by.arabienko.onlineSchool.service.impl.UserInfoServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandUserPageImpl implements CommandAction {
    private static final Logger LOGGER =
            LogManager.getLogger(CommandUserPageImpl.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws PersistentException, ExceptionService{
        UserInfoService userInfoService = new UserInfoServiceImpl();
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttributes.ROLE).equals(3)
                || session.getAttribute(SessionAttributes.ROLE) ==null) {
            //session.setAttribute(SessionAttributes.USER, SessionAttributes.GUEST);
            // session.setAttribute(SessionAttributes.ROLE, UserRole.GUEST);
            LOGGER.info("Session as a guest...");
            return new CommandResult(Page.LOGIN_PAGE, true);
        } else {
            long id = (long) session.getAttribute(SessionAttributes.ID);
            UserInfo userInfo = userInfoService.findEntityById(id);
            LOGGER.info("User page for - " + userInfo.toString());
            request.setAttribute(JspNameParam.USER_INFO, userInfo);
            return new CommandResult(Page.USER_INFO, true);
        }
    }
}
