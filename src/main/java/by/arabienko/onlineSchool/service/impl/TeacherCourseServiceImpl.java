package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.*;
import by.arabienko.onlineSchool.dao.mysql.TransactionFactoryImpl;
import by.arabienko.onlineSchool.entity.Subject;
import by.arabienko.onlineSchool.entity.TeacherCourse;
import by.arabienko.onlineSchool.entity.TeacherSubject;
import by.arabienko.onlineSchool.entity.UserInfo;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.TeacherCourseService;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class TeacherCourseServiceImpl extends ServiceImpl implements TeacherCourseService {
    private static final Logger LOGGER =
            LogManager.getLogger(TeacherCourseServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    TeacherCourseDao teacherCourseDao =
            daoFactory.getTeacherCourseDAO();
    SubjectDao subjectDao = daoFactory.getSubjectDAO();
    TeacherSubjectDao teacherSubjectDao = daoFactory.getTeacherSubjectDAO();
    UserInfoDao teacherDao = daoFactory.getUserInfoDAO();
    TransactionFactory factory = TransactionFactoryImpl.getInstance();
    Transaction transaction;

    @Override
    public List<TeacherCourse> findAll()
            throws ExceptionService, PersistentException {
        List<TeacherCourse> teacherCourses;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(teacherCourseDao);
            teacherCourses = teacherCourseDao.
                    findAll();
            transaction.commit();
        } catch (DaoException | PersistentException | SQLException e) {
            LOGGER.debug("Service error findAll " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        teacherCourses.sort(
                Comparator.comparingLong(
                        TeacherCourse::getId));
        return teacherCourses;
    }

    @Override
    public TeacherCourse findEntityById(Long id)
            throws ExceptionService, PersistentException {
        TeacherCourse teacherCourse;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(teacherCourseDao);
            teacherCourse = teacherCourseDao.findEntityById(id);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error find by id " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return teacherCourse;
    }

    @Override
    public boolean delete(TeacherCourse t) {
        LOGGER.debug("Deleting TeacherCourse is not supported.");
        throw new UnsupportedOperationException(
                "Deleting TeacherCourse is not supported.");
    }

    @Override
    public boolean delete(long id) {
        LOGGER.debug("Deleting TeacherCourse is not supported.");
        throw new UnsupportedOperationException(
                "Deleting TeacherCourse is not supported.");
    }

    @Override
    public boolean create(TeacherCourse t)
            throws ExceptionService, PersistentException {
        boolean result;
        if (t==null) {
            LOGGER.debug("TeacherCourse equals null");
            throw new ExceptionService("TeacherCourse equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(teacherCourseDao);
            result = teacherCourseDao.create(t);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error create teacher course " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public boolean update(TeacherCourse t)
            throws ExceptionService, PersistentException {
        boolean result;
        if (t==null) {
            LOGGER.debug("TeacherCourse equals null");
            throw new ExceptionService("TeacherCourse equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(teacherCourseDao);
            result = teacherCourseDao.update(t);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error update teacher course " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public List<TeacherCourse> findCourseByStartDate(
            String namePattern) throws ExceptionService, PersistentException {
        List<TeacherCourse> teacherCourses;
        if (!DataValidator.isDateFormat(namePattern)
                || Objects.equals(namePattern, "")) {
            LOGGER.debug("Date is not valid.");
            throw new ExceptionService("Date is not valid.");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(teacherCourseDao);
            teacherCourses = teacherCourseDao.
                    findCourseByStartDate(namePattern);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error find by start date " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        teacherCourses.sort(
                Comparator.comparingLong(
                        TeacherCourse::getId));
        return teacherCourses;
    }

    @Override
    public List<TeacherCourse> findCourseBySubject(
            String namePattern) throws ExceptionService, PersistentException {
        List<TeacherCourse> teacherCourses = new ArrayList<>();
        if (!DataValidator.isSubjectNameValid(namePattern)
                || Objects.equals(namePattern, "")) {
            LOGGER.debug("Subject name is not valid.");
            throw new ExceptionService("Subject name is not valid.");
        }

        try {
            transaction = factory.createTransaction();
            transaction.createDao(teacherCourseDao,
                    teacherSubjectDao,subjectDao,teacherDao);
            for (TeacherCourse t : teacherCourseDao.
                    findCourseBySubject(namePattern)) {
                Long idTS = t.getTeacherSubject().getId();
                TeacherSubject ts = teacherSubjectDao.findEntityById(idTS);
                UserInfo teacher = teacherDao.findEntityById(ts.getUserInfo().getId());
                Long idS = ts.getSubject().getId();
                Subject subject =
                        subjectDao.findEntityById(idS);
                TeacherCourse.TeacherCourseBuilder teacherCourseBuilder =
                        new TeacherCourse.TeacherCourseBuilder();
                TeacherCourse teacherCourse = teacherCourseBuilder.build();
                TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder =
                        new TeacherSubject.TeacherSubjectBuilder();
                TeacherSubject teacherSubject = teacherSubjectBuilder.build();
                teacherSubject.setId(t.getTeacherSubject().getId());
                teacherSubjectBuilder.setSubject(subject).setUserInfo(teacher);
                teacherCourse.setId(t.getId());
                teacherCourseBuilder.setTeacherSubject(teacherSubject).
                        setStartDate(t.getStartDate()).setEndDate(t.getEndDate());
                teacherCourses.add(teacherCourse);
            }
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error find by start date " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        teacherCourses.sort(
                Comparator.comparing(
                                TeacherCourse::getStartDate).
                        thenComparing(TeacherCourse::getId));
        return teacherCourses;
    }
}
