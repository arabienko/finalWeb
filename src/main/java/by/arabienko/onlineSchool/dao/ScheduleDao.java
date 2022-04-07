package by.arabienko.onlineSchool.dao;

import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.entity.Schedule;

import java.util.List;

/**
 * Interface schedule
 */
public interface ScheduleDao extends Dao<Long, Schedule> {

    /**
     * @param namePattern id student
     * @return schedule
     * @throws DaoException
     */
    List<Schedule> findScheduleByStudent(long namePattern) throws DaoException;

    /**
     * @param patternCourse id course
     * @param patternDate date schedule
     * @return boolean true if schedule is Unique.
     * @throws DaoException
     */
    boolean isUnique(long patternCourse, String patternDate) throws DaoException;

    /**
     * @param id teacher
     * @return schedule
     * @throws DaoException
     */
    List<Schedule> findScheduleByTeacher(long id)
            throws DaoException;
}
