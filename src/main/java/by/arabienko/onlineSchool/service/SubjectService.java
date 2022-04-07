package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.Subject;
import by.arabienko.onlineSchool.exception.ExceptionService;

import java.util.List;

/**
 * The interface Subject service.
 */
public interface SubjectService extends Service{
    /**
     * Find all subjects list.
     *
     * @return the list
     * @throws ExceptionService the service exception
     */
    List<Subject> findAll()
            throws ExceptionService;

    /**
     * Find by id.
     *
     * @param id the id
     * @return the subject.
     * @throws ExceptionService the service exception
     */
    Subject findById(Long id)
            throws ExceptionService;

    /**
     * Save subject.
     *
     * @param subject
     * @return boolean
     * @throws ExceptionService the service exception
     */
    boolean save(Subject subject)
            throws ExceptionService;

    /**
     * Check user by login password optional.
     *
     * @param pattern the name
     * @return Subject.
     * @throws ExceptionService the service exception
     */
    Subject findSubjectByName(String pattern)
            throws ExceptionService;

    /**
     * Update subject.
     *
     * @param subject
     * @return boolean
     * @throws ExceptionService the service exception
     */
    boolean update(Subject subject)
            throws ExceptionService;

    /**
     * Is login unique boolean.
     *
     * @param patternNameSubject the pattern name
     * @return the boolean
     * @throws ExceptionService the service exception
     */
    boolean isNameSubjectUnique
    (String patternNameSubject)
            throws ExceptionService;
}