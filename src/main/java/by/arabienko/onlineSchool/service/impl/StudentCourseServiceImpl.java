package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.StudentCourseDao;
import by.arabienko.onlineSchool.dao.Transaction;
import by.arabienko.onlineSchool.dao.TransactionFactory;
import by.arabienko.onlineSchool.dao.mysql.TransactionFactoryImpl;
import by.arabienko.onlineSchool.entity.StudentCourse;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.StudentCourseService;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class StudentCourseServiceImpl extends ServiceImpl implements StudentCourseService {
    private static final Logger LOGGER =
            LogManager.getLogger(StudentCourseServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    StudentCourseDao studentCourseDao =
            daoFactory.getStudentCourseDAO();
    TransactionFactory factory = TransactionFactoryImpl.getInstance();
    Transaction transaction;

    @Override
    public List<StudentCourse> findAll()
            throws ExceptionService, PersistentException {
        List<StudentCourse> studentCourses;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(studentCourseDao);
            studentCourses = studentCourseDao.findAll();
            transaction.commit();
        } catch (DaoException | PersistentException | SQLException e) {
            LOGGER.debug("Service error findAll" + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            if (factory!=null) {
                factory.close();
            }
        }
        studentCourses.sort(
                Comparator.comparingLong(
                        StudentCourse::getId));
        return studentCourses;
    }

    @Override
    public StudentCourse findEntityById(Long id)
            throws ExceptionService, PersistentException {
        StudentCourse studentCourse;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(studentCourseDao);
            studentCourse = studentCourseDao.findEntityById(id);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error find by id " + e);
            assert transaction!=null;
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return studentCourse;
    }

    @Override
    public boolean delete(StudentCourse t) {
        LOGGER.debug("Deleting StudentCourse is not supported.");
        throw new UnsupportedOperationException(
                "Deleting StudentCourse is not supported.");
    }

    @Override
    public boolean delete(long id) {
        LOGGER.debug("Deleting StudentCourse is not supported.");
        throw new UnsupportedOperationException(
                "Deleting StudentCourse is not supported.");
    }

    @Override
    public boolean create(StudentCourse t)
            throws ExceptionService, PersistentException {
        boolean result;
        if (t==null) {
            LOGGER.debug("StudentCourse equals null");
            throw new ExceptionService("StudentCourse equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(studentCourseDao);
            result = studentCourseDao.create(t);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error create StudentCourse " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public boolean update(StudentCourse t)
            throws ExceptionService {
        boolean result;
        if (t==null) {
            LOGGER.debug("StudentCourse equals null");
            throw new ExceptionService("StudentCourse equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(studentCourseDao);
            result = studentCourseDao.update(t);
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error update StudentCourse " + e);
            throw new ExceptionService(e);
        }
        return result;
    }

    @Override
    public List<StudentCourse> findStudentCourseBySubject(
            String namePattern) throws ExceptionService, PersistentException {
        List<StudentCourse> studentCourses;
        if (!DataValidator.isSubjectNameValid(namePattern)
                || Objects.equals(namePattern, "")) {
            LOGGER.debug("Subject name is not valid.");
            throw new ExceptionService("Subject name is not valid.");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(studentCourseDao);
            studentCourses = studentCourseDao.
                    findStudentCourseBySubject(namePattern);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error find by start date " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        studentCourses.sort(
                Comparator.comparing(
                        StudentCourse::getId));
        return studentCourses;
    }

    @Override
    public boolean isUnique(long patternCourse, long patternStudent)
            throws ExceptionService, PersistentException {
        boolean result;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(studentCourseDao);
            result = studentCourseDao.
                    isUnique(patternCourse, patternStudent);
            transaction.commit();
        } catch (DaoException | PersistentException | SQLException e) {
            LOGGER.debug("Service error isUnique " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }
}
