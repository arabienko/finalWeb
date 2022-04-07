package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.entity.StudentCourse;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.StudentCourseService;
import by.arabienko.onlineSchool.service.impl.StudentCourseServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandAllStudentCourse implements CommandAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        StudentCourseService service =
                new StudentCourseServiceImpl();
        List<StudentCourse> set = null;
        try {
            set = service.findAll();
        } catch (ExceptionService e) {
            e.printStackTrace();
        }
        request.setAttribute("parse", "commandSCAll");
        request.setAttribute("courseStudent", set);
    }
}
