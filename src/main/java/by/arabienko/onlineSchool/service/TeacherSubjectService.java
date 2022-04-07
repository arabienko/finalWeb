package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.TeacherSubject;
import by.arabienko.onlineSchool.exception.ExceptionService;

import java.util.List;

public interface TeacherSubjectService extends Service{
    /**
     * Find all TeacherSubjects
     *
     * @return list TeacherSubjects
     * @throws ExceptionService
     */
    List<TeacherSubject> findAll() throws ExceptionService;

    /**
     * @param id TeacherSubject
     * @return TeacherSubject
     * @throws ExceptionService
     */
    TeacherSubject findEntityById(Long id);

    /**
     * @param t TeacherSubject
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     */
    boolean delete(TeacherSubject t);

    /**
     * @param id TeacherSubject
     * @return boolean true if delete transaction is good.
     * @throws ExceptionService
     */
    boolean delete(long id);

    /**
     * @param t TeacherSubject
     * @return boolean true if create transaction is good.
     * @throws ExceptionService
     */
    boolean create(TeacherSubject t) throws ExceptionService;

    /**
     * @param t TeacherSubject
     * @return boolean true if update transaction is good.
     * @throws ExceptionService
     */
    boolean update(TeacherSubject t) throws ExceptionService;

    /**
     * @param namePattern surname
     * @return list teacher subjects
     * @throws ExceptionService
     */
    List<TeacherSubject> findTeacherSubjectBySurnameTeacher(String namePattern) throws ExceptionService;

    /**
     * @param namePattern name subject
     * @return list teacher subjects
     * @throws ExceptionService
     */
    List<TeacherSubject> findTeacherSubjectBySubject(String namePattern) throws ExceptionService;

}
