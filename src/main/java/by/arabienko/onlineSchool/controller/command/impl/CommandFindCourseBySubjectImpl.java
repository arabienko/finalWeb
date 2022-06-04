package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandResult;
import by.arabienko.onlineSchool.entity.StudentCourse;
import by.arabienko.onlineSchool.entity.TeacherCourse;
import by.arabienko.onlineSchool.enumeration.JspNameParam;
import by.arabienko.onlineSchool.enumeration.Page;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.StudentCourseService;
import by.arabienko.onlineSchool.service.TeacherCourseService;
import by.arabienko.onlineSchool.service.impl.StudentCourseServiceImpl;
import by.arabienko.onlineSchool.service.impl.TeacherCourseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Select special courses by the name subject.
 */
public class CommandFindCourseBySubjectImpl implements CommandAction {
    private static final Logger LOGGER =
            LogManager.getLogger(CommandFindCourseBySubjectImpl.class);
    @Override
    public CommandResult execute(
            HttpServletRequest request, HttpServletResponse response)
            throws PersistentException, ExceptionService {
        StudentCourseService service =
                new StudentCourseServiceImpl();
        List<StudentCourse> set;
        String name = request.getParameter(JspNameParam.FIND);
            set = service.findStudentCourseBySubject(name);
            LOGGER.debug("Was selected courses by name subject: "+name);
        request.setAttribute(JspNameParam.SELECTED, name);
        request.setAttribute(JspNameParam.STUDENT_COURSES, set);
        return new CommandResult(Page.STUDENT_COURSES,true);
    }
}
