package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.dao.mysql.ScheduleDaoImpl;
import by.arabienko.onlineSchool.entity.User;
import by.arabienko.onlineSchool.entity.UserInfo;
import by.arabienko.onlineSchool.exception.DaoException;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.service.impl.UserInfoServiceImpl;
import by.arabienko.onlineSchool.service.impl.UserServiceImpl;

public class TestS {
    public static void main(String[] args) throws DaoException, ExceptionService, PersistentException {
        UserInfoServiceImpl userService = new UserInfoServiceImpl();
        UserInfo.UserBuilder userBuilder =
                new UserInfo.UserBuilder();
        User user = new User("login", "password", 1);
        UserInfo userInfo = userBuilder.build();
        userBuilder.setName("username").
                setPhone("phone").setSurname("surname").setPathImage("pathFile").setUser(user);
        System.out.println(userService.create(userInfo));
    }
}
