package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandResult;
import by.arabienko.onlineSchool.dao.mysql.StudentCourseDaoImpl;
import by.arabienko.onlineSchool.dao.mysql.TeacherCourseDaoImpl;
import by.arabienko.onlineSchool.entity.TeacherCourse;
import by.arabienko.onlineSchool.enumeration.JspNameParam;
import by.arabienko.onlineSchool.enumeration.Page;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.TeacherCourseService;
import by.arabienko.onlineSchool.service.impl.TeacherCourseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Select all courses in the OnlineSchool by name subject.
 */
public class CommandFindTeacherCoursesImpl implements CommandAction {
    private static final Logger LOGGER =
            LogManager.getLogger(CommandFindTeacherCoursesImpl.class);

    @Override
    public CommandResult execute(
            HttpServletRequest request, HttpServletResponse response)
            throws PersistentException, ExceptionService {
        int page = 1;
        int recordsPerPage = 3;
        if (request.getParameter("page")!=null) {
            page = Integer.parseInt(
                    request.getParameter("page"));
            System.out.println("page "+page);
        }
        TeacherCourseService service =
                new TeacherCourseServiceImpl();
        List<TeacherCourse> set;
        String name = request.getParameter(JspNameParam.FIND);
        set = service.findAll((page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = service.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0
                / recordsPerPage);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        LOGGER.debug("Was selected all courses by subject: " + name);
        request.setAttribute(JspNameParam.SELECTED, name);
        request.setAttribute(JspNameParam.TEACHER_COURSES, set);
        return new CommandResult(Page.STUDENT_ALL_COURSES, true);
    }
}
