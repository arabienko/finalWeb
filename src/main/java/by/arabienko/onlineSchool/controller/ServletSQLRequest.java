package by.arabienko.onlineSchool.controller;

import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandFactory;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.arabienko.onlineSchool.controller.Constants.*;


@WebServlet(urlPatterns = {"/my"})
@MultipartConfig(
        fileSizeThreshold = MEMORY_THRESHOLD,
        maxFileSize = MAX_FILE_SIZE,
        maxRequestSize = MAX_REQUEST_SIZE)
public class ServletSQLRequest extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER =
            LogManager.getLogger(ServletSQLRequest.class);

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException{
        LOGGER.info("Start servlet.");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        String str = request.getParameter("command");
        CommandFactory commandFactory = CommandFactory.getInstance();
        CommandAction commandAction = commandFactory.getCommand(str);
        try {
            commandAction.execute(request, response);
        } catch (ExceptionService | PersistentException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("jsp/outAccount.jsp").
                forward(request, response);
    }
}