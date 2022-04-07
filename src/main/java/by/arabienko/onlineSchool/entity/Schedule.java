package by.arabienko.onlineSchool.entity;

public class Schedule extends Entity{
    private String dateTime;
    private TeacherCourse teacherCourse;

    public Schedule() {
    }

    private Schedule(ScheduleBuilder builder) {
        this.dateTime = builder.schedule.dateTime;
        this.teacherCourse = builder.schedule.teacherCourse;
    }

    public String getDateTime() {
        return dateTime;
    }

    public TeacherCourse getTeacherCourse() {
        return teacherCourse;
    }

    @Override
    public String toString() {
        return "ScheduleSubjects{" +
                "ID='" + this.getId() + '\'' +
                ", " + this.getTeacherCourse() + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }

    public static class ScheduleBuilder {
        private Schedule schedule;

        public ScheduleBuilder() {
            schedule = new Schedule();
        }

        public ScheduleBuilder setDateTime(String dateTime) {
            schedule.dateTime = dateTime;
            return this;
        }

        public ScheduleBuilder setTeacherCourse(TeacherCourse teacherCourse) {
            schedule.teacherCourse = teacherCourse;
            return this;
        }

        //Return the finally consrcuted TeacherSubject object
        public Schedule build() {
            return schedule;
        }
    }
}
