package by.arabienko.onlineSchool.dao.mysql;

import by.arabienko.onlineSchool.dao.BaseDao;
import by.arabienko.onlineSchool.dao.UserDao;
import by.arabienko.onlineSchool.entity.User;
import by.arabienko.onlineSchool.exception.DaoException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDao implements UserDao {
    private static final Logger LOGGER =
            LogManager.getLogger(UserDaoImpl.class);

    private static final String SQL_SELECT_ALL_USERS =
            "SELECT id, login, password, role FROM users";
    private static final String SQL_SELECT_USER_BY_LOGIN =
            "SELECT id, login, password, role FROM users WHERE login=?";
    private static final String SQL_SELECT_USER_BY_PARAM =
            "SELECT id, login, password, role FROM users WHERE login=? AND password=?";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT id, login, password, role FROM users WHERE id=?";
    private static final String SQL_UPDATE_USER =
            "UPDATE users SET login=? , password=? WHERE id = ?";
    private static final String SQL_CREATE_USER = "INSERT INTO users "
            + "(login, password, role) VALUES(?, ?, ?)";
    private static final String SQL_IS_LOGIN_UNIQUE = "SELECT login FROM users WHERE login=?";
    private static final String SQL_FIND_BY_STUDENT_ID =
            "SELECT id, login, password, role  FROM users WHERE id=? AND role='3'";
    private static final String SQL_FIND_BY_TEACHER_ID =
            "SELECT id, login, password, role FROM users WHERE id=? AND role='2'";
    private static final String SQL_FIND_BY_ADMIN =
            "SELECT id, login, password, role FROM users WHERE role='0'";

    public UserDaoImpl() {
    }

    @Override
    public List<User> findAll() throws DaoException {
        LOGGER.debug("Start find all user.");
        List<User> users = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.
                    executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(
                        resultSet.getInt(1));
                user.setLogin(
                        resultSet.getString(2));
                user.setPassword(
                        resultSet.getString(3));
                user.setRole(
                        resultSet.getInt(4));
                users.add(user);
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
        return users;
    }

    @Override
    public User findEntityById(
            final Long id) throws DaoException {
        LOGGER.debug("Start find user by ID.");
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_SELECT_USER_BY_ID);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                user.setId(
                        resultSet.getInt("id"));
                user.setLogin(
                        resultSet.getString("login"));
                user.setPassword(
                        resultSet.getString("password"));
                user.setRole(
                        resultSet.getInt("role"));
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
        return user;
    }

    @Override
    public Optional<User> findEntityByParam(
            final String log, String pass) throws DaoException {
        LOGGER.debug("Start find user by param.");
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_SELECT_USER_BY_PARAM);
            statement.setString(1, String.valueOf(log));
            statement.setString(2, String.valueOf(pass));
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                user.setId(
                        resultSet.getInt("id"));
                user.setLogin(
                        resultSet.getString("login"));
                user.setPassword(
                        resultSet.getString("password"));
                user.setRole(
                        resultSet.getInt("role"));
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
        return Optional.of(user);
    }

    @Override
    public boolean delete(final User user)
            throws DaoException {
        LOGGER.debug("Delete is not supported the operation.");
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(final Long id)
            throws DaoException {
        LOGGER.debug("Delete is not supported the operation.");
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(final User user) {
        LOGGER.debug("Create user.");
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_CREATE_USER);
            statement.setString(
                    1, user.getLogin());
            statement.setString(
                    2, DigestUtils.sha512Hex(user.getPassword()));
            statement.setInt(
                    3, user.getRole());
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
    public boolean update(final User user) {
        LOGGER.debug("Update user.");
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_UPDATE_USER);
            statement.setString(
                    4, String.valueOf(user.getId()));
            statement.setString(
                    1, user.getLogin());
            statement.setString(
                    2, DigestUtils.sha512Hex
                            (user.getPassword()));
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
    public User findUserByLogin(
            final String patternName) throws DaoException {
        LOGGER.debug("Find user by login.");
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            statement.setString(
                    1, patternName);
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                user.setId(
                        resultSet.getInt("id"));
                user.setLogin(
                        resultSet.getString("login"));
                user.setPassword(
                        resultSet.getString("password"));
                user.setRole(
                        resultSet.getInt("role"));
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
        return user;
    }

    @Override
    public boolean isLoginUnique(String patternLogin)
            throws DaoException {
        boolean result = true;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_IS_LOGIN_UNIQUE);
            statement.setString(1, patternLogin);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = false;
                LOGGER.debug("User already exists ");
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
        return result;
    }

    @Override
    public boolean registerUser(User user) throws DaoException {
        if (!isLoginUnique(user.getLogin())) {
            return false;
        }
        return create(user);
    }

    @Override
    public User findStudentById(
            final Long id) throws DaoException {
        LOGGER.debug("Start find user by ID.");
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_FIND_BY_STUDENT_ID);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                user.setId(
                        resultSet.getInt("id"));
                user.setLogin(
                        resultSet.getString("login"));
                user.setPassword(
                        resultSet.getString("password"));
                user.setRole(
                        resultSet.getInt("role"));
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
        return user;
    }

    @Override
    public User findTeacherById(
            final Long id) throws DaoException {
        LOGGER.debug("Start find user by ID.");
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_FIND_BY_TEACHER_ID);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                user.setId(
                        resultSet.getInt("id"));
                user.setLogin(
                        resultSet.getString("login"));
                user.setPassword(
                        resultSet.getString("password"));
                user.setRole(
                        resultSet.getInt("role"));
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
        return user;
    }

    @Override
    public User findAdmin() throws DaoException {
        LOGGER.debug("Start find admin.");
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_FIND_BY_ADMIN);
            ResultSet resultSet =
                    statement.executeQuery();
            while (resultSet.next()) {
                user.setId(
                        resultSet.getInt("id"));
                user.setLogin(
                        resultSet.getString("login"));
                user.setPassword(
                        resultSet.getString("password"));
                user.setRole(
                        resultSet.getInt("role"));
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
        return user;
    }
}
