package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.StudentCourse;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;

import java.util.List;

/**
 * Interface student course
 */
public interface StudentCourseService extends Service{
    /**
     * Find all StudentCourses
     *
     * @return list StudentCourses
     * @throws ExceptionService
     */
    List<StudentCourse> findAll() throws ExceptionService;

    /**
     * @param id StudentCourse
     * @return StudentCourse
     * @throws ExceptionService
     */
    StudentCourse findEntityById(Long id) throws ExceptionService, PersistentException;

    /**
     * @param t StudentCourse
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     */
    boolean delete(StudentCourse t);

    /**
     * @param id StudentCourse
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     */
    boolean delete(long id);

    /**
     * @param t StudentCourse
     * @return boolean true if create transaction is good.
     * @throws ExceptionService
     */
    boolean create(StudentCourse t) throws ExceptionService;

    /**
     * @param t StudentCourse
     * @return boolean true if update transaction is good.
     * @throws ExceptionService
     */
    boolean update(StudentCourse t) throws ExceptionService;

    /**
     * @param namePattern name subject
     * @return list courses for student
     */
    List<StudentCourse> findStudentCourseBySubject(String namePattern) throws ExceptionService;

    /**
     * @param patternCourse id course
     * @param patternStudent id student
     * @return boolean true if student course is unique.
     */
    boolean isUnique(long patternCourse, long patternStudent) throws ExceptionService;
}
