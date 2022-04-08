package by.arabienko.onlineSchool.dao.mysql;

import by.arabienko.onlineSchool.dao.BaseDao;
import by.arabienko.onlineSchool.dao.UserInfoDao;
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

public class UserInfoDaoImpl extends BaseDao implements UserInfoDao {
    private static final Logger LOGGER =
            LogManager.getLogger(UserInfoDaoImpl.class);

    private static final String SQL_SELECT_ALL_USERS_INFO =
            "SELECT id, surname, name, phone, pathImage FROM user_info";
    private static final String SQL_SELECT_USER_INFO_BY_ID =
            "SELECT id, surname, name, phone, pathImage FROM user_info WHERE id=?";
    private static final String SQL_SELECT_ALL_TEACHER =
            "SELECT ui.id, ui.surname, ui.name, ui.phone, ui.pathImage " +
                    "FROM user_info ui inner join users on users.id = ui.id " +
                    "WHERE users.role = '1'";
    private static final String SQL_SELECT_ALL_STUDENT =
            "SELECT ui.id, ui.surname, ui.name, ui.phone, ui.pathImage " +
                    "FROM user_info ui inner join users on users.id = ui.id " +
                    "WHERE users.role = '2'";
    private static final String SQL_SELECT_ADMIN =
            "SELECT ui.id, ui.surname, ui.name, ui.phone, ui.pathImage " +
                    "FROM user_info ui inner join users on users.id = ui.id " +
                    "WHERE users.role = '0'";
    private static final String SQL_SELECT_BY_SURNAME =
            "SELECT id, surname, name, phone, pathImage " +
                    "FROM user_info WHERE surname=?";
    private static final String SQL_UPDATE_USER_INFO =
            "UPDATE user_info SET surname=?, name = ?, " +
                    "phone = ?, pathImage = ? WHERE id = ?";
    private static final String SQL_CREATE_USER_INFO = "INSERT INTO user_info "
            + "(id, surname, name, phone, pathImage) VALUES(?, ?, ?, ?, ?)";

