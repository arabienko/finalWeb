package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.TeacherSubject;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;

import java.util.List;

public interface TeacherSubjectService extends Service {
    /**
     * Find all TeacherSubjects
     *
     * @return list TeacherSubjects
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<TeacherSubject> findAll() throws ExceptionService, PersistentException;

    /**
     * @param id TeacherSubject
     * @return TeacherSubject
     * @throws ExceptionService
     * @throws PersistentException
     */
    TeacherSubject findEntityById(Long id) throws PersistentException, ExceptionService;

    /**
     * @param t TeacherSubject
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean delete(TeacherSubject t);

    /**
     * @param id TeacherSubject
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean delete(long id);

    /**
     * @param t TeacherSubject
     * @return boolean true if create transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean create(TeacherSubject t) throws ExceptionService, PersistentException;

    /**
     * @param t TeacherSubject
     * @return boolean true if update transaction is good.
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean update(TeacherSubject t) throws ExceptionService, PersistentException;

    /**
     * @param namePattern surname
     * @return list teacher subjects
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<TeacherSubject> findTeacherSubjectBySurnameTeacher(String namePattern) throws ExceptionService, PersistentException;

    /**
     * @param namePattern name subject
     * @return list teacher subjects
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<TeacherSubject> findTeacherSubjectBySubject(String namePattern) throws ExceptionService, PersistentException;

}
