package by.arabienko.onlineSchool.dao;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.entity.TeacherCourse;

import java.util.List;

public interface TeacherCourseDao extends Dao<Long, TeacherCourse> {
    List<TeacherCourse> findCourseByStartDate(String namePattern) throws DaoException;
    List<TeacherCourse> findCourseBySubject(String namePattern) throws DaoException;
    int getNoOfRecords();
}
