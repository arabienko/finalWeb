package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.service.xmlparser.DOM.StudentCourseDomBuilder;

public class TestDOM {
    public static void main(String[] args) {
        StudentCourseDomBuilder domBuilder = new StudentCourseDomBuilder();
        domBuilder.buildStudentCurses("src/main/resources/date/curse.xml");
        System.out.println(domBuilder.getStudentCourses());
    }
}
