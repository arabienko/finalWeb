package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.*;
import by.arabienko.onlineSchool.dao.mysql.TransactionFactoryImpl;
import by.arabienko.onlineSchool.entity.TeacherSubject;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.TeacherSubjectService;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class TeacherSubjectServiceImpl extends ServiceImpl
        implements TeacherSubjectService {
    private static final Logger LOGGER =
            LogManager.getLogger(TeacherSubjectServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    TeacherSubjectDao teacherSubjectDao =
            daoFactory.getTeacherSubjectDAO();
    TransactionFactory factory =
            TransactionFactoryImpl.getInstance();
    Transaction transaction;

    @Override
    public List<TeacherSubject> findAll()
            throws ExceptionService, PersistentException {
        List<TeacherSubject> teacherSubjects = new ArrayList<>();
        try {
            transaction = factory.createTransaction();
            transaction.createDao(teacherSubjectDao);
            for (TeacherSubject subject : teacherSubjectDao.
                    findAllEntity()) {
                TeacherSubject.TeacherSubjectBuilder subjectBuilder =
                        new TeacherSubject.TeacherSubjectBuilder();
                TeacherSubject teacherSubject = subjectBuilder.build();
                UserInfoDao userInfoDao = daoFactory.getUserInfoDAO();
                SubjectDao subjectDao = daoFactory.getSubjectDAO();
                teacherSubject.setId(subject.getId());
                subjectBuilder.setUserInfo(userInfoDao.findEntityById(
                                subject.getUserInfo().getId())).
                        setSubject(subjectDao.findEntityById(
                                subject.getSubject().getId()));
                teacherSubjects.add(teacherSubject);
            }
            transaction.commit();
        } catch (DaoException | PersistentException | SQLException e) {
            LOGGER.debug("Service error findAll " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        teacherSubjects.sort(
                Comparator.comparingLong(
                        TeacherSubject::getId));
        return teacherSubjects;
    }

    @Override
    public TeacherSubject findEntityById(Long id)
            throws PersistentException, ExceptionService {
        TeacherSubject.TeacherSubjectBuilder builder =
                new TeacherSubject.TeacherSubjectBuilder();
        TeacherSubject teacherSubject = builder.build();
        try {
            transaction = factory.createTransaction();
            transaction.createDao(teacherSubjectDao);
            UserInfoDao userInfoDao =
                    daoFactory.getUserInfoDAO();
            SubjectDao subjectDao =
                    daoFactory.getSubjectDAO();
            teacherSubject.setId(
                    teacherSubjectDao.findEntityById(id).getId());
            builder.setSubject(subjectDao.findEntityById(
                    teacherSubjectDao.findEntityById(id).
                            getSubject().getId()));
            builder.setUserInfo(userInfoDao.findEntityById(
                    teacherSubjectDao.findEntityById(id).
                            getUserInfo().getId()));
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Error find teacher subject entity {}.", e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return teacherSubject;
    }

    @Override
    public boolean delete(TeacherSubject t) {
        LOGGER.debug("Deleting TeacherSubject is not supported.");
        throw new UnsupportedOperationException(
                "Deleting TeacherSubject is not supported.");
    }

    @Override
    public boolean delete(long id) {
        LOGGER.debug("Deleting TeacherSubject is not supported.");
        throw new UnsupportedOperationException(
                "Deleting TeacherSubject is not supported.");
    }

    @Override
    public boolean create(TeacherSubject t)
            throws ExceptionService, PersistentException {
        boolean result;
        if (t==null) {
            LOGGER.debug("TeacherSubject equals null");
            throw new ExceptionService("TeacherSubject equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(teacherSubjectDao);
            result = teacherSubjectDao.create(t);
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error create " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public boolean update(TeacherSubject t)
            throws ExceptionService, PersistentException {
        boolean result;
        if (t==null) {
            LOGGER.debug("TeacherSubject equals null");
            throw new ExceptionService("TeacherSubject equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(teacherSubjectDao);
            result = teacherSubjectDao.update(t);
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
    public List<TeacherSubject> findTeacherSubjectBySurnameTeacher(
            String namePattern) throws ExceptionService, PersistentException {
        if (!DataValidator.isNameValid(namePattern)
                || Objects.equals(namePattern, "")) {
            LOGGER.debug("Subject name is not valid.");
            throw new ExceptionService("Subject name is not valid.");
        }
        List<TeacherSubject> teacherSubjects =
                new ArrayList<>();
        try {
            transaction = factory.createTransaction();
            transaction.createDao(teacherSubjectDao);
            for (TeacherSubject subject : teacherSubjectDao.
                    findTeacherSubjectBySurnameTeacher(namePattern)) {
                TeacherSubject.TeacherSubjectBuilder builder =
                        new TeacherSubject.TeacherSubjectBuilder();
                TeacherSubject teacherSubject = builder.build();
                UserInfoDao userInfoDao = daoFactory.getUserInfoDAO();
                SubjectDao subjectDao = daoFactory.getSubjectDAO();
                teacherSubject.setId(subject.getId());
                builder.setUserInfo(userInfoDao.findEntityById(
                                subject.getUserInfo().getId())).
                        setSubject(subjectDao.findEntityById(
                                subject.getSubject().getId()));
                teacherSubjects.add(teacherSubject);
            }
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug(
                    "Service error findTeacherSubjectBySurnameTeacher " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        teacherSubjects.sort(
                Comparator.comparingLong(
                        TeacherSubject::getId));
        return teacherSubjects;
    }

    @Override
    public List<TeacherSubject> findTeacherSubjectBySubject(
            String namePattern) throws ExceptionService, PersistentException {
        if (!DataValidator.isNameValid(namePattern)
                || Objects.equals(namePattern, "")) {
            LOGGER.debug("Subject name is not valid.");
            throw new ExceptionService("Subject name is not valid.");
        }
        List<TeacherSubject> teacherSubjects =
                new ArrayList<>();
        try {
            transaction = factory.createTransaction();
            transaction.createDao(teacherSubjectDao);
            for (TeacherSubject subject : teacherSubjectDao.
                    findTeacherSubjectBySubject(namePattern)) {
                TeacherSubject.TeacherSubjectBuilder builder =
                        new TeacherSubject.TeacherSubjectBuilder();
                TeacherSubject teacherSubject = builder.build();
                UserInfoDao userInfoDao = daoFactory.getUserInfoDAO();
                SubjectDao subjectDao = daoFactory.getSubjectDAO();
                teacherSubject.setId(subject.getId());
                builder.setUserInfo(userInfoDao.findEntityById(
                                subject.getUserInfo().getId())).
                        setSubject(subjectDao.findEntityById(
                                subject.getSubject().getId()));
                teacherSubjects.add(teacherSubject);
            }
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug(
                    "Service error findTeacherSubjectBySubject " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        teacherSubjects.sort(
                Comparator.comparingLong(
                        TeacherSubject::getId));
        return teacherSubjects;
    }
}
