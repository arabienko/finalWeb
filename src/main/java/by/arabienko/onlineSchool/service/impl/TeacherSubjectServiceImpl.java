package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.SubjectDao;
import by.arabienko.onlineSchool.dao.TeacherSubjectDao;
import by.arabienko.onlineSchool.dao.UserInfoDao;
import by.arabienko.onlineSchool.entity.TeacherSubject;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.TeacherSubjectService;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class TeacherSubjectServiceImpl
        implements TeacherSubjectService {
    private static final Logger LOGGER =
            LogManager.getLogger(TeacherSubjectServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    TeacherSubjectDao teacherSubjectDao =
            daoFactory.getTeacherSubjectDAO();

    @Override
    public List<TeacherSubject> findAll() throws ExceptionService {
        List<TeacherSubject> teacherSubjects = new ArrayList<>();
        try {
            for (TeacherSubject subject : teacherSubjectDao.
                    findAll()) {
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
        } catch (DaoException e) {
            LOGGER.debug("Service error findAll " + e);
            throw new ExceptionService(e);
        }
        teacherSubjects.sort(
                Comparator.comparingLong(
                        TeacherSubject::getId));
        return teacherSubjects;
    }

    @Override
    public TeacherSubject findEntityById(Long id) {
        TeacherSubject.TeacherSubjectBuilder builder =
                new TeacherSubject.TeacherSubjectBuilder();
        TeacherSubject teacherSubject = builder.build();
        try {
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
        } catch (DaoException e) {
            LOGGER.debug("Error find teacher subject entity {}.",e);
        }
        return teacherSubject;
    }

    @Override
    public boolean delete(TeacherSubject t) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean create(TeacherSubject t) throws ExceptionService {
        if (t==null){
            LOGGER.debug("TeacherSubject equals null");
            throw new ExceptionService("TeacherSubject equals null");
        }
        try {
            return teacherSubjectDao.create(t);
        } catch (DaoException e) {
            LOGGER.debug("Service error create " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean update(TeacherSubject t)
            throws ExceptionService {
        if (t==null){
            LOGGER.debug("TeacherSubject equals null");
            throw new ExceptionService("TeacherSubject equals null");
        }
        try {
            return teacherSubjectDao.update(t);
        } catch (DaoException e) {
            LOGGER.debug("Service error update " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public List<TeacherSubject> findTeacherSubjectBySurnameTeacher(
            String namePattern) throws ExceptionService {
        if (!DataValidator.isNameValid(namePattern)
                || Objects.equals(namePattern, "")){
            LOGGER.debug("Subject name is not valid.");
            throw new ExceptionService("Subject name is not valid.");
        }
        List<TeacherSubject> teacherSubjects =
                new ArrayList<>();
        try {
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
        } catch (DaoException e) {
            LOGGER.debug(
                    "Service error findTeacherSubjectBySurnameTeacher " + e);
            throw new ExceptionService(e);
        }
        teacherSubjects.sort(
                Comparator.comparingLong(
                        TeacherSubject::getId));
        return teacherSubjects;
    }

    @Override
    public List<TeacherSubject> findTeacherSubjectBySubject(
            String namePattern) throws ExceptionService {
        if (!DataValidator.isNameValid(namePattern)
                || Objects.equals(namePattern, "")){
            LOGGER.debug("Subject name is not valid.");
            throw new ExceptionService("Subject name is not valid.");
        }
        List<TeacherSubject> teacherSubjects =
                new ArrayList<>();
        try {
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
        } catch (DaoException e) {
            LOGGER.debug(
                    "Service error findTeacherSubjectBySubject " + e);
            throw new ExceptionService(e);
        }
        teacherSubjects.sort(
                Comparator.comparingLong(
                        TeacherSubject::getId));
        return teacherSubjects;
    }
}
