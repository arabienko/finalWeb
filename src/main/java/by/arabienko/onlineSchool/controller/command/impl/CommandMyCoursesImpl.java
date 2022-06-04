package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandResult;
import by.arabienko.onlineSchool.entity.StudentCourse;
import by.arabienko.onlineSchool.entity.TeacherCourse;
import by.arabienko.onlineSchool.enumeration.JspNameParam;
import by.arabienko.onlineSchool.enumeration.Page;
import by.arabienko.onlineSchool.enumeration.SessionAttributes;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.StudentCourseService;
import by.arabienko.onlineSchool.service.impl.StudentCourseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CommandMyCoursesImpl implements CommandAction {
    private static final Logger LOGGER =
            LogManager.getLogger(CommandMyCoursesImpl.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws PersistentException, ExceptionService {
        LOGGER.debug("START MY COURSES");
        StudentCourseService service = new StudentCourseServiceImpl();
        List<StudentCourse> set;
        Long id;
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttributes.ID)!=null) {
            id = (Long) session.getAttribute(SessionAttributes.ID);
            set = service.findEntityByStudentId(id);
        }else {
            request.setAttribute("errorMsg", "Unauthorized user");
            return new CommandResult(Page.ERROR_PAGE, true);
        }
        LOGGER.debug("Was selected all courses for student: " +
                session.getAttribute(SessionAttributes.USER));
        request.setAttribute(JspNameParam.SELECTED,
                session.getAttribute(SessionAttributes.USER));
        request.setAttribute(JspNameParam.STUDENT_COURSES, set);
        return new CommandResult(Page.MY_COURSES_PAGE, true);
    }
}
