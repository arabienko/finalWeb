package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.ServletSQLRequest;
import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandResult;
import by.arabienko.onlineSchool.dao.StudentCourseDao;
import by.arabienko.onlineSchool.dao.mysql.StudentCourseDaoImpl;
import by.arabienko.onlineSchool.entity.StudentCourse;
import by.arabienko.onlineSchool.enumeration.JspCommand;
import by.arabienko.onlineSchool.enumeration.JspNameParam;
import by.arabienko.onlineSchool.enumeration.Page;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.StudentCourseService;
import by.arabienko.onlineSchool.service.impl.StudentCourseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Select all student courses command
 */
public class CommandAllStudentCourseImpl implements CommandAction {
    private static final Logger LOGGER =
            LogManager.getLogger(CommandAllStudentCourseImpl.class);
    @Override
    public CommandResult execute(
            HttpServletRequest request, HttpServletResponse response)
            throws PersistentException, ExceptionService {
        StudentCourseService service =
                new StudentCourseServiceImpl();

        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(
                    request.getParameter("page"));
        StudentCourseDaoImpl dao = new StudentCourseDaoImpl();
        List<StudentCourse> set;
        set = service.findAll( (page - 1) * recordsPerPage,
                recordsPerPage);
        LOGGER.debug("Was selected all courses. "+JspNameParam.STUDENT_COURSES);
        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int)Math.ceil(noOfRecords * 1.0
                / recordsPerPage);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute(JspNameParam.SELECTED, JspCommand.COURSES);
        request.setAttribute(JspNameParam.STUDENT_COURSES, set);
        return new CommandResult(Page.STUDENT_COURSES,true);
    }
}
