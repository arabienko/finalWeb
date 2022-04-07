package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.StudentCourseService;
import by.arabienko.onlineSchool.service.impl.StudentCourseServiceImpl;

public class TestCourseStudent {
    public static void main(String[] args) throws ExceptionService {
        StudentCourseService service = new StudentCourseServiceImpl();
        System.out.println(service.findAll());
    }
}
