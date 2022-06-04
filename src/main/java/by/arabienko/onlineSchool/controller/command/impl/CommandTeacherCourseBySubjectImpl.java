package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandResult;
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

public class CommandTeacherCourseBySubjectImpl implements CommandAction {
    private static final Logger LOGGER =
            LogManager.getLogger(CommandTeacherCourseBySubjectImpl.class);
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws PersistentException, ExceptionService {
        TeacherCourseService service =
                new TeacherCourseServiceImpl();
        List<TeacherCourse> set;
        String name = request.getParameter(JspNameParam.FIND);
        set = service.findCourseBySubject(name);
        if (set.size()<1){
            request.setAttribute("errorMsg", "no courses by subject: "+name);
            return new CommandResult(Page.ERROR_PAGE, true);
        }
        LOGGER.debug("Was selected all teacher courses by subject: "+name);
        request.setAttribute(JspNameParam.SELECTED, name);
        request.setAttribute(JspNameParam.TEACHER_COURSES, set);
        return new CommandResult(Page.SELECT_COURSES_PAGE,true);
    }
}
