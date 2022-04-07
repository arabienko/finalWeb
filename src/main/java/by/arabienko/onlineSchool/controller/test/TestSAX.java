package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.service.xmlparser.SAX.StudentCoursesSAXBuilder;

public class TestSAX {
    public static void main(String[] args){
        StudentCoursesSAXBuilder saxBuilder = new StudentCoursesSAXBuilder();
        saxBuilder.buildStudentCurses("date/test1.xml");
        System.out.println(saxBuilder.getStudentCourses());
    }
}
