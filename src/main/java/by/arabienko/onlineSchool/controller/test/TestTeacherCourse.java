package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.impl.TeacherCourseServiceImpl;

public class TestTeacherCourse {
    public static void main(String[] args) throws ExceptionService, DaoException {
        TeacherCourseServiceImpl courseService = new TeacherCourseServiceImpl();
        System.out.println(courseService.findCourseBySubject("javascript"));

    }
}
