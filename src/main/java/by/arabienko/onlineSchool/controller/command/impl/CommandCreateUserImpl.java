package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandResult;
import by.arabienko.onlineSchool.entity.User;
import by.arabienko.onlineSchool.entity.UserInfo;
import by.arabienko.onlineSchool.enumeration.*;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.UserInfoService;
import by.arabienko.onlineSchool.service.UserService;
import by.arabienko.onlineSchool.service.impl.UserInfoServiceImpl;
import by.arabienko.onlineSchool.service.impl.UserServiceImpl;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

import static by.arabienko.onlineSchool.controller.Constants.*;

/**
 * Creating a user account:
 * save account, user info and image into DB.
 */
public class CommandCreateUserImpl implements CommandAction {
    private static final Logger LOGGER =
            LogManager.getLogger(CommandCreateUserImpl.class);
    private static UserService userService = new UserServiceImpl();
    private static UserInfoService userInfoService = new UserInfoServiceImpl();

    @Override
    public CommandResult execute(
            HttpServletRequest request, HttpServletResponse response)
            throws PersistentException, ExceptionService {
        String filePath = "";
        String fileName;
        DiskFileItemFactory factory =
                new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(
                System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload =
                new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String uploadPath = request.getServletContext().
                getRealPath("")
                + UPLOAD_DIRECTORY;
        LOGGER.debug("Upload path is {}", uploadPath);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String pathFile = "";
        try {
            for (Part part : request.getParts()) {
                fileName = extractFileName(part);
                if (fileName!=null && fileName.length() > 0) {
                    filePath = uploadPath + File.separator + fileName;
                    part.write(filePath);
                    pathFile = UPLOAD_DIRECTORY + "/" + fileName;
                    LOGGER.debug("Path file is {}", pathFile);
                }
            }
        } catch (IOException | ServletException e) {
            LOGGER.debug("Error write file - {}", filePath);
        }
        if (pathFile.equals("")){
            pathFile = "img/img.png";
            LOGGER.debug("Path file by temp is {}", pathFile);
        }
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String optradio = request.getParameter("optradio");
        Integer role;
        if (optradio.equals("TEACHER")) {
            role = UserRole.TEACHER;
        } else {
            role = UserRole.STUDENT;
        }
        User user = new User(login, password, role);
        UserInfo.UserBuilder userBuilder =
                new UserInfo.UserBuilder();
        UserInfo userInfo = userBuilder.build();
        userBuilder.setUser(user).setName(username).
                setPhone(phone).setSurname(surname).setPathImage(pathFile);
        UserInfoService userInfoService = new UserInfoServiceImpl();
        if (userInfoService.create(userInfo)) {
            request.setAttribute("userInfo", userInfo);
        } else {
            return new CommandResult(Page.ERROR_PAGE, true);
        }
        logInCommand(request, response, login, password);
        return new CommandResult(Page.USER_INFO, true);
    }

    /**
     * extract file name from selected file by user.
     * @param part
     * @return
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }

    private void logInCommand( HttpServletRequest request, HttpServletResponse response,
                               String login, String password)
            throws PersistentException, ExceptionService {
        LOGGER.debug("Start logIn command: " + request.getMethod());
        User user;
        UserInfo userInfo;
        if (login==null || !DataValidator.isLoginValid(login)) {
            LOGGER.info("invalid login format was received:" + login);
            request.setAttribute(JspNameParam.INVALID_LOGIN, true);
        }
        if (password==null || !DataValidator.isPasswordValid(password)) {
            LOGGER.info("invalid password format was received:" + password);
            request.setAttribute(JspNameParam.INVALID_PASSWORD, true);
        }
        boolean isRemember = Boolean.parseBoolean(
                request.getParameter(JspNameParam.REMEMBER_ME));
        HttpSession session = request.getSession();
        if (userService.findEntityByParam(login, password)!=null) {
            user = userService.findUserByLogin(login);
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
        }
        request.setAttribute(JspNameParam.WRONG_DATA, CommandLoginImpl.class);
    }
}
