package by.arabienko.onlineSchool.service.impl;

import by.arabienko.onlineSchool.controller.DAOFactory;
import by.arabienko.onlineSchool.dao.ScheduleDao;
import by.arabienko.onlineSchool.dao.Transaction;
import by.arabienko.onlineSchool.dao.TransactionFactory;
import by.arabienko.onlineSchool.dao.mysql.TransactionFactoryImpl;
import by.arabienko.onlineSchool.entity.Schedule;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.ScheduleService;
import by.arabienko.onlineSchool.valid.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class ScheduleServiceImpl extends ServiceImpl implements ScheduleService {
    private static final Logger LOGGER =
            LogManager.getLogger(ScheduleServiceImpl.class);

    DAOFactory daoFactory = DAOFactory.getInstance();
    ScheduleDao scheduleDao = daoFactory.getScheduleDao();
     TransactionFactory factory = TransactionFactoryImpl.getInstance();
    Transaction transaction;

    @Override
    public List<Schedule> findAll()
            throws ExceptionService, PersistentException {
        List<Schedule> schedules;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(scheduleDao);
            schedules = scheduleDao.findAllEntity();
            transaction.commit();
        } catch (DaoException | PersistentException | SQLException e) {
            LOGGER.debug("Service error findAll " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        schedules.sort(
                Comparator.comparingLong(
                        Schedule::getId));
        return schedules;
    }

    @Override
    public Schedule findEntityById(Long id)
            throws ExceptionService, PersistentException {
                Schedule schedule;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(scheduleDao);
            schedule = scheduleDao.findEntityById(id);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            transaction.rollback();
            LOGGER.debug("Service error findAll " + e);
            throw new ExceptionService(e);
        } finally {
            factory.close();
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
    public boolean create(Schedule t) throws ExceptionService, PersistentException {
        boolean result;
        if (t==null) {
            LOGGER.debug("Schedule equals null");
            throw new ExceptionService("Schedule equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(scheduleDao);
            result = scheduleDao.create(t);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error create Schedule " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public boolean update(Schedule t) throws ExceptionService, PersistentException {
        boolean result;
        if (t==null) {
            LOGGER.debug("Schedule equals null");
            throw new ExceptionService("Schedule equals null");
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(scheduleDao);
            result = scheduleDao.update(t);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error update Schedule " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public List<Schedule> findScheduleByStudent(long idPattern)
            throws ExceptionService, PersistentException {
        List<Schedule> schedules;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(scheduleDao);
            schedules = scheduleDao.
                    findScheduleByStudent(idPattern);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findScheduleByStudent " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        schedules.sort(
                Comparator.comparingLong(
                        Schedule::getId));
        return schedules;
    }

    @Override
    public boolean isUnique(long patternCourse, String patternDate)
            throws ExceptionService, PersistentException {
        boolean result;
        if (!DataValidator.isDateOfWeekValid(patternDate)) {
            LOGGER.debug("Pattern date of week is not valid.- "
                    + patternDate);
            throw new ExceptionService("Pattern date of week is not valid."
                    + patternDate);
        }
        try {
            transaction = factory.createTransaction();
            transaction.createDao(scheduleDao);
            result = scheduleDao.isUnique(patternCourse, patternDate.trim());
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error isUnique " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        return result;
    }

    @Override
    public List<Schedule> findScheduleByTeacher(long id)
            throws ExceptionService, PersistentException {
        List<Schedule> schedules;
        try {
            transaction = factory.createTransaction();
            transaction.createDao(scheduleDao);
            schedules = scheduleDao.
                    findScheduleByTeacher(id);
            transaction.commit();
        } catch (DaoException | PersistentException e) {
            LOGGER.debug("Service error findScheduleByTeacher " + e);
            transaction.rollback();
            throw new ExceptionService(e);
        } finally {
            factory.close();
        }
        schedules.sort(
                Comparator.comparingLong(
                        Schedule::getId));
        return schedules;
    }
}
