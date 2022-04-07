package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.SubjectDao;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.entity.Subject;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.SubjectService;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class SubjectsServiceImpl extends ServiceImpl implements SubjectService {
    private static final Logger LOGGER =
            LogManager.getLogger(SubjectsServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    SubjectDao subjectDAO = daoFactory.getSubjectDAO();

    @Override
    public List<Subject> findAll()
            throws ExceptionService {
        try {
            return subjectDAO.findAll();
        } catch (DaoException e) {
            LOGGER.debug("Service error findAll " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public Subject findById(Long id)
            throws ExceptionService {
        try {
            return subjectDAO.findEntityById(id);
        } catch (DaoException e) {
            LOGGER.debug("Service error findById " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean save(Subject subject)
            throws ExceptionService {
        if (subject==null){
            LOGGER.debug("Subject entity equals null");
            throw new ExceptionService("Subject entity equals null");
        }
        try {
            return subjectDAO.create(subject);
        } catch (DaoException e) {
            LOGGER.debug("Service error save " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public Subject findSubjectByName(String pattern)
            throws ExceptionService {
        if (!DataValidator.isSubjectNameValid(pattern)
                || Objects.equals(pattern, "")){
            LOGGER.debug("Subject name is not valid.");
            throw new ExceptionService("Subject name is not valid.");
        }
        try {
            return subjectDAO.findSubjectByName(pattern);
        } catch (DaoException e) {
            LOGGER.debug("Service error findSubjectByName " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean update(Subject subject)
            throws ExceptionService {
        try {
            return subjectDAO.update(subject);
        } catch (DaoException e) {
            LOGGER.debug("Service error update " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean isNameSubjectUnique(
            String patternNameSubject) throws ExceptionService {
        if (!DataValidator.isNameValid(patternNameSubject)
                || Objects.equals(patternNameSubject, "")){
            LOGGER.debug("Subject name is not valid.");
            throw new ExceptionService("Subject name is not valid.");
        }
        try {
            return subjectDAO.isUnique(patternNameSubject);
        } catch (DaoException e) {
            LOGGER.debug("Service error isNameSubjectUnique " + e);
            throw new ExceptionService(e);
        }
    }
}
