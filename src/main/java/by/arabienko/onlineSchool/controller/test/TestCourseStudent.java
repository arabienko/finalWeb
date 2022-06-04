package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.StudentCourseService;
import by.arabienko.onlineSchool.service.impl.StudentCourseServiceImpl;

public class TestCourseStudent {
    public static void main(String[] args) throws ExceptionService, PersistentException {
        StudentCourseService service = new StudentCourseServiceImpl();
    }
}
