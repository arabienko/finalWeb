package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.service.xmlparser.AbstractStudentCoursesBuilder;
import by.arabienko.onlineSchool.service.xmlparser.StudentCoursesBuilderFactory;

public class ParsTest {
    public static void main(String[] args) {
        AbstractStudentCoursesBuilder builder =
                StudentCoursesBuilderFactory.createStudentBuilder("dom");
        //TODO: find how
        builder.buildStudentCurses(
                "D:\\JavaProg\\task07xml\\src\\main\\resources\\date\\curse.xml");
        System.out.println(builder.getStudentCourses());
    }
}
