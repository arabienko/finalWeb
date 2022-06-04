package by.arabienko.onlineSchool.controller.command;

import by.arabienko.onlineSchool.controller.command.impl.EmptyCommandImpl;
import by.arabienko.onlineSchool.enumeration.JspNameParam;
import by.arabienko.onlineSchool.enumeration.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Action factory.
 */
public class ActionFactory {
    private static Logger LOGGER = LogManager.getLogger(ActionFactory.class);

    /**
     * Define command action command.
     *
     * @param request the request
     * @return the action command
     */
    public static CommandAction defineCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommandAction currentCommand = new EmptyCommandImpl();
        String action = request.getParameter(JspNameParam.COMMAND);
        LOGGER.debug("Command: " + action);
        if (action==null || action.isEmpty()) {
            request.setAttribute("errorMsg", "Command execution error: no command.");
            RequestDispatcher dispatcher =
                    request.getServletContext().getRequestDispatcher(Page.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
        try {
            currentCommand = CommandFactory.getInstance().getCommand(action);
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", true);
            LOGGER.info("Wrong action!");
        }
        return currentCommand;
    }
}
