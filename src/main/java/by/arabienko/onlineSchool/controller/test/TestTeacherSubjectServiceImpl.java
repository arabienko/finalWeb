package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.impl.TeacherSubjectServiceImpl;
import by.arabienko.onlineSchool.service.xmlparser.ServiceException;

public class TestTeacherSubjectServiceImpl {
    public static void main(String[] args) throws ServiceException, ExceptionService {
        TeacherSubjectServiceImpl teacherSubjectService = new TeacherSubjectServiceImpl();
        System.out.println(teacherSubjectService.findTeacherSubjectBySubject("c#"));
    }
}
