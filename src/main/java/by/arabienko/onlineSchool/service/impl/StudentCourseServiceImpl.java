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
import by.arabienko.onlineSchool.service.TeacherCourseService;
import by.arabienko.onlineSchool.service.UserInfoService;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class StudentCourseServiceImpl implements StudentCourseService {
    private static final Logger LOGGER =
            LogManager.getLogger(StudentCourseServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    StudentCourseDao studentCourseDao =
            daoFactory.getStudentCourseDAO();
    TeacherCourseService teacherCourseService =
            new TeacherCourseServiceImpl();
    UserInfoService userService =
            new UserInfoServiceImpl();
    TransactionFactory factory = TransactionFactoryImpl.getInstance();
    Transaction transaction;

    @Override
    public List<StudentCourse> findAll() throws ExceptionService {
        List<StudentCourse> studentCourses = new ArrayList<>();
        try {
            transaction = factory.createTransaction();
            transaction.createDao(studentCourseDao);
            for (StudentCourse courseDAO : studentCourseDao.
                    findAll()) {
                StudentCourse.StudentCourseBuilder courseBuilder =
                        new StudentCourse.StudentCourseBuilder();
                StudentCourse studentCourse = courseBuilder.build();
                studentCourse.setId(courseDAO.getId());
                courseBuilder.setTeacherCourse(
                                teacherCourseService.findEntityById(
                                        courseDAO.getTeacherCourse().getId())).
                        setUserInfo(userService.findEntityById(
                                courseDAO.getUserInfo().getId())).
                        setStatus(courseDAO.getStatus());
                studentCourses.add(studentCourse);
            }
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findAll" + e);
            throw new ExceptionService(e);
        } finally {
            if (factory != null){
            factory.close();}
        }
        studentCourses.sort(
                Comparator.comparingLong(
                        StudentCourse::getId));
        return studentCourses;
    }

    @Override
    public StudentCourse findEntityById(Long id) throws ExceptionService, PersistentException {
        StudentCourse.StudentCourseBuilder studentCourseBuilder =
                new StudentCourse.StudentCourseBuilder();
        StudentCourse studentCourse = studentCourseBuilder.build();
        TransactionFactory factory = TransactionFactoryImpl.getInstance();
        Transaction transaction = null;
        try {
            transaction = (Transaction) factory.createTransaction().createDao(studentCourseDao);

            studentCourse.setId(studentCourseDao.findEntityById(id).getId());
            studentCourseBuilder.setTeacherCourse(teacherCourseService.findEntityById(
                            studentCourseDao.findEntityById(id).
                                    getTeacherCourse().getId())).
                    setUserInfo(userService.findEntityById(
                            studentCourseDao.findEntityById(id).getUserInfo().getId())).
                    setStatus(studentCourseDao.findEntityById(id).getStatus());
            transaction.commit();
        } catch (DaoException | ExceptionService | PersistentException e) {
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
    public boolean create(StudentCourse t) throws ExceptionService {
        if (t==null) {
            LOGGER.debug("StudentCourse equals null");
            throw new ExceptionService("StudentCourse equals null");
        }
        try {
            return studentCourseDao.create(t);
        } catch (DaoException e) {
            LOGGER.debug("Service error create StudentCourse " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean update(StudentCourse t) throws ExceptionService {
        if (t==null) {
            LOGGER.debug("StudentCourse equals null");
            throw new ExceptionService("StudentCourse equals null");
        }
        try {
            return studentCourseDao.update(t);
        } catch (DaoException e) {
            LOGGER.debug("Service error update StudentCourse " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public List<StudentCourse> findStudentCourseBySubject(
            String namePattern) throws ExceptionService {
        List<StudentCourse> studentCourses = new ArrayList<>();
        if (!DataValidator.isSubjectNameValid(namePattern)
                || Objects.equals(namePattern, "")) {
            LOGGER.debug("Subject name is not valid.");
            throw new ExceptionService("Subject name is not valid.");
        }
        try {
            for (StudentCourse courseDAO : studentCourseDao.
                    findStudentCourseBySubject(namePattern)) {
                StudentCourse.StudentCourseBuilder courseBuilder =
                        new StudentCourse.StudentCourseBuilder();
                StudentCourse studentCourse = courseBuilder.build();
                studentCourse.setId(courseDAO.getId());
                courseBuilder.setTeacherCourse(
                                teacherCourseService.findEntityById(
                                        courseDAO.getTeacherCourse().getId())).
                        setUserInfo(userService.findEntityById(
                                courseDAO.getUserInfo().getId())).
                        setStatus(courseDAO.getStatus());
                studentCourses.add(studentCourse);
            }
        } catch (DaoException e) {
            LOGGER.debug("Service error find by start date " + e);
            throw new ExceptionService(e);
        }
        studentCourses.sort(
                Comparator.comparing(
                        StudentCourse::getId));
        return studentCourses;
    }

    @Override
    public boolean isUnique(long patternCourse,
                            long patternStudent) throws ExceptionService {
        try {
            return studentCourseDao.isUnique(patternCourse, patternStudent);
        } catch (DaoException e) {
            LOGGER.debug("Service error isUnique " + e);
            throw new ExceptionService(e);
        }
    }
}
