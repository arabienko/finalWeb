package by.arabienko.onlineSchool.dao;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.entity.StudentCourse;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface student course
 */
public interface StudentCourseDao extends Dao<Long, StudentCourse> {

    /**
     * @param namePattern name subject
     * @return list courses for student
     * @throws DaoException
     */
    List<StudentCourse> findStudentCourseBySubject(String namePattern) throws DaoException;

    /**
     * @param patternCourse id course
     * @param patternStudent id student
     * @return boolean true if student course is unique.
     * @throws DaoException
     * @throws SQLException
     */
    boolean isUnique(long patternCourse, long patternStudent) throws DaoException, SQLException;

}
