package by.arabienko.onlineSchool.dao.mysql;

import by.arabienko.onlineSchool.dao.BaseDao;
import by.arabienko.onlineSchool.dao.TeacherCourseDao;
import by.arabienko.onlineSchool.entity.TeacherCourse;
import by.arabienko.onlineSchool.entity.TeacherSubject;
import by.arabienko.onlineSchool.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherCourseDaoImpl extends BaseDao implements TeacherCourseDao {
    private static final Logger LOGGER =
            LogManager.getLogger(TeacherCourseDaoImpl.class);
    private static final String SQL_SELECT_ALL_TEACHER_COURSE =
            "SELECT id, teacher_subject_id, start_date, end_date FROM teacher_course";
    private static final String SQL_SELECT_TEACHER_COURSE_BY_ID =
            "SELECT id, teacher_subject_id, start_date, end_date FROM teacher_course WHERE id=?";
    private static final String SQL_DELETE_TEACHER_COURSE_BY_ID =
            "DELETE FROM teacher_course WHERE id=?";
    private static final String SQL_CREATE_TEACHER_COURSE = "INSERT INTO  teacher_course "
            + "(teacher_subject_id, start_date, end_date) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE_TEACHER_COURSE =
            "UPDATE teacher_course SET teacher_subject_id=? , start_date=?, end_date=? WHERE id = ?";
    private static final String SQL_SELECT_TEACHER_COURSE_BY_START_DATE =
            "SELECT id, teacher_subject_id, start_date, end_date FROM teacher_course WHERE start_date>?";
    private static final String SQL_SELECT_TEACHER_COURSE_BY_SUBJECT =
            "SELECT tc.id, tc.teacher_subject_id, tc.start_date, tc.end_date" +
                    " FROM teacher_course tc, teacher_subject ts, subjects s, user_info t" +
                    " WHERE s.nameSubject=? AND tc.teacher_subject_id = ts.id AND ts.subject_id = s.id";

    @Override
    public List<TeacherCourse> findAll() throws DaoException {
        LOGGER.debug("Start find all teacher course.");
        List<TeacherCourse> teacherCourses = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.
                    executeQuery(SQL_SELECT_ALL_TEACHER_COURSE);
            while (resultSet.next()) {
                TeacherCourse.TeacherCourseBuilder teacherCourseBuilder =
                        new TeacherCourse.TeacherCourseBuilder();
                TeacherCourse teacherCourse = teacherCourseBuilder.build();
                TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder =
                        new TeacherSubject.TeacherSubjectBuilder();
                TeacherSubject teacherSubject = teacherSubjectBuilder.build();
                teacherCourse.setId(
                        resultSet.getInt(1));
                teacherSubject.setId(resultSet.getInt(2));
                teacherCourseBuilder.setTeacherSubject(teacherSubject).
                        setStartDate(resultSet.getString(3)).
                        setEndDate(resultSet.getString(4));

                teacherCourses.add(teacherCourse);
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException ((TeacherCourse - findAllUsers): " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException ((TeacherCourse - findAllUsers): " + e);
            }
        }
        return teacherCourses;
    }

    @Override
    public TeacherCourse findEntityById(Long id) throws DaoException {
        LOGGER.debug("Start find course teacher by ID.");
        TeacherCourse.TeacherCourseBuilder teacherCourseBuilder =
                new TeacherCourse.TeacherCourseBuilder();
        TeacherCourse teacherCourse = teacherCourseBuilder.build();
        TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder =
                new TeacherSubject.TeacherSubjectBuilder();
        TeacherSubject teacherSubject = teacherSubjectBuilder.build();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_SELECT_TEACHER_COURSE_BY_ID);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                teacherCourse.setId(
                        resultSet.getInt(1));
                teacherSubject.setId(resultSet.getInt(2));
                teacherCourseBuilder.setTeacherSubject(teacherSubject).
                        setStartDate(resultSet.getString(3)).
                        setEndDate(resultSet.getString(4));
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (TeacherCourse - FindByID): " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (TeacherCourse - FindByID): " + e);
            }
        }
        return teacherCourse;
    }

    @Override
    public boolean delete(TeacherCourse teacherCourse) throws DaoException {
        LOGGER.debug("Delete is not supported the operation.");
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        LOGGER.debug("Start delete course teacher by ID.");
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_DELETE_TEACHER_COURSE_BY_ID);
            statement.setString(1,
                    String.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug("SQLException (TeacherCourse - delete): " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (TeacherCourse - delete): " + e);
            }
        }
        return true;
    }

    @Override
    public boolean create(
            TeacherCourse teacherCourse) throws DaoException {
        LOGGER.debug("Create teacherCourse.");
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(
                            SQL_CREATE_TEACHER_COURSE);
            statement.setLong(
                    1, teacherCourse.
                            getTeacherSubject().getId());
            statement.setString(
                    2, teacherCourse.
                            getStartDate());
            statement.setString(
                    3, teacherCourse.
                            getEndDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug("SQLException " +
                    "(TeacherCourse - create): " + e);
            return false;
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException " +
                        "(TeacherCourse - create): " + e);
            }
        }
        return true;
    }

    @Override
    public boolean update(
            TeacherCourse teacherCourse) throws DaoException {
        LOGGER.debug("Update teacherCourse.");
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_UPDATE_TEACHER_COURSE);
            statement.setString(
                    4, String.valueOf(
                            teacherCourse.getId()));
            statement.setString(
                    1, String.valueOf(teacherCourse.
                            getTeacherSubject().getId()));
            statement.setString(
                    2, teacherCourse.getStartDate());
            statement.setString(
                    3, teacherCourse.getEndDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug("SQLException " +
                    "(TeacherCourse - update): " + e);
            e.printStackTrace();
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException " +
                        "(TeacherCourse - update): " + e);
            }
        }
        return true;
    }

    @Override
    public List<TeacherCourse> findCourseByStartDate(
            String namePattern) throws DaoException {
        LOGGER.debug("Start find course teacher by StartDate.");
        List<TeacherCourse> teacherCourses = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_SELECT_TEACHER_COURSE_BY_START_DATE);
            statement.setString(1, String.valueOf(namePattern));
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                TeacherCourse.TeacherCourseBuilder teacherCourseBuilder =
                        new TeacherCourse.TeacherCourseBuilder();
                TeacherCourse teacherCourse = teacherCourseBuilder.build();
                TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder =
                        new TeacherSubject.TeacherSubjectBuilder();
                TeacherSubject teacherSubject = teacherSubjectBuilder.build();
                teacherCourse.setId(
                        resultSet.getInt(1));
                teacherSubject.setId(resultSet.getInt(2));
                teacherCourseBuilder.setTeacherSubject(teacherSubject).
                        setStartDate(resultSet.getString(3)).
                        setEndDate(resultSet.getString(4));
                teacherCourses.add(teacherCourse);
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (" +
                    "TeacherCourse,findCourseByStartDate): " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (" +
                        "TeacherCourse,findCourseByStartDate): " + e);
            }
        }
        return teacherCourses;
    }

    @Override
    public List<TeacherCourse> findCourseBySubject(
            String namePattern) throws DaoException {
        LOGGER.debug("Start find course teacher by StartDate.");
        List<TeacherCourse> teacherCourses = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_SELECT_TEACHER_COURSE_BY_SUBJECT);
            statement.setString(1, String.valueOf(namePattern));
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                TeacherCourse.TeacherCourseBuilder teacherCourseBuilder =
                        new TeacherCourse.TeacherCourseBuilder();
                TeacherCourse teacherCourse = teacherCourseBuilder.build();
                TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder =
                        new TeacherSubject.TeacherSubjectBuilder();
                TeacherSubject teacherSubject = teacherSubjectBuilder.build();
                teacherCourse.setId(
                        resultSet.getInt(1));
                teacherSubject.setId(resultSet.getInt(2));
                teacherCourseBuilder.setTeacherSubject(teacherSubject).
                        setStartDate(resultSet.getString(3)).
                        setEndDate(resultSet.getString(4));
                if (!teacherCourses.contains(teacherCourse)) {
                    teacherCourses.add(teacherCourse);
                }
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (" +
                    "TeacherCourse,findCourseByStartDate): " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (" +
                        "TeacherCourse,findCourseByStartDate): " + e);
            }
        }
        return teacherCourses;
    }
}
