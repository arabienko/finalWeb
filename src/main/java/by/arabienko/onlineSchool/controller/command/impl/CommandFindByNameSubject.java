package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.entity.TeacherCourse;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.TeacherCourseService;
import by.arabienko.onlineSchool.service.impl.TeacherCourseServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandFindByNameSubject implements CommandAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        TeacherCourseService service =
                new TeacherCourseServiceImpl();
        List<TeacherCourse> set = null;
        String name = request.getParameter("find");
        try {
            set = service.findCourseBySubject(name);
        } catch (ExceptionService | PersistentException e) {
            e.printStackTrace();
        }
            request.setAttribute("name", name);
        request.setAttribute("parse", name);
        request.setAttribute("teacherCourse", set);
    }
}
