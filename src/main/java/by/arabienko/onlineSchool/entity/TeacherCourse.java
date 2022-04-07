package by.arabienko.onlineSchool.entity;

import java.util.Objects;

public class TeacherCourse extends Entity {
    private String startDate;
    private String endDate;
    private TeacherSubject teacherSubject;

    public TeacherCourse() {
    }

    private TeacherCourse(TeacherCourseBuilder builder) {
        this.startDate = builder.teacherCourse.startDate;
        this.endDate = builder.teacherCourse.endDate;
        this.teacherSubject = builder.teacherCourse.teacherSubject;
    }
    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public TeacherSubject getTeacherSubject() {
        return teacherSubject;
    }

    @Override
    public String toString() {
        return "TeacherCourse{" +
                "ID= " + this.getId() +
                ", " + this.getTeacherSubject() +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (!(o instanceof TeacherCourse)) return false;
        TeacherCourse that = (TeacherCourse) o;
        return Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(teacherSubject, that.teacherSubject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, teacherSubject);
    }

    public static class TeacherCourseBuilder {
        TeacherCourse teacherCourse;
        public TeacherCourseBuilder() {
            teacherCourse = new TeacherCourse();
        }

        public TeacherCourseBuilder setStartDate(String startDate) {
            teacherCourse.startDate = startDate;
            return this;
        }

        public TeacherCourseBuilder setEndDate(String endDate) {
            teacherCourse.endDate = endDate;
            return this;
        }

        public TeacherCourseBuilder setTeacherSubject(TeacherSubject teacherSubject) {
            teacherCourse.teacherSubject = teacherSubject;
            return this;
        }

        //Return the finally constructed TeacherSubject object
        public TeacherCourse build() {
            return teacherCourse;
        }
    }
}
