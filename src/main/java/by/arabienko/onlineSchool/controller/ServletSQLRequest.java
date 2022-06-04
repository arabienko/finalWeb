package by.arabienko.onlineSchool.controller;

import by.arabienko.onlineSchool.controller.command.ActionFactory;
import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandResult;
import by.arabienko.onlineSchool.enumeration.JspNameParam;
import by.arabienko.onlineSchool.enumeration.Page;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.arabienko.onlineSchool.controller.Constants.*;


@WebServlet(name = "my", urlPatterns = {"/my"})
@MultipartConfig(
        fileSizeThreshold = MEMORY_THRESHOLD,
        maxFileSize = MAX_FILE_SIZE,
        maxRequestSize = MAX_REQUEST_SIZE)
public class ServletSQLRequest extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER =
            LogManager.getLogger(ServletSQLRequest.class);

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        System.out.println("request.getParameter(\"page\") "+request.getParameter("page"));
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Start servlet (doPost).");
        System.out.println("request.getParameter(\"page\") "+request.getParameter("page"));
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        CommandAction commandAction = ActionFactory.defineCommand(request, response);
        CommandResult commandResult = null;
        try {
            commandResult = commandAction.execute(request, response);
        } catch (PersistentException | ExceptionService e) {
            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher(Page.WELCOME_PAGE);
            dispatcher.forward(request, response);
        }
        String page = "";
        assert commandResult!=null;
        if (commandResult.getPage()!=null) {
            if (commandResult.isRedirect()) {
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

                page = commandResult.getPage();
                LOGGER.debug("Output result page: " + page);
            }
        }
        process(request, response, page);
        LOGGER.debug("finish servlet...");

    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response, String page)
            throws ServletException, IOException {
        request.getRequestDispatcher(page).
                forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}