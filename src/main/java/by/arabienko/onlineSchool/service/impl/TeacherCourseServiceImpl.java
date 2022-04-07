package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.entity.TeacherCourse;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.dao.TeacherCourseDao;
import by.arabienko.onlineSchool.service.TeacherCourseService;
import by.arabienko.onlineSchool.service.TeacherSubjectService;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class TeacherCourseServiceImpl implements TeacherCourseService {
    private static final Logger LOGGER =
            LogManager.getLogger(TeacherCourseServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    TeacherCourseDao teacherCourseDao =
            daoFactory.getTeacherCourseDAO();
    TeacherSubjectService teacherSubjectService =
            new TeacherSubjectServiceImpl();

    @Override
    public List<TeacherCourse> findAll() throws ExceptionService {
        List<TeacherCourse> teacherCourses = new ArrayList<>();
        try {
            for (TeacherCourse subject : teacherCourseDao.
                    findAll()) {
                TeacherCourse.TeacherCourseBuilder courseBuilder =
                        new TeacherCourse.TeacherCourseBuilder();
                TeacherCourse teacherCourse = courseBuilder.build();
                teacherCourse.setId(subject.getId());
                courseBuilder.setTeacherSubject(
                                teacherSubjectService.findEntityById(
                                        subject.getTeacherSubject().getId())).
                        setStartDate(subject.getStartDate()).
                        setEndDate(subject.getEndDate());
                teacherCourses.add(teacherCourse);
            }
        } catch (DaoException e) {
            LOGGER.debug("Service error findAll " + e);
            throw new ExceptionService(e);
        }
        teacherCourses.sort(
                Comparator.comparingLong(
                        TeacherCourse::getId));
        return teacherCourses;
    }

    @Override
    public TeacherCourse findEntityById(Long id) throws ExceptionService {
        TeacherCourse.TeacherCourseBuilder courseBuilder =
                new TeacherCourse.TeacherCourseBuilder();
        TeacherCourse teacherCourse = courseBuilder.build();
        try {
            teacherCourse.setId(teacherCourseDao.findEntityById(id).getId());
            courseBuilder.setTeacherSubject(teacherSubjectService.findEntityById(
                            teacherCourseDao.findEntityById(id).
                                    getTeacherSubject().getId())).
                    setStartDate(teacherCourseDao.findEntityById(id).getStartDate()).
                    setEndDate(teacherCourseDao.findEntityById(id).getEndDate());
        } catch (DaoException e) {
            LOGGER.debug("Service error find by id " + e);
            throw new ExceptionService(e);
        }
        return teacherCourse;
    }

    @Override
    public boolean delete(TeacherCourse t) {
        LOGGER.debug( "Deleting TeacherCourse is not supported.");
        throw new UnsupportedOperationException(
                "Deleting TeacherCourse is not supported.");    }

    @Override
    public boolean delete(long id) {
        LOGGER.debug( "Deleting TeacherCourse is not supported.");
        throw new UnsupportedOperationException(
                "Deleting TeacherCourse is not supported.");    }

    @Override
    public boolean create(TeacherCourse t) throws ExceptionService {
        if (t==null) {
            LOGGER.debug("TeacherCourse equals null");
            throw new ExceptionService("TeacherCourse equals null");
        }
        try {
            return teacherCourseDao.create(t);
        } catch (DaoException e) {
            LOGGER.debug("Service error create teacher course " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean update(TeacherCourse t) throws ExceptionService {
        if (t==null) {
            LOGGER.debug("TeacherCourse equals null");
            throw new ExceptionService("TeacherCourse equals null");
        }
        try {
            return teacherCourseDao.update(t);
        } catch (DaoException e) {
            LOGGER.debug("Service error update teacher course " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public List<TeacherCourse> findCourseByStartDate(
            String namePattern) throws ExceptionService {
        List<TeacherCourse> teacherCourses = new ArrayList<>();
        if (!DataValidator.isDateFormat(namePattern)
                || Objects.equals(namePattern, "")) {
            LOGGER.debug("Date is not valid.");
            throw new ExceptionService("Date is not valid.");
        }
        try {
            for (TeacherCourse subject : teacherCourseDao.
                    findCourseByStartDate(namePattern)) {
                TeacherCourse.TeacherCourseBuilder courseBuilder =
                        new TeacherCourse.TeacherCourseBuilder();
                TeacherCourse teacherCourse = courseBuilder.build();
                teacherCourse.setId(subject.getId());
                courseBuilder.setTeacherSubject(
                                teacherSubjectService.findEntityById(
                                        subject.getTeacherSubject().getId())).
                        setStartDate(subject.getStartDate()).
                        setEndDate(subject.getEndDate());
                teacherCourses.add(teacherCourse);
            }
        } catch (DaoException e) {
            LOGGER.debug("Service error find by start date " + e);
            throw new ExceptionService(e);
        }
        teacherCourses.sort(
                Comparator.comparingLong(
                        TeacherCourse::getId));
        return teacherCourses;
    }

    @Override
    public List<TeacherCourse> findCourseBySubject(
            String namePattern) throws ExceptionService {
        List<TeacherCourse> teacherCourses = new ArrayList<>();
        if (!DataValidator.isSubjectNameValid(namePattern)
                || Objects.equals(namePattern, "")) {
            LOGGER.debug("Subject name is not valid.");
            throw new ExceptionService("Subject name is not valid.");
        }
        try {
            for (TeacherCourse subject : teacherCourseDao.
                    findCourseBySubject(namePattern)) {
                TeacherCourse.TeacherCourseBuilder courseBuilder =
                        new TeacherCourse.TeacherCourseBuilder();
                TeacherCourse teacherCourse = courseBuilder.build();
                teacherCourse.setId(subject.getId());
                courseBuilder.setTeacherSubject(
                                teacherSubjectService.findEntityById(
                                        subject.getTeacherSubject().getId())).
                        setStartDate(subject.getStartDate()).
                        setEndDate(subject.getEndDate());
                teacherCourses.add(teacherCourse);
            }
        } catch (DaoException e) {
            LOGGER.debug("Service error find by start date " + e);
            throw new ExceptionService(e);
        }
        teacherCourses.sort(
                Comparator.comparing(
                        TeacherCourse::getStartDate).
                        thenComparing(TeacherCourse::getId));
        return teacherCourses;
    }
}