    @Override
    public List<UserInfo> findAll()
            throws DaoException {
        LOGGER.debug("Start find all users.");
        List<UserInfo> users = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.
                    executeQuery(SQL_SELECT_ALL_USERS_INFO);
            while (resultSet.next()) {
                UserInfo.UserBuilder builder = new UserInfo.UserBuilder();
                UserInfo userInfo = builder.build();
                builder.setId(
                        resultSet.getInt(1));
                builder.setSurname(
                        resultSet.getString(2));
                builder.setName(
                        resultSet.getString(3));
                builder.setPhone(
                        resultSet.getString(4));
                builder.setPathImage(
                        resultSet.getString(5));
                users.add(userInfo);
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (findAll) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (findAll) " + e);
            }
        }
        return users;
    }

    @Override
    public UserInfo findEntityById(Long id)
            throws DaoException {
        LOGGER.debug("Start find users by id.");
        PreparedStatement statement = null;
        UserInfo.UserBuilder builder = new UserInfo.UserBuilder();
        UserInfo userInfo = builder.build();
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_INFO_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                builder.setId(
                        resultSet.getInt(1));
                builder.setSurname(
                        resultSet.getString(2));
                builder.setName(
                        resultSet.getString(3));
                builder.setPhone(
                        resultSet.getString(4));
                builder.setPathImage(
                        resultSet.getString(5));
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (findUserBySurname) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (findUserBySurname) " + e);
            }
        }
        return userInfo;
    }

    @Override
    public boolean delete(UserInfo userInfo)
            throws DaoException {
        LOGGER.debug("Deleting user is not supported.");
        throw new UnsupportedOperationException(
                "Deleting user is not supported.");
    }

    @Override
    public boolean delete(Long id)
            throws DaoException {
        LOGGER.debug("Deleting user by ID is not supported.");
        throw new UnsupportedOperationException(
                "Deleting user by ID is not supported.");
    }

    @Override
    public boolean create(UserInfo userInfo)
            throws DaoException {
        LOGGER.debug("Create user info.");
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_CREATE_USER_INFO);
            statement.setLong(1, userInfo.getId());
            statement.setString(
                    2, userInfo.getSurname());
            statement.setString(
                    3, userInfo.getName());
            statement.setString(
                    4, userInfo.getPhone());
            statement.setString(
                    5, userInfo.getPathImage());
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
    public boolean update(UserInfo userInfo) throws DaoException {
        LOGGER.debug("Update user info.");
        PreparedStatement statement = null;
        try {
            statement = connection.
                    prepareStatement(SQL_UPDATE_USER_INFO);
            statement.setString(
                    5, String.valueOf(userInfo.getId()));
            statement.setString(
                    1, userInfo.getSurname());
            statement.setString(
                    2, userInfo.getName());
            statement.executeUpdate();
            statement.setString(
                    3, userInfo.getPhone());
            statement.setString(
                    4, userInfo.getPathImage());
            statement.executeUpdate();
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
    public List<UserInfo> findUserBySurname(
            String patternName) throws DaoException {
        LOGGER.debug("Start find users by surname.");
        List<UserInfo> users = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_BY_SURNAME);
            statement.setString(1, patternName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserInfo.UserBuilder builder = new UserInfo.UserBuilder();
                UserInfo userInfo = builder.build();
                builder.setId(
                        resultSet.getInt(1));
                builder.setSurname(
                        resultSet.getString(2));
                builder.setName(
                        resultSet.getString(3));
                builder.setPhone(
                        resultSet.getString(4));
                builder.setPathImage(
                        resultSet.getString(5));
                users.add(userInfo);
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (findUserBySurname) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (findUserBySurname) " + e);
            }
        }
        return users;
    }

    @Override
    public List<UserInfo> findUserTeacher()
            throws DaoException {
        LOGGER.debug("Start find teacher users.");
        List<UserInfo> users = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.
                    executeQuery(SQL_SELECT_ALL_TEACHER);
            while (resultSet.next()) {
                UserInfo.UserBuilder builder = new UserInfo.UserBuilder();
                UserInfo user = builder.build();
                user.setId(
                        resultSet.getInt(1));
                builder.setSurname(
                        resultSet.getString(2));
                builder.setName(
                        resultSet.getString(3));
                builder.setPhone(
                        resultSet.getString(4));
                builder.setPathImage(
                        resultSet.getString(5));
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (findUserTeacher) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (findUserTeacher) " + e);
            }
        }
        return users;
    }

    @Override
    public List<UserInfo> findUserStudent()
            throws DaoException {
        LOGGER.debug("Start find student users.");
        List<UserInfo> users = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.
                    executeQuery(SQL_SELECT_ALL_STUDENT);
            while (resultSet.next()) {
                UserInfo.UserBuilder builder = new UserInfo.UserBuilder();
                UserInfo user = builder.build();
                user.setId(
                        resultSet.getInt(1));
                builder.setSurname(
                        resultSet.getString(2));
                builder.setName(
                        resultSet.getString(3));
                builder.setPhone(
                        resultSet.getString(4));
                builder.setPathImage(
                        resultSet.getString(5));
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (findUserStudent) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (findUserStudent) " + e);
            }
        }
        return users;
    }

    @Override
    public UserInfo findUserAdmin()
            throws DaoException {
        LOGGER.debug("Start find admin.");
        UserInfo.UserBuilder builder = new UserInfo.UserBuilder();
        UserInfo user = builder.build();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.
                    executeQuery(SQL_SELECT_ADMIN);
            while (resultSet.next()) {
                user.setId(
                        resultSet.getInt(1));
                builder.setSurname(
                        resultSet.getString(2));
                builder.setName(
                        resultSet.getString(3));
                builder.setPhone(
                        resultSet.getString(4));
                builder.setPathImage(
                        resultSet.getString(5));
            }
        } catch (SQLException e) {
            LOGGER.debug("SQLException (findUserAdmin) " + e);
            throw new DaoException(e);
        } finally {
            try {
                assert statement!=null;
                statement.close();
            } catch (SQLException e) {
                LOGGER.debug("SQLException (findUserAdmin) " + e);
            }
        }
        return user;
    }
}
