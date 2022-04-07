package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.Schedule;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.xmlparser.ServiceException;

import java.util.List;

/**
 * Interface schedule
 */
public interface ScheduleService extends Service{
    /**
     * Find all Schedules
     *
     * @return list Schedules
     * @throws ServiceException
     */
    List<Schedule> findAll() throws ServiceException, ExceptionService;

    /**
     * @param id Schedule
     * @return Schedule
     * @throws ServiceException
     */
    Schedule findEntityById(Long id) throws ExceptionService;

    /**
     * @param t Schedule
     * @return boolean true if delete transaction is good.
     * @throws ServiceException
     */
    boolean delete(Schedule t);

    /**
     * @param id Schedule
     * @return boolean true if delete transaction is good.
     * @throws ServiceException
     */
    boolean delete(long id);

    /**
     * @param t Schedule
     * @return boolean true if create transaction is good.
     * @throws ServiceException
     */
    boolean create(Schedule t) throws ExceptionService;

    /**
     * @param t Schedule
     * @return boolean true if update transaction is good.
     * @throws ServiceException
     */
    boolean update(Schedule t) throws ExceptionService;

    /**
     * @param namePattern surname student
     * @return list Schedules
     */
    List<Schedule> findScheduleByStudent(long namePattern) throws ExceptionService;

    /**
     * @param patternCourse id course
     * @param patternDate data start course
     * @return boolean true if schedule is Unique.
     * @throws ExceptionService
     */
    boolean isUnique(long patternCourse, String patternDate) throws ExceptionService;

    /**
     * @param id teacher
     * @return schedules
     * @throws ExceptionService
     */
    List<Schedule> findScheduleByTeacher(long id) throws ExceptionService;

}
