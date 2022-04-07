package by.arabienko.onlineSchool.entity;

import java.util.Objects;

public class StudentCourse extends Entity {
    private String status;
    private TeacherCourse teacherCourse;
    private UserInfo userInfo;

    public StudentCourse() {
    }

    private StudentCourse(StudentCourseBuilder builder) {
        this.status = builder.studentCourse.status;
        this.teacherCourse = builder.studentCourse.teacherCourse;
        this.userInfo = builder.studentCourse.userInfo;
    }

    public String getStatus() {
        return status;
    }

    public TeacherCourse getTeacherCourse() {
        return teacherCourse;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public String toString() {
        return "StudentCurse{" +
                "id=" + this.getId() +
                ", " + this.getTeacherCourse() +
                ", user= " + this.getUserInfo() +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (!(o instanceof StudentCourse)) return false;
        StudentCourse that = (StudentCourse) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(teacherCourse, that.teacherCourse) &&
                Objects.equals(userInfo, that.userInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, teacherCourse, userInfo);
    }

    public static class StudentCourseBuilder {
        StudentCourse studentCourse;

        public StudentCourseBuilder() {
            studentCourse = new StudentCourse();
        }

        public StudentCourseBuilder setStatus(String status) {
            studentCourse.status = status;
            return this;
        }

        public StudentCourseBuilder setTeacherCourse(TeacherCourse teacherCourse) {
            studentCourse.teacherCourse = teacherCourse;
            return this;
        }

        public StudentCourseBuilder setUserInfo(UserInfo userInfo) {
            studentCourse.userInfo = userInfo;
            return this;
        }

        //Return the finally consrcuted TeacherSubject object
        public StudentCourse build() {
            return studentCourse;
        }
    }
}
