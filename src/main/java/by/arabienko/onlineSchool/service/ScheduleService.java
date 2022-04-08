package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.Schedule;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.xmlparser.ServiceException;

import java.util.List;

/**
 * Interface schedule
 */
public interface ScheduleService extends Service {
    /**
     * Find all Schedules
     *
     * @return list Schedules
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<Schedule> findAll() throws ServiceException, ExceptionService, PersistentException;

    /**
     * @param id Schedule
     * @return Schedule
     * @throws ExceptionService
     * @throws PersistentException
     */
    Schedule findEntityById(Long id) throws ExceptionService, PersistentException;

    /**
     * @param t Schedule
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean delete(Schedule t);

    /**
     * @param id Schedule
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean delete(long id);

    /**
     * @param t Schedule
     * @return boolean true if create transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean create(Schedule t) throws ExceptionService, PersistentException;

    /**
     * @param t Schedule
     * @return boolean true if update transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean update(Schedule t) throws ExceptionService, PersistentException;

    /**
     * @param namePattern surname student
     * @return list Schedules
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<Schedule> findScheduleByStudent(long namePattern) throws ExceptionService, PersistentException;

    /**
     * @param patternCourse id course
     * @param patternDate   data start course
     * @return boolean true if schedule is Unique.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean isUnique(long patternCourse, String patternDate) throws ExceptionService, PersistentException;

    /**
     * @param id teacher
     * @return schedules
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<Schedule> findScheduleByTeacher(long id) throws ExceptionService, PersistentException;

}
