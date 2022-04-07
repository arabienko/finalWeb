package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.ScheduleDao;
import by.arabienko.onlineSchool.entity.Schedule;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.ScheduleService;
import by.arabienko.onlineSchool.valid.DataValidator;
import by.arabienko.onlineSchool.service.TeacherCourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {
    private static final Logger LOGGER =
            LogManager.getLogger(ScheduleServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    ScheduleDao scheduleDao = daoFactory.getScheduleDao();
    TeacherCourseService teacherCourseService =
            new TeacherCourseServiceImpl();

    @Override
    public List<Schedule> findAll()
            throws ExceptionService {
        List<Schedule> schedules =
                new ArrayList<>();
        try {
            for (Schedule schedule1 : scheduleDao.
                    findAll()) {
                Schedule.ScheduleBuilder scheduleBuilder =
                        new Schedule.ScheduleBuilder();
                Schedule schedule = scheduleBuilder.build();
                schedule.setId(schedule1.getId());
                scheduleBuilder.setTeacherCourse(
                                teacherCourseService.findEntityById(
                                        schedule1.getTeacherCourse().getId())).
                        setDateTime(schedule1.getDateTime());
                schedules.add(schedule);
            }
        } catch (DaoException e) {
            LOGGER.debug("Service error findAll " + e);
            throw new ExceptionService(e);
        }
        schedules.sort(
                Comparator.comparingLong(
                        Schedule::getId));
        return schedules;
    }

    @Override
    public Schedule findEntityById(Long id)
            throws ExceptionService {
        Schedule.ScheduleBuilder scheduleBuilder =
                new Schedule.ScheduleBuilder();
        Schedule schedule = scheduleBuilder.build();
        try {
            schedule.setId(scheduleDao.findEntityById(id).getId());
            scheduleBuilder.setTeacherCourse(
                            teacherCourseService.findEntityById(
                                    scheduleDao.findEntityById(id).
                                            getTeacherCourse().getId())).
                    setDateTime(scheduleDao.
                            findEntityById(id).getDateTime());
        } catch (DaoException e) {
            LOGGER.debug("Service error findAll " + e);
            throw new ExceptionService(e);
        }
        return schedule;
    }

    @Override
    public boolean delete(Schedule t) {
        LOGGER.debug("Deleting Schedule is not supported.");
        throw new UnsupportedOperationException(
                "Deleting Schedule is not supported.");
    }

    @Override
    public boolean delete(long id) {
        LOGGER.debug("Deleting Schedule is not supported.");
        throw new UnsupportedOperationException(
                "Deleting Schedule is not supported.");
    }

    @Override
    public boolean create(Schedule t) throws ExceptionService {
        if (t==null) {
            LOGGER.debug("Schedule equals null");
            throw new ExceptionService("Schedule equals null");
        }
        try {
            return scheduleDao.create(t);
        } catch (DaoException e) {
            LOGGER.debug("Service error create Schedule " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public boolean update(Schedule t) throws ExceptionService {
        if (t==null) {
            LOGGER.debug("Schedule equals null");
            throw new ExceptionService("Schedule equals null");
        }
        try {
            return scheduleDao.update(t);
        } catch (DaoException e) {
            LOGGER.debug("Service error update Schedule " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public List<Schedule> findScheduleByStudent(long idPattern)
            throws ExceptionService {
        List<Schedule> schedules =
                new ArrayList<>();
        try {
            for (Schedule schedule1 : scheduleDao.
                    findScheduleByStudent(idPattern)) {
                Schedule.ScheduleBuilder scheduleBuilder =
                        new Schedule.ScheduleBuilder();
                Schedule schedule = scheduleBuilder.build();
                schedule.setId(schedule1.getId());
                scheduleBuilder.setTeacherCourse(
                                teacherCourseService.findEntityById(
                                        schedule1.getTeacherCourse().getId())).
                        setDateTime(schedule1.getDateTime());
                schedules.add(schedule);
            }
        } catch (DaoException e) {
            LOGGER.debug("Service error findScheduleByStudent " + e);
            throw new ExceptionService(e);
        }
        schedules.sort(
                Comparator.comparingLong(
                        Schedule::getId));
        return schedules;
    }

    @Override
    public boolean isUnique(long patternCourse, String patternDate)
            throws ExceptionService {
        if (!DataValidator.isDateOfWeekValid(patternDate)){
            LOGGER.debug("Pattern date of week is not valid.- "
                    +patternDate);
            throw new ExceptionService("Pattern date of week is not valid."
                    + patternDate);
        }
        try {
            return scheduleDao.isUnique(patternCourse, patternDate.trim());
        } catch (DaoException e) {
            LOGGER.debug("Service error isUnique " + e);
            throw new ExceptionService(e);
        }
    }

    @Override
    public List<Schedule> findScheduleByTeacher(long id)
            throws ExceptionService {
        List<Schedule> schedules =
                new ArrayList<>();
        try {
            for (Schedule schedule1 : scheduleDao.
                    findScheduleByTeacher(id)) {
                Schedule.ScheduleBuilder scheduleBuilder =
                        new Schedule.ScheduleBuilder();
                Schedule schedule = scheduleBuilder.build();
                schedule.setId(schedule1.getId());
                scheduleBuilder.setTeacherCourse(
                                teacherCourseService.findEntityById(
                                        schedule1.getTeacherCourse().getId())).
                        setDateTime(schedule1.getDateTime());
                schedules.add(schedule);
            }
        } catch (DaoException e) {
            LOGGER.debug("Service error findScheduleByTeacher " + e);
            throw new ExceptionService(e);
        }
        schedules.sort(
                Comparator.comparingLong(
                        Schedule::getId));
        return schedules;
    }
}
