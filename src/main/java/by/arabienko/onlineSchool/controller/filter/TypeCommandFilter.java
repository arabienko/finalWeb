package by.arabienko.onlineSchool.controller.filter;

import by.arabienko.onlineSchool.controller.command.CommandAccess;
import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandEnum;
import by.arabienko.onlineSchool.controller.command.impl.CommandLoginImpl;
import by.arabienko.onlineSchool.enumeration.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TypeCommandFilter implements Filter {
    private static final Logger LOGGER =
            LogManager.getLogger(TypeCommandFilter.class);
    private CommandAccess commandAccess = new CommandAccess();

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        LOGGER.info("Start TypeCommandFilter.");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        System.out.println("session "+session.getAttribute(SessionAttributes.ROLE));
        Integer userRole = (Integer) session.getAttribute(SessionAttributes.ROLE);
        System.out.println("userRole "+userRole);
        if(userRole==null){
            userRole = UserRole.GUEST;
        }
        List<CommandAction> commandTypes = commandAccess.getCommandTypesByUser(userRole);
        String action = request.getParameter(JspNameParam.COMMAND);
        CommandAction commandAction;
        if (action!=null) {
            try {
                LOGGER.debug("TypeCommandFilter: command "+action);
                commandAction = CommandEnum.getCurrentCommand(action);
            } catch (IllegalArgumentException exception) {
                LOGGER.warn("Action with incorrect command:" + action);
                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(Page.ERROR_PAGE);
                requestDispatcher.forward(servletRequest, servletResponse);
                return;
            }

            if (commandTypes.contains(commandAction)) {
                LOGGER.debug("TypeCommandFilter: command is commandAccess.");
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                LOGGER.debug("TypeCommandFilter: commandDeny.");
                request.setAttribute(JspNameParam.WRONG_DATA, action);
                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(Page.NO_ACCESS_PAGE);
                requestDispatcher.forward(servletRequest, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
