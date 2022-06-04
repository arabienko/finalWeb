package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.TeacherCourse;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;

import java.util.List;

/**
 * Interface teacher course
 */
public interface TeacherCourseService extends Service {
    /**
     * Find all TeacherCourses
     *
     * @return list TeacherCourses
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<TeacherCourse> findAll() throws ExceptionService, PersistentException;

    List<TeacherCourse> findAll(int offset, int noOfRecords) throws ExceptionService, PersistentException;


    /**
     * @param id TeacherCourse
     * @return TeacherCourse
     * @throws ExceptionService
     * @throws PersistentException
     */
    TeacherCourse findEntityById(Long id) throws ExceptionService, PersistentException;

    /**
     * @param t TeacherCourse
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean delete(TeacherCourse t);

    /**
     * @param id TeacherCourse
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean delete(long id);

    /**
     * @param t TeacherCourse
     * @return boolean true if create transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean create(TeacherCourse t) throws ExceptionService, PersistentException;

    /**
     * @param t TeacherCourse
     * @return boolean true if update transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean update(TeacherCourse t) throws ExceptionService, DaoException, PersistentException;

    /**
     * @param namePattern start date of course
     * @return list courses
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<TeacherCourse> findCourseByStartDate(String namePattern) throws ExceptionService, PersistentException;

    /**
     * @param namePattern name subject
     * @return list TeacherCourses
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<TeacherCourse> findCourseBySubject(String namePattern) throws ExceptionService, PersistentException;

    int getNoOfRecords();
}
