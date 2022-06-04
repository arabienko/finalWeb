package by.arabienko.onlineSchool.dao.mysql;

import by.arabienko.onlineSchool.dao.BaseDao;
import by.arabienko.onlineSchool.dao.TeacherSubjectDao;
import by.arabienko.onlineSchool.entity.Subject;
import by.arabienko.onlineSchool.entity.TeacherSubject;
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

public class TeacherSubjectDaoImpl extends BaseDao implements TeacherSubjectDao {
    private static final Logger LOGGER =
            LogManager.getLogger(TeacherSubjectDaoImpl.class);

    private static final String SQL_SELECT_ALL_TEACHER_SUBJECT =
            "SELECT ts.id, s.id,s.nameSubject, s.description, " +
                    "t.id, t.surname, t.name, t.phone, t.pathImage" +
                    " FROM teacher_subject ts, subjects s, user_info t " +
                    "WHERE ts.teacher_id = t.id AND ts.subject_id = s.id";
    private static final String SQL_CREATE_TEACHER_SUBJECT = "INSERT INTO  teacher_subject "
            + "(teacher_id, subject_id) VALUES(?, ?)";
    private static final String SQL_SELECT_TEACHER_SUBJECT_BY_ID =
            "SELECT ts.id, s.id,s.nameSubject, s.description," +
                    " t.id, t.surname, t.name, t.phone, t.pathImage" +
                    " FROM teacher_subject ts, subjects s, user_info t" +
                    " WHERE ts.id=? AND ts.teacher_id = t.id AND ts.subject_id = s.id";
    private static final String SQL_UPDATE_TEACHER_SUBJECT =
            "UPDATE teacher_subject SET teacher_id =? , subject_id=? WHERE id = ?";
    private static final String SQL_SELECT_TEACHER_SUBJECT_BY_SURNAME =
            "SELECT ts.id, s.id,s.nameSubject, s.description," +
                    " t.id, t.surname, t.name, t.phone, t.pathImage" +
                    " FROM teacher_subject ts, subjects s, user_info t" +
                    " WHERE t.surname=? AND ts.teacher_id = t.id AND ts.subject_id = s.id";
    private static final String SQL_SELECT_TEACHER_SUBJECT_BY_SUBJECT =
            "SELECT ts.id, s.id,s.nameSubject, s.description," +
                    " t.id, t.surname, t.name, t.phone, t.pathImage" +
                    " FROM teacher_subject ts, subjects s, user_info t" +
                    " WHERE s.nameSubject=? AND ts.teacher_id = t.id AND ts.subject_id = s.id";

    @Override
    public List<TeacherSubject> findAll(int offset, int noOfRecords) throws DaoException, SQLException {
        return null;
    }

