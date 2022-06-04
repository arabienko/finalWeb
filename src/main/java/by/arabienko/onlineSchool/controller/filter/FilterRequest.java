package by.arabienko.onlineSchool.controller.filter;


import by.arabienko.onlineSchool.enumeration.JspNameParam;
import by.arabienko.onlineSchool.enumeration.SessionAttributes;
import by.arabienko.onlineSchool.enumeration.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.relation.Role;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class FilterRequest implements Filter {
    private static final Logger LOGGER =
            LogManager.getLogger(FilterRequest.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        LOGGER.info("Start FilterRequest.");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
       if (session.getAttribute(SessionAttributes.ROLE)==null) {
            session.setAttribute(SessionAttributes.ROLE, UserRole.GUEST);
            LOGGER.info("Start session as a guest...");
        }
        String contextPath = request.getContextPath();
        String action = servletRequest.getParameter(JspNameParam.COMMAND);

        String commandName;
        String uri = request.getRequestURI();
        LOGGER.debug("Start processing Uri from the request - " + uri);
        int beginCommand = contextPath.length();
        int endCommand = uri.lastIndexOf('.');
        if (endCommand >= 0) {
            commandName = uri.substring(beginCommand, endCommand);
        } else {
            commandName = uri.substring(beginCommand);
        }
        if (action==null) {
            action = commandName;
        }
        System.out.println("action  fq "+action);
        servletRequest.setAttribute(JspNameParam.COMMAND, action);
        servletRequest.setAttribute(JspNameParam.USER_ROLE, session.getAttribute(SessionAttributes.ROLE));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
