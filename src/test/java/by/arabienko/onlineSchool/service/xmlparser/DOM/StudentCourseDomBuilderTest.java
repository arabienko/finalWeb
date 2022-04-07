package by.arabienko.onlineSchool.service.xmlparser.DOM;

import by.arabienko.onlineSchool.entity.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class StudentCourseDomBuilderTest {
    StudentCourseDomBuilder courseDomBuilder =
            new StudentCourseDomBuilder();

    @DataProvider(name = "parsing_DOM")
    public Object[][] dateForParsing() {
        UserInfo.UserBuilder builder =
                new UserInfo.UserBuilder();
        UserInfo userStudent = builder.build();
        builder.setId(13).setPhone("+375295885225").
                setSurname("Kamarova").setName("Sanya").
                setPathImage("url13");
        UserInfo.UserBuilder builderTeacher =
                new UserInfo.UserBuilder();
        UserInfo userTeacher = builderTeacher.build();
        builderTeacher.setId(13).setPhone("+375335456565").
                setSurname("Lagunou").setName("Artur").
                setPathImage("url1");
        Subject subject = new Subject(13, "Basic","");
        TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder =
                new TeacherSubject.TeacherSubjectBuilder();
        TeacherSubject teacherSubject =
                teacherSubjectBuilder.build();
        teacherSubject.setId(13);
        teacherSubjectBuilder.setSubject(subject).
                setUserInfo(userTeacher);
        TeacherCourse.TeacherCourseBuilder teacherCourseBuilder =
                new TeacherCourse.TeacherCourseBuilder();
        TeacherCourse teacherCourse =
                teacherCourseBuilder.build();
        teacherCourse.setId(13);
        teacherCourseBuilder.setTeacherSubject(teacherSubject).
                setEndDate("2023-04-01").setStartDate("2023-01-10");
        StudentCourse.StudentCourseBuilder studentCourseBuilder =
                new StudentCourse.StudentCourseBuilder();
        StudentCourse studentCourse = studentCourseBuilder.build();
        studentCourse.setId(13);
        studentCourseBuilder.setTeacherCourse(teacherCourse).
                setUserInfo(userStudent).setStatus("preparing");
        Set<StudentCourse> actual = new HashSet<>();
        actual.add(studentCourse);
        Set<StudentCourse> actual2 = new HashSet<>();
        actual2.add(studentCourse);
        actual2.add(studentCourse);
        return new Object[][]{
                new Object[]{"C:\\Users\\User\\Desktop\\java39ST\\task07xml\\src\\test\\resources\\test1", actual},
                new Object[]{"C:\\Users\\User\\Desktop\\java39ST\\task07xml\\src\\test\\resources\\test2", actual2},
                new Object[]{"", actual},
                new Object[]{"err.xml", actual}
        };
    }

    @Test(description = "positive scenario",
            dataProvider = "parsing_DOM")
    public void testBuildStudentCurses(String file,
                                       Set<StudentCourse> actual) {
        courseDomBuilder.buildStudentCurses(file);
        Set<StudentCourse> expected =
                courseDomBuilder.getStudentCourses();
        assertTrue(expected.equals(actual));
    }
}