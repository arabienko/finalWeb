package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.StudentCourse;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;

import java.util.List;

/**
 * Interface student course
 */
public interface StudentCourseService extends Service {
    /**
     * Find all StudentCourses
     *
     * @return list StudentCourses
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<StudentCourse> findAll(int offset, int noOfRecords) throws ExceptionService, PersistentException;

    /**
     * @param id StudentCourse
     * @return StudentCourse
     * @throws ExceptionService
     * @throws PersistentException
     */
    StudentCourse findEntityById(Long id) throws ExceptionService, PersistentException;
    /**
     * @param id Student
     * @return StudentCourse
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<StudentCourse> findEntityByStudentId(Long id) throws ExceptionService, PersistentException;

    /**
     * @param t StudentCourse
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean delete(StudentCourse t);

    /**
     * @param id StudentCourse
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean delete(long id);

    /**
     * @param t StudentCourse
     * @return boolean true if create transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean create(StudentCourse t) throws ExceptionService, PersistentException;

    /**
     * @param t StudentCourse
     * @return boolean true if update transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean update(StudentCourse t) throws ExceptionService;

    /**
     * @param namePattern name subject
     * @return list courses for student
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<StudentCourse> findStudentCourseBySubject(String namePattern) throws ExceptionService, PersistentException;

    /**
     * @param patternCourse  id course
     * @param patternStudent id student
     * @return boolean true if student course is unique.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean isUnique(long patternCourse, long patternStudent) throws ExceptionService, PersistentException;
}
