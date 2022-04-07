package by.arabienko.onlineSchool.dao.mysql;

import by.arabienko.onlineSchool.dao.BaseDao;
import by.arabienko.onlineSchool.dao.StudentCourseDao;
import by.arabienko.onlineSchool.entity.StudentCourse;
import by.arabienko.onlineSchool.entity.TeacherCourse;
import by.arabienko.onlineSchool.entity.UserInfo;
import by.arabienko.onlineSchool.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDaoImpl extends BaseDao implements StudentCourseDao {
    private static final Logger LOGGER =
            LogManager.getLogger(StudentCourseDaoImpl.class);

    private static final String SQL_SELECT_ALL_STUDENT_COURSE =
            "SELECT id, course_id, student_id, status FROM student_course";

    @Override
    public List<StudentCourse> findAll() throws DaoException {
        LOGGER.debug("Start find all student course.");
        List<StudentCourse> studentCourses = new ArrayList<>();
        Statement statement = null;
        if (connection==null) {
            LOGGER.error("ERROR Connection");
        }
        try {
            LOGGER.debug("Start find all student course.222");
            statement = connection.createStatement();
            LOGGER.debug("Start find all student course.222");
            ResultSet resultSet = statement.
                    executeQuery(SQL_SELECT_ALL_STUDENT_COURSE);
            LOGGER.debug("Start find all student course.");
            while (resultSet.next()) {
                StudentCourse.StudentCourseBuilder studentCourseBuilder =
                        new StudentCourse.StudentCourseBuilder();
                StudentCourse studentCourse = studentCourseBuilder.build();
                TeacherCourse.TeacherCourseBuilder teacherCourseBuilder =
                        new TeacherCourse.TeacherCourseBuilder();
                TeacherCourse teacherCourse = teacherCourseBuilder.build();
                UserInfo.UserBuilder userBuilder = new UserInfo.UserBuilder();
                UserInfo userInfo = userBuilder.build();
                studentCourse.setId(
                        resultSet.getInt(1));
                teacherCourse.setId(resultSet.getInt(2));
                userBuilder.setId(resultSet.getInt(3));
                studentCourseBuilder.setTeacherCourse(teacherCourse).
                        setUserInfo(userInfo).setStatus(
                                resultSet.getString(4));
                studentCourses.add(studentCourse);
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException ((StudentCourse - findAllUsers): " + e);
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException ((StudentCourse - findAllUsers): " + e);
            }
        }
        return studentCourses;
    }

    private static final String SQL_SELECT_STUDENT_COURSE_BY_ID =
            "SELECT id, course_id, student_id, status FROM student_course WHERE id=?";

    @Override
    public StudentCourse findEntityById(Long id) throws DaoException {
        LOGGER.debug("Start find course student by ID.");
        StudentCourse.StudentCourseBuilder studentCourseBuilder =
                new StudentCourse.StudentCourseBuilder();
        StudentCourse studentCourse = studentCourseBuilder.build();
        TeacherCourse.TeacherCourseBuilder teacherCourseBuilder =
                new TeacherCourse.TeacherCourseBuilder();
        TeacherCourse teacherCourse = teacherCourseBuilder.build();
        UserInfo.UserBuilder userBuilder = new UserInfo.UserBuilder();
        UserInfo userInfo = userBuilder.build();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_SELECT_STUDENT_COURSE_BY_ID);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                studentCourse.setId(
                        resultSet.getInt(1));
                teacherCourse.setId(resultSet.getInt(2));
                userBuilder.setId(resultSet.getInt(3));
                studentCourseBuilder.setTeacherCourse(teacherCourse).
                        setUserInfo(userInfo).setStatus(
                                resultSet.getString(4));
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (studentCourse - FindByID): " + e);
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (studentCourse - FindByID): " + e);
            }
        }
        return studentCourse;
    }

    @Override
    public boolean delete(StudentCourse studentCourse) throws DaoException {
        LOGGER.debug("Deleting subject is not supported.");
        throw new UnsupportedOperationException(
                "Deleting subject is not supported.");
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        LOGGER.debug("Deleting subject is not supported.");
        throw new UnsupportedOperationException(
                "Deleting subject is not supported.");
    }

    private static final String SQL_CREATE_STUDENT_COURSE = "INSERT INTO  student_course "
            + "( course_id, student_id, status) VALUES(?, ?, ?)";

    @Override
    public boolean create(StudentCourse studentCourse) throws DaoException {
        LOGGER.debug("Create teacherCourse.");
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(
                            SQL_CREATE_STUDENT_COURSE);
            statement.setLong(
                    1, studentCourse.
                            getTeacherCourse().getId());
            statement.setLong(
                    2, studentCourse.
                            getUserInfo().getId());
            statement.setString(
                    3, studentCourse.
                            getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug("SQLException " +
                    "(studentCourse - create): " + e);
            return false;
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException " +
                        "(studentCourse - create): " + e);
            }
        }
        return true;
    }

    private static final String SQL_UPDATE_STUDENT_COURSE =
            "UPDATE student_course SET course_id=? , student_id=?, status=? WHERE id = ?";

    @Override
    public boolean update(StudentCourse studentCourse) throws DaoException {
        LOGGER.debug("Update studentCourse.");
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_UPDATE_STUDENT_COURSE);
            statement.setString(
                    4, String.valueOf(
                            studentCourse.getId()));
            statement.setString(
                    1, String.valueOf(studentCourse.
                            getTeacherCourse().getId()));
            statement.setString(
                    2, String.valueOf(studentCourse.
                            getUserInfo().getId()));
            statement.setString(
                    3, studentCourse.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug("SQLException " +
                    "(studentCourse - update): " + e);
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException " +
                        "(studentCourse - update): " + e);
            }
        }
        return true;
    }

    private static final String SQL_SELECT_STUDENT_COURSE_BY_SUBJECT =
            "SELECT sc.id, sc.course_id, sc.student_id, sc.status" +
                    " FROM student_course sc, teacher_course tc, teacher_subject ts, subjects s" +
                    " WHERE s.nameSubject=? AND s.id=ts.subject_id AND tc.teacher_subject_id = ts.id " +
                    "AND tc.id = sc.course_id";

    @Override
    public List<StudentCourse> findStudentCourseBySubject(String namePattern) throws DaoException {
        LOGGER.debug("Start find course teacher by subject.");
        List<StudentCourse> studentCourses = new ArrayList<>();
        StudentCourse.StudentCourseBuilder studentCourseBuilder =
                new StudentCourse.StudentCourseBuilder();
        StudentCourse studentCourse = studentCourseBuilder.build();
        TeacherCourse.TeacherCourseBuilder teacherCourseBuilder =
                new TeacherCourse.TeacherCourseBuilder();
        TeacherCourse teacherCourse = teacherCourseBuilder.build();
        UserInfo.UserBuilder userBuilder = new UserInfo.UserBuilder();
        UserInfo userInfo = userBuilder.build();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_SELECT_STUDENT_COURSE_BY_SUBJECT);
            statement.setString(1, String.valueOf(namePattern));
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                studentCourse.setId(
                        resultSet.getInt(1));
                teacherCourse.setId(resultSet.getInt(2));
                userBuilder.setId(resultSet.getInt(3));
                studentCourseBuilder.setTeacherCourse(teacherCourse).
                        setUserInfo(userInfo).setStatus(
                                resultSet.getString(4));
                if (!studentCourses.contains(studentCourse)) {
                    studentCourses.add(studentCourse);
                }
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (" +
                    "studentCourse,findCourseByStartDate): " + e);
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (" +
                        "studentCourse,findCourseByStartDate): " + e);
            }
        }
        return studentCourses;
    }

    private static final String SQL_IS_STUDENT_COURSE_UNIQUE =
            "SELECT course_id , student_id FROM student_course WHERE course_id=? AND student_id=?";

    @Override
    public boolean isUnique(long patternCourse, long patternStudent)
            throws DaoException {
        LOGGER.debug("Is the data unique (student course)");
        boolean result = true;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_IS_STUDENT_COURSE_UNIQUE);
            statement.setLong(1, patternCourse);
            statement.setLong(2, patternStudent);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = false;
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (studentCourse, isUnique) " + e);
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (studentCourse, isUnique) " + e);
            }
        }
        return result;
    }
}