    @Override
    public List<TeacherSubject> findAllEntity() throws DaoException {
        LOGGER.info("Start find all teacher subjects.");
        List<TeacherSubject> teacherSubjects = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.
                    executeQuery(SQL_SELECT_ALL_TEACHER_SUBJECT);
            while (resultSet.next()) {
                TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder =
                        new TeacherSubject.TeacherSubjectBuilder();
                TeacherSubject teacherSubject = teacherSubjectBuilder.build();
                UserInfo.UserBuilder builder = new UserInfo.UserBuilder();
                UserInfo teacher = builder.build();
                Subject subject = new Subject();
                teacherSubject.setId(
                        resultSet.getInt(1));
                subject.setId(resultSet.getInt(2));
                subject.setNameSubject(
                        resultSet.getString(3));
                subject.setDescription(
                        resultSet.getString(4));
                teacher.setId(
                        resultSet.getInt(5));
                builder.setSurname(
                        resultSet.getString(6));
                builder.setName(
                        resultSet.getString(7));
                builder.setPhone(
                        resultSet.getString(8));
                builder.setPathImage(
                        resultSet.getString(9));
                teacherSubjectBuilder.setSubject(subject);
                teacherSubjectBuilder.setUserInfo(teacher);
                teacherSubjects.add(teacherSubject);
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (findAllUsers) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (findAllUsers) " + e);
            }
        }
        return teacherSubjects;
    }

    @Override
    public TeacherSubject findEntityById(Long id) throws DaoException {
        LOGGER.info("Start find TeacherSubject by ID.");
        TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder =
                new TeacherSubject.TeacherSubjectBuilder();
        TeacherSubject teacherSubject = teacherSubjectBuilder.build();
        UserInfo.UserBuilder builder = new UserInfo.UserBuilder();
        UserInfo teacher = builder.build();
        Subject subject = new Subject();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_SELECT_TEACHER_SUBJECT_BY_ID);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                teacherSubject.setId(
                        resultSet.getInt(1));
                subject.setId(resultSet.getInt(2));
                subject.setNameSubject(
                        resultSet.getString(3));
                subject.setDescription(
                        resultSet.getString(4));
                teacher.setId(
                        resultSet.getInt(5));
                builder.setSurname(
                        resultSet.getString(6));
                builder.setName(
                        resultSet.getString(7));
                builder.setPhone(
                        resultSet.getString(8));
                builder.setPathImage(
                        resultSet.getString(9));
                teacherSubjectBuilder.setSubject(subject);
                teacherSubjectBuilder.setUserInfo(teacher);
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException " + e);
            }

        }
        return teacherSubject;
    }

    @Override
    public boolean delete(TeacherSubject teacherSubject) throws DaoException {
        LOGGER.debug("Deleting teacher subject is not supported.");
        throw new UnsupportedOperationException(
                "Deleting teacher subject is not supported.");
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        LOGGER.debug("Deleting teacher subject by ID is not supported.");
        throw new UnsupportedOperationException(
                "Deleting teacher subject by ID is not supported.");
    }

    @Override
    public boolean create(TeacherSubject teacherSubject) throws DaoException {
        LOGGER.info("Create teacherSubject.");
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_CREATE_TEACHER_SUBJECT);
            statement.setLong(
                    1, teacherSubject.getId());
            statement.setLong(
                    2, teacherSubject.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug("SQLException " + e);
            return false;
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException " + e);
            }

        }
        return true;
    }

    @Override
    public boolean update(TeacherSubject teacherSubject)
            throws DaoException {
        LOGGER.info("Update TeacherSubject.");
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_UPDATE_TEACHER_SUBJECT);
            statement.setLong(
                    3,
                    teacherSubject.getId());
            statement.setLong(
                    1,
                    teacherSubject.getUserInfo().getId());
            statement.setLong(
                    2,
                    teacherSubject.getSubject().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug("SQLException " + e);
            e.printStackTrace();
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException " + e);
            }
        }
        return true;
    }

    @Override
    public List<TeacherSubject> findTeacherSubjectBySurnameTeacher
            (String namePattern) throws DaoException {
        LOGGER.info("Start find TeacherSubject by teacher surname.");
        List<TeacherSubject> teacherSubjects = new ArrayList<>();
        UserInfo.UserBuilder builder = new UserInfo.UserBuilder();
        UserInfo teacher = builder.build();
        Subject subject = new Subject();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_SELECT_TEACHER_SUBJECT_BY_SURNAME);
            statement.setString(1, namePattern);
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder =
                        new TeacherSubject.TeacherSubjectBuilder();
                TeacherSubject teacherSubject = teacherSubjectBuilder.build();
                teacherSubject.setId(
                        resultSet.getInt(1));
                subject.setId(resultSet.getInt(2));
                subject.setNameSubject(
                        resultSet.getString(3));
                subject.setDescription(
                        resultSet.getString(4));
                teacher.setId(
                        resultSet.getInt(5));
                builder.setSurname(
                        resultSet.getString(6));
                builder.setName(
                        resultSet.getString(7));
                builder.setPhone(
                        resultSet.getString(8));
                builder.setPathImage(
                        resultSet.getString(9));
                teacherSubjectBuilder.setSubject(subject);
                teacherSubjectBuilder.setUserInfo(teacher);
                teacherSubjects.add(teacherSubject);
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException " + e);
            }
        }
        return teacherSubjects;
    }

    @Override
    public List<TeacherSubject> findTeacherSubjectBySubject
            (String namePattern) throws DaoException {
        LOGGER.info("Start find TeacherSubject by subject name.");
        List<TeacherSubject> teacherSubjects = new ArrayList<>();
        UserInfo.UserBuilder builder = new UserInfo.UserBuilder();
        UserInfo teacher = builder.build();
        Subject subject = new Subject();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_SELECT_TEACHER_SUBJECT_BY_SUBJECT);
            statement.setString(1, namePattern);
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                TeacherSubject.TeacherSubjectBuilder teacherSubjectBuilder =
                        new TeacherSubject.TeacherSubjectBuilder();
                TeacherSubject teacherSubject = teacherSubjectBuilder.build();
                teacherSubject.setId(
                        resultSet.getInt(1));
                subject.setId(resultSet.getInt(2));
                subject.setNameSubject(
                        resultSet.getString(3));
                subject.setDescription(
                        resultSet.getString(4));
                teacher.setId(
                        resultSet.getInt(5));
                builder.setSurname(
                        resultSet.getString(6));
                builder.setName(
                        resultSet.getString(7));
                builder.setPhone(
                        resultSet.getString(8));
                builder.setPathImage(
                        resultSet.getString(9));
                teacherSubjectBuilder.setSubject(subject);
                teacherSubjectBuilder.setUserInfo(teacher);
                teacherSubjects.add(teacherSubject);
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException " + e);
            }
        }
        return teacherSubjects;
    }
}
