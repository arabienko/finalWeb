package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.entity.User;
import by.arabienko.onlineSchool.entity.UserInfo;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.UserInfoService;
import by.arabienko.onlineSchool.service.UserService;
import by.arabienko.onlineSchool.service.impl.UserInfoServiceImpl;
import by.arabienko.onlineSchool.service.impl.UserServiceImpl;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import static by.arabienko.onlineSchool.controller.Constants.*;

public class CommandCreateUser implements CommandAction {
    private static final Logger LOGGER =
            LogManager.getLogger(CommandCreateUser.class);
    private File file;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws PersistentException, ExceptionService {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf8");
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
                    pathFile = UPLOAD_DIRECTORY + "/" +fileName;
                } else {
                    pathFile = UPLOAD_DIRECTORY + "/" + "img.png";
                }
            }
        } catch (IOException | ServletException e) {
            LOGGER.debug("Error write file - {}", filePath);
        }
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String optradio = request.getParameter("optradio");
        int role;
        if (optradio.equals("option1")) {
            role = 1;
        } else {
            role = 2;
        }
        User user = new User(login, password, role);
        System.out.println(user);
        UserInfo.UserBuilder userBuilder = new UserInfo.UserBuilder();
        UserInfo userInfo = userBuilder.build();
        userBuilder.setUser(user).setName(username).
                setPhone(phone).setSurname(surname).setPathImage(pathFile);
        System.out.println("userInfo " + userInfo);
        UserService userService = new UserServiceImpl();
        UserInfoService userInfoService = new UserInfoServiceImpl();
        if (userService.save(user)) {
            userInfoService.create(userInfo);
            request.setAttribute("userInfo", userInfo);
        } else {
            request.setAttribute("userInfo", userInfo);
        }

    }

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

}
