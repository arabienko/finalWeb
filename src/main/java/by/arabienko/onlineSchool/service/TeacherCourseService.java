package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.TeacherCourse;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;

import java.util.List;

/**
 * Interface teacher course
 */
public interface TeacherCourseService extends Service{
    /**
     * Find all TeacherCourses
     *
     * @return list TeacherCourses
     * @throws ExceptionService
     */
    List<TeacherCourse> findAll() throws ExceptionService;

    /**
     * @param id TeacherCourse
     * @return TeacherCourse
     * @throws ExceptionService
     */
    TeacherCourse findEntityById(Long id) throws ExceptionService;

    /**
     * @param t TeacherCourse
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     */
    boolean delete(TeacherCourse t);

    /**
     * @param id TeacherCourse
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     */
    boolean delete(long id);

    /**
     * @param t TeacherCourse
     * @return boolean true if create transaction is good.
     * @throws ExceptionService
     */
    boolean create(TeacherCourse t) throws ExceptionService;

    /**
     * @param t TeacherCourse
     * @return boolean true if update transaction is good.
     * @throws ExceptionService
     */
    boolean update(TeacherCourse t) throws ExceptionService, DaoException;

    /**
     * @param namePattern  start date of course
     * @return list courses
     */
    List<TeacherCourse> findCourseByStartDate(String namePattern) throws ExceptionService;

    /**
     * @param namePattern name subject
     * @return list TeacherCourses
     */
    List<TeacherCourse> findCourseBySubject(String namePattern) throws ExceptionService;
}
