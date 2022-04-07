package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.entity.StudentCourse;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.StudentCourseService;
import by.arabienko.onlineSchool.service.impl.StudentCourseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static by.arabienko.onlineSchool.controller.Constants.*;


@WebServlet(urlPatterns = {"/myy"})
@MultipartConfig(
        fileSizeThreshold = MEMORY_THRESHOLD,
        maxFileSize = MAX_FILE_SIZE,
        maxRequestSize = MAX_REQUEST_SIZE)
public class ServletParseXmlTEST extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER =
            LogManager.getLogger(ServletParseXmlTEST.class);

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) {

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.debug("Start servlet.");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf8");
        StudentCourseService service = new StudentCourseServiceImpl();
        List<StudentCourse> courseStudent = null;
        try {
            courseStudent = service.findAll();
        } catch (ExceptionService e) {
            e.printStackTrace();
        }
        List<City> cities = new ArrayList<>();
        PrintWriter out= response.getWriter();
        out.println(request.getMethod());
        out.println(request.getRequestURL());
        out.println(request.getProtocol());
        out.println(request.getRemoteAddr());
        out.println(request.getContextPath());
        out.println(request.getScheme());
        cities.add(new City(1L, "Bratislava", 432000));
        cities.add(new City(2L, "Budapest", 1759000));
        cities.add(new City(3L, "Prague", 1280000));
        cities.add(new City(4L, "Warsaw", 1748000));
        cities.add(new City(5L, "Los Angeles", 3971000));
        cities.add(new City(6L, "New York", 8550000));
        cities.add(new City(7L, "Edinburgh", 464000));
        cities.add(new City(8L, "Berlin", 3671000));
        String parse = request.getParameter("selectParse");
        request.setAttribute("parse", parse);
        System.out.println(parse);
        request.setAttribute("cities", cities);
       // request.setAttribute("parse", cities.get(0).getId());
        request.getRequestDispatcher("/outResult.jsp").
                forward(request, response);
    }
}