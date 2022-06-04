package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.*;
import by.arabienko.onlineSchool.dao.mysql.TransactionFactoryImpl;
import by.arabienko.onlineSchool.entity.*;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.StudentCourseService;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class StudentCourseServiceImpl extends ServiceImpl implements StudentCourseService {
    private static final Logger LOGGER =
            LogManager.getLogger(StudentCourseServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    StudentCourseDao studentCourseDao =
            daoFactory.getStudentCourseDAO();
    TeacherCourseDao teacherCourseDao =
            daoFactory.getTeacherCourseDAO();
    SubjectDao subjectDao = daoFactory.getSubjectDAO();
    TeacherSubjectDao teacherSubjectDao =
            daoFactory.getTeacherSubjectDAO();
    UserInfoDao userInfoDAO = daoFactory.getUserInfoDAO();
    TransactionFactory factory = TransactionFactoryImpl.getInstance();
    Transaction transaction;

    public List<StudentCourse> findAll(int offset, int noOfRecords)
            throws ExceptionService, PersistentException {
        List<StudentCourse> studentCourses = new ArrayList<>();
        try {
            transaction = factory.createTransaction();
            transaction.createDao(studentCourseDao, teacherCourseDao,
                    teacherSubjectDao, subjectDao, userInfoDAO);
            for (StudentCourse course : studentCourseDao.findAll(offset, noOfRecords)) {
                StudentCourse.StudentCourseBuilder studentCourseBuilder =
                        new StudentCourse.StudentCourseBuilder();
                StudentCourse studentCourse = studentCourseBuilder.build();
                studentCourse.setId(course.getId());
                Long idTC = course.getTeacherCourse().getId();
                TeacherCourse tc = teacherCourseDao.findEntityById(idTC);
                Long idTS = tc.getTeacherSubject().getId();
                TeacherSubject ts = teacherSubjectDao.findEntityById(idTS);
                UserInfo teacher = userInfoDAO.findEntityById(
                        ts.getUserInfo().getId());
                Long idS = ts.getSubject().getId();
                Subject subject =
                        subjectDao.findEntityById(idS);
                TeacherCourse.TeacherCourseBuilder teacherCourseBuilder =
                        new TeacherCourse.TeacherCourseBuilder();
                TeacherCourse teacherCourse = teacherCourseBuilder.build();
                TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder =
                        new TeacherSubject.TeacherSubjectBuilder();
                TeacherSubject teacherSubject = teacherSubjectBuilder.build();
                teacherSubject.setId(tc.getTeacherSubject().getId());
                teacherSubjectBuilder.setSubject(subject).setUserInfo(teacher);
                teacherCourse.setId(tc.getId());
                teacherCourseBuilder.setTeacherSubject(teacherSubject).
                        setStartDate(tc.getStartDate()).setEndDate(tc.getEndDate());
                studentCourseBuilder.setTeacherCourse(teacherCourse).
                        setStatus(course.getStatus());
                UserInfo student = userInfoDAO.findEntityById(
                        course.getUserInfo().getId());
                studentCourseBuilder.setUserInfo(student);
                studentCourses.add(studentCourse);
            }
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
    public List<StudentCourse> findEntityByStudentId(Long id)
            throws ExceptionService, PersistentException {
        LOGGER.debug("Start service - findEntityByStudentId");
        List<StudentCourse> studentCourses = new ArrayList<>();
        try {
            transaction = factory.createTransaction();
            transaction.createDao(studentCourseDao, teacherCourseDao,
                    teacherSubjectDao, subjectDao, userInfoDAO);
            for (StudentCourse course :
                    studentCourseDao.findStudentCourseByStudentID(id)) {
                StudentCourse.StudentCourseBuilder studentCourseBuilder =
                        new StudentCourse.StudentCourseBuilder();
                StudentCourse studentCourse = studentCourseBuilder.build();
                studentCourse.setId(course.getId());
                Long idTC = course.getTeacherCourse().getId();
                TeacherCourse tc = teacherCourseDao.findEntityById(idTC);
                Long idTS = tc.getTeacherSubject().getId();
                TeacherSubject ts = teacherSubjectDao.findEntityById(idTS);
                UserInfo teacher = userInfoDAO.findEntityById(
                        ts.getUserInfo().getId());
                Long idS = ts.getSubject().getId();
                Subject subject =
                        subjectDao.findEntityById(idS);
                TeacherCourse.TeacherCourseBuilder teacherCourseBuilder =
                        new TeacherCourse.TeacherCourseBuilder();
                TeacherCourse teacherCourse = teacherCourseBuilder.build();
                TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder =
                        new TeacherSubject.TeacherSubjectBuilder();
                TeacherSubject teacherSubject = teacherSubjectBuilder.build();
                teacherSubject.setId(tc.getTeacherSubject().getId());
                teacherSubjectBuilder.setSubject(subject).setUserInfo(teacher);
                teacherCourse.setId(tc.getId());
                teacherCourseBuilder.setTeacherSubject(teacherSubject).
                        setStartDate(tc.getStartDate()).setEndDate(tc.getEndDate());
                studentCourseBuilder.setTeacherCourse(teacherCourse).
                        setStatus(course.getStatus());
                UserInfo student = userInfoDAO.findEntityById(
                        course.getUserInfo().getId());
                studentCourseBuilder.setUserInfo(student);
                studentCourses.add(studentCourse);
            }
            transaction.commit();
        } catch (DaoException | PersistentException e) {
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
        List<StudentCourse> studentCourses =
                new ArrayList<>();
        if (!DataValidator.isSubjectNameValid(namePattern)
                || Objects.equals(namePattern, "")) {
            LOGGER.debug("Subject name is not valid.");
            throw new ExceptionService("Subject name is not valid.");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(studentCourseDao, teacherCourseDao,
                    teacherSubjectDao, subjectDao, userInfoDAO);
            for (StudentCourse course : studentCourseDao.
                    findStudentCourseBySubject(namePattern)) {
                StudentCourse.StudentCourseBuilder studentCourseBuilder =
                        new StudentCourse.StudentCourseBuilder();
                StudentCourse studentCourse = studentCourseBuilder.build();
                studentCourse.setId(course.getId());
                Long idTC = course.getTeacherCourse().getId();
                TeacherCourse tc = teacherCourseDao.findEntityById(idTC);
                Long idTS = tc.getTeacherSubject().getId();
                TeacherSubject ts = teacherSubjectDao.findEntityById(idTS);
                UserInfo teacher = userInfoDAO.findEntityById(
                        ts.getUserInfo().getId());
                Long idS = ts.getSubject().getId();
                Subject subject =
                        subjectDao.findEntityById(idS);
                TeacherCourse.TeacherCourseBuilder teacherCourseBuilder =
                        new TeacherCourse.TeacherCourseBuilder();
                TeacherCourse teacherCourse = teacherCourseBuilder.build();
                TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder =
                        new TeacherSubject.TeacherSubjectBuilder();
                TeacherSubject teacherSubject = teacherSubjectBuilder.build();
                teacherSubject.setId(tc.getTeacherSubject().getId());
                teacherSubjectBuilder.setSubject(subject).setUserInfo(teacher);
                teacherCourse.setId(tc.getId());
                teacherCourseBuilder.setTeacherSubject(teacherSubject).
                        setStartDate(tc.getStartDate()).setEndDate(tc.getEndDate());
                studentCourseBuilder.setTeacherCourse(teacherCourse).
                        setStatus(course.getStatus());
                UserInfo student = userInfoDAO.findEntityById(
                        course.getUserInfo().getId());
                studentCourseBuilder.setUserInfo(student);
                studentCourses.add(studentCourse);
            }
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
