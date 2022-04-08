package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.SubjectDao;
import by.arabienko.onlineSchool.dao.Transaction;
import by.arabienko.onlineSchool.dao.TransactionFactory;
import by.arabienko.onlineSchool.dao.mysql.TransactionFactoryImpl;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.entity.Subject;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.SubjectService;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class SubjectsServiceImpl extends ServiceImpl implements SubjectService {
    private static final Logger LOGGER =
            LogManager.getLogger(SubjectsServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    SubjectDao subjectDAO = daoFactory.getSubjectDAO();
    TransactionFactory factory = TransactionFactoryImpl.getInstance();
    Transaction transaction;

    @Override
    public List<Subject> findAll()
            throws ExceptionService, PersistentException {
        List<Subject> result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(subjectDAO);
            result = subjectDAO.findAll();
            transaction.commit();
        } catch (DaoException | PersistentException | SQLException e) {
            LOGGER.debug("Service error findAll " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public Subject findById(Long id)
            throws ExceptionService, PersistentException {
        Subject result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(subjectDAO);
            result = subjectDAO.findEntityById(id);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findById " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        }
        return result;
    }

    @Override
    public boolean save(Subject subject)
            throws ExceptionService, PersistentException {
        boolean result;
        if (subject==null) {
            LOGGER.debug("Subject entity equals null");
            throw new ExceptionService("Subject entity equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(subjectDAO);
            result = subjectDAO.create(subject);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error save " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        }
        return result;
    }

    @Override
    public Subject findSubjectByName(String pattern)
            throws ExceptionService, PersistentException {
        Subject result;
        if (!DataValidator.isSubjectNameValid(pattern)
                || Objects.equals(pattern, "")) {
            LOGGER.debug("Subject name is not valid.");
            throw new ExceptionService("Subject name is not valid.");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(subjectDAO);
            result = subjectDAO.findSubjectByName(pattern);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findSubjectByName " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public boolean update(Subject subject)
            throws ExceptionService, PersistentException {
        boolean result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(subjectDAO);
            result = subjectDAO.update(subject);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error update " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public boolean isNameSubjectUnique(
            String patternNameSubject) throws ExceptionService, PersistentException {
        boolean result;
        if (!DataValidator.isNameValid(patternNameSubject)
                || Objects.equals(patternNameSubject, "")) {
            LOGGER.debug("Subject name is not valid.");
            throw new ExceptionService("Subject name is not valid.");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(subjectDAO);
            result = subjectDAO.isUnique(patternNameSubject);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error isNameSubjectUnique " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }
}
