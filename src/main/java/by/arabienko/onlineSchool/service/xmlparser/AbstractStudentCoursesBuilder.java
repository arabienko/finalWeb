package by.arabienko.onlineSchool.service.xmlparser;

import by.arabienko.onlineSchool.entity.StudentCourse;

import java.util.HashSet;
import java.util.Set;

/**
 * Combine document processing with
 * the help of all three parsers into one application
 * using the FactoryMethod and Builder design patterns.
 * To do this, we build a hierarchy of builder classes in
 * led by the abstract class AbstractStudentsBuilder.
 */
public abstract class AbstractStudentCoursesBuilder {

    protected Set<StudentCourse> studentCourses;
    public AbstractStudentCoursesBuilder() {
        studentCourses = new HashSet<>();
    }
    public AbstractStudentCoursesBuilder(Set<StudentCourse> students) {
        this.studentCourses = students;
    }
    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }
    public abstract void buildStudentCurses(String filename);
}
