package by.arabienko.onlineSchool.dao.mysql;

import by.arabienko.onlineSchool.dao.ScheduleDao;
import by.arabienko.onlineSchool.dao.pool.ConnectionPool;
import by.arabienko.onlineSchool.entity.Schedule;
import by.arabienko.onlineSchool.entity.TeacherCourse;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDaoImpl implements ScheduleDao {
    private static final Logger LOGGER =
            LogManager.getLogger(ScheduleDaoImpl.class);

    private static final String SQL_SELECT_ALL_SCHEDULE =
            "SELECT id, course_id, date_time FROM schedule";

    @Override
    public List<Schedule> findAll() throws DaoException {
        LOGGER.debug("Start find all teacher subjects.");
        List<Schedule> schedules = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.
                    executeQuery(SQL_SELECT_ALL_SCHEDULE);
            while (resultSet.next()) {
                Schedule.ScheduleBuilder scheduleBuilder =
                        new Schedule.ScheduleBuilder();
                Schedule schedule = scheduleBuilder.build();
                TeacherCourse teacherCourse = new TeacherCourse();
                schedule.setId(resultSet.getInt(1));
                teacherCourse.setId(resultSet.getInt(2));
                scheduleBuilder.setTeacherCourse(teacherCourse).
                        setDateTime(resultSet.getString(3));
                schedules.add(schedule);
            }
        } catch (SQLException | PersistentException e) {
            LOGGER.debug("SQLException (find all schedule) " + e);
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (find all schedule) " + e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (find all schedule) " + e);
            }
        }
        return schedules;
    }

    private static final String SQL_IS_LOGIN_UNIQUE =
            "SELECT course_id, date_time FROM schedule WHERE course_id=?, date_time=?";
    @Override
    public boolean isUnique(long patternCourse, String patternDate)
            throws DaoException {
        boolean result = true;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_IS_LOGIN_UNIQUE);
            statement.setLong(1, patternCourse);
            statement.setString(2, patternDate);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = false;
            }
        } catch (SQLException | PersistentException e) {
            LOGGER.debug("SQLException in schedule" + e);
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException in schedule" + e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException in schedule" + e);
            }
        }
        return result;
    }

    private static final String SQL_SELECT_SUBJECT_BY_ID =
            "SELECT id, course_id, date_time FROM schedule WHERE id=?";

    @Override
    public Schedule findEntityById(Long id) throws DaoException {
        LOGGER.debug("Start find all teacher subjects.");
        Connection connection = null;
        PreparedStatement statement = null;
        Schedule.ScheduleBuilder scheduleBuilder =
                new Schedule.ScheduleBuilder();
        Schedule schedule = scheduleBuilder.build();
        TeacherCourse teacherCourse = new TeacherCourse();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.
                    prepareStatement(SQL_SELECT_SUBJECT_BY_ID);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                schedule.setId(resultSet.getInt(1));
                teacherCourse.setId(resultSet.getInt(2));
                scheduleBuilder.setTeacherCourse(teacherCourse).
                        setDateTime(resultSet.getString(3));
            }
        } catch (SQLException | PersistentException e) {
            LOGGER.debug("SQLException (find schedule by ID) " + e);
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (find schedule by ID) " + e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (find schedule by ID) " + e);
            }
        }
        return schedule;
    }

    @Override
    public boolean delete(Schedule schedule) throws DaoException {
        LOGGER.debug("Delete is not supported the operation.");
        throw new UnsupportedOperationException();
    }

    private static final String SQL_DELETE_SCHEDULE_BY_ID =
            "DELETE FROM schedule WHERE id=?";

    @Override
    public boolean delete(Long id) throws DaoException {
        LOGGER.debug("Start delete schedule by ID.");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.
                    prepareStatement(SQL_DELETE_SCHEDULE_BY_ID);
            statement.setString(1,
                    String.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException | PersistentException e) {
            LOGGER.debug("SQLException (schedule - delete): " + e);
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (schedule - delete): " + e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (schedule - delete): " + e);
            }
        }
        return true;
    }

    private static final String SQL_CREATE_SCHEDULE = "INSERT INTO  schedule "
            + "(course_id, date_time) VALUES(?, ?)";
    @Override
    public boolean create(Schedule schedule) throws DaoException {
        LOGGER.debug("Create schedule.");
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.
                    prepareStatement(
                            SQL_CREATE_SCHEDULE);
            statement.setLong(
                    1, schedule.
                            getTeacherCourse().getId());
            statement.setString(
                    2, schedule.getDateTime());
            statement.executeUpdate();
        } catch (SQLException | PersistentException e) {
            LOGGER.debug("SQLException " +
                    "(schedule - create): " + e);
            return false;
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException " +
                        "(schedule - create): " + e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException " +
                        "(schedule - create): " + e);
            }
        }
        return true;    }

    private static final String SQL_UPDATE_SCHEDULE =
            "UPDATE schedule SET course_id=? , date_time=? WHERE id = ?";
    @Override
    public boolean update(Schedule schedule) throws DaoException {
        LOGGER.debug("Update schedule.");
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.
                    prepareStatement(SQL_UPDATE_SCHEDULE);
            statement.setString(
                    3, String.valueOf(
                            schedule.getId()));
            statement.setLong(
                    1, schedule.
                            getTeacherCourse().getId());
            statement.setString(
                    2, schedule.getDateTime());
            statement.executeUpdate();
        } catch (SQLException | PersistentException e) {
            LOGGER.debug("SQLException " +
                    "(schedule - update): " + e);
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException " +
                        "(schedule - update): " + e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException " +
                        "(schedule - update): " + e);
            }
        }
        return true;    }

    private static final String SQL_SELECT_SCHEDULE_BY_ID_STUDENT =
            "select schedule.id, schedule.course_id, schedule.date_time " +
                    "from schedule inner join teacher_course on " +
                    "teacher_course.id = schedule.course_id inner join student_course " +
                    "on student_course.course_id = teacher_course.id " +
                    "where student_course.student_id=?";
    @Override
    public List<Schedule> findScheduleByStudent(long id)
            throws DaoException {
        LOGGER.debug("Start find schedule by Student.");
        List<Schedule> schedules = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.
                    prepareStatement(SQL_SELECT_SCHEDULE_BY_ID_STUDENT);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                Schedule.ScheduleBuilder scheduleBuilder =
                        new Schedule.ScheduleBuilder();
                Schedule schedule = scheduleBuilder.build();
                TeacherCourse teacherCourse = new TeacherCourse();
                schedule.setId(resultSet.getInt(1));
                teacherCourse.setId(resultSet.getInt(2));
                scheduleBuilder.setTeacherCourse(teacherCourse).
                        setDateTime(resultSet.getString(3));
                schedules.add(schedule);
            }
        } catch (SQLException | PersistentException e) {
            LOGGER.debug("SQLException (" +
                    "Schedule,findScheduleByStudent): " + e);
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (" +
                        "Schedule,findScheduleByStudent): " + e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (" +
                        "Schedule,findScheduleByStudent): " + e);
            }
        }
        return schedules;
    }

    private static final String SQL_SELECT_SCHEDULE_BY_ID_TEACHER =
            "select schedule.id, schedule.course_id, schedule.date_time " +
                    "from schedule inner join teacher_course " +
                    "on teacher_course.id = schedule.course_id inner join teacher_subject " +
                    "on teacher_subject.id = teacher_course.teacher_subject_id " +
                    "where teacher_subject.teacher_id=?";
    @Override
    public List<Schedule> findScheduleByTeacher(long id)
            throws DaoException {
        LOGGER.debug("Start find schedule by Student.");
        List<Schedule> schedules = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.
                    prepareStatement(SQL_SELECT_SCHEDULE_BY_ID_TEACHER);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                Schedule.ScheduleBuilder scheduleBuilder =
                        new Schedule.ScheduleBuilder();
                Schedule schedule = scheduleBuilder.build();
                TeacherCourse teacherCourse = new TeacherCourse();
                schedule.setId(resultSet.getInt(1));
                teacherCourse.setId(resultSet.getInt(2));
                scheduleBuilder.setTeacherCourse(teacherCourse).
                        setDateTime(resultSet.getString(3));
                schedules.add(schedule);
            }
        } catch (SQLException | PersistentException e) {
            LOGGER.debug("SQLException (" +
                    "Schedule,findScheduleByStudent): " + e);
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (" +
                        "Schedule,findScheduleByStudent): " + e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (" +
                        "Schedule,findScheduleByStudent): " + e);
            }
        }
        return schedules;
    }
}
