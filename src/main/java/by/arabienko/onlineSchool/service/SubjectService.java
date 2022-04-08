package by.arabienko.onlineSchool.service;

import by.arabienko.onlineSchool.entity.Subject;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;

import java.util.List;

/**
 * The interface Subject service.
 */
public interface SubjectService extends Service {
    /**
     * Find all subjects list.
     *
     * @return the list
     * @throws ExceptionService
     * @throws PersistentException
     */
    List<Subject> findAll()
            throws ExceptionService, PersistentException;

    /**
     * Find by id.
     *
     * @param id the id
     * @return the subject.
     * @throws ExceptionService
     * @throws PersistentException
     */
    Subject findById(Long id)
            throws ExceptionService, PersistentException;

    /**
     * Save subject.
     *
     * @param subject
     * @return boolean
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean save(Subject subject)
            throws ExceptionService, PersistentException;

    /**
     * Check user by login password optional.
     *
     * @param pattern the name
     * @return Subject.
     * @throws ExceptionService
     * @throws PersistentException
     */
    Subject findSubjectByName(String pattern)
            throws ExceptionService, PersistentException;

    /**
     * Update subject.
     *
     * @param subject
     * @return boolean
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean update(Subject subject)
            throws ExceptionService, PersistentException;

    /**
     * Is login unique boolean.
     *
     * @param patternNameSubject the pattern name
     * @return the boolean
     * @throws ExceptionService
     * @throws PersistentException
     */
    boolean isNameSubjectUnique
    (String patternNameSubject)
            throws ExceptionService, PersistentException;
}