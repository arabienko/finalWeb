package by.arabienko.onlineSchool.dao.mysql;

import by.arabienko.onlineSchool.dao.BaseDao;
import by.arabienko.onlineSchool.dao.SubjectDao;
import by.arabienko.onlineSchool.entity.Subject;
import by.arabienko.onlineSchool.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImpl extends BaseDao implements SubjectDao {
    private static final Logger LOGGER =
            LogManager.getLogger(SubjectDaoImpl.class);

    private static final String SQL_SELECT_ALL_SUBJECTS =
            "SELECT id, nameSubject, description FROM subjects";
    private static final String SQL_SELECT_SUBJECT_BY_ID =
            "SELECT id, nameSubject, description FROM subjects WHERE id = ?";
    private static final String SQL_SELECT_SUBJECT_BY_NAME =
            "SELECT id, nameSubject, description FROM subjects WHERE nameSubject = ?";
    private static final String SQL_UPDATE_SUBJECT =
            "UPDATE subjects SET nameSubject = ? and description=? WHERE id = ?";
    private static final String SQL_CREATE_SUBJECT =
            "INSERT INTO subjects (nameSubject) VALUES(?,?)";
    private static final String SQL_DELETE_SUBJECT =
            "DELETE FROM subjects WHERE nameSubject=?";
    private static final String SQL_DELETE_SUBJECT_BY_ID =
            "DELETE FROM subjects WHERE id=?";
    private static final String SQL_IS_SUBJECT_UNIQUE =
            "SELECT nameSubject FROM subjects WHERE nameSubject=?";

    @Override
    public List<Subject> findAll(int offset, int noOfRecords) throws DaoException, SQLException {
        return null;
    }

    @Override
    public List<Subject> findAllEntity() throws DaoException {
        List<Subject> subjects = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.
                    executeQuery(SQL_SELECT_ALL_SUBJECTS);
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getInt(1));
                subject.setNameSubject(
                        resultSet.getString(2));
                subject.setDescription(
                        resultSet.getString(3));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (Subjects, findAll) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (Subjects, findAll) " + e);
            }
        }
        return subjects;
    }

    @Override
    public Subject findEntityById(final Long id) throws DaoException {
        Subject subject = new Subject();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_SUBJECT_BY_ID);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                subject.setId(
                        resultSet.getInt(1));
                subject.setNameSubject(
                        resultSet.getString(2));
                subject.setDescription(
                        resultSet.getString(3));
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (Subjects, findEntityById) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (Subjects, findEntityById) " + e);
            }
        }
        return subject;
    }

    @Override
    public boolean delete(final Subject subject) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_SUBJECT);
            statement.setString(1, subject.getNameSubject());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug("SQLException (Subjects, delete) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (Subjects, delete) " + e);
            }
        }
        return true;
    }

    @Override
    public boolean delete(final Long id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_DELETE_SUBJECT_BY_ID);
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug("SQLException (Subjects, delete) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (Subjects, delete) " + e);
            }
        }
        return true;
    }

    @Override
    public boolean create(final Subject subject)
            throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_CREATE_SUBJECT);
            statement.setString(
                    1, subject.getNameSubject());
            statement.setString(
                    2, subject.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug("SQLException (Subjects, create) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (Subjects, create) " + e);
            }
        }
        return true;
    }

    @Override
    public boolean update(final Subject subject)
            throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_SUBJECT);
            statement.setString(
                    1, subject.getNameSubject());
            statement.setString(
                    2, String.valueOf(subject.getId()));
            statement.setString(
                    3, String.valueOf(subject.getDescription()));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.debug("SQLException (Subjects, update) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (Subjects, update) " + e);
            }
        }
        return true;
    }

    @Override
    public Subject findSubjectByName(
            final String patternName)
            throws DaoException {
        Subject subject = new Subject();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_SELECT_SUBJECT_BY_NAME);
            statement.setString(1, patternName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                subject.setId(resultSet.getInt(1));
                subject.setNameSubject(
                        resultSet.getString(2));
                subject.setDescription(
                        resultSet.getString(3));
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (Subjects, findSubjectByName) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (Subjects, findSubjectByName) " + e);
            }
        }
        return subject;
    }

    @Override
    public boolean isUnique(String pattern)
            throws DaoException {
        boolean result = true;
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_IS_SUBJECT_UNIQUE);
            statement.setString(1, pattern);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = false;
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (Subjects, isUnique) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (Subjects, isUnique) " + e);
            }
        }
        return result;
    }
}
