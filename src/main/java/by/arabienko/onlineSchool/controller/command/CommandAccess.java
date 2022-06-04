package by.arabienko.onlineSchool.controller.command;

import by.arabienko.onlineSchool.enumeration.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandAccess {
    private static final Logger LOGGER =
            LogManager.getLogger(CommandAccess.class);
    /**
     * @param role
     * @return the available command types by user
     */
    public List<CommandAction> getCommandTypesByUser(Integer role) {
        List<CommandAction> commandActionList = new ArrayList<>();
        if (role!=null) {
            if (Objects.equals(role, UserRole.ADMIN)) {
                commandActionList.addAll(getAdminCommands());
            } else if (role.equals(UserRole.STUDENT)) {
                commandActionList.addAll(getStudentCommands());
            } else if (role.equals(UserRole.TEACHER)) {
                commandActionList.addAll(getTeacherCommands());
            } else if (role.equals(UserRole.GUEST)) {
                commandActionList.addAll(getGuestCommands());
            } else {
                throw new IllegalArgumentException("Unknown role");
            }
        }else {
            commandActionList.add(CommandEnum.WELCOME.getCommand());
        }
        return commandActionList;
    }

    private List<CommandAction> getGuestCommands() {
        List<CommandAction> commandTypes = new ArrayList<>();
        commandTypes.add(CommandEnum.ABOUT.getCommand());
        commandTypes.add(CommandEnum.SELECTALL.getCommand());
        commandTypes.add(CommandEnum.REGISTER.getCommand());
        commandTypes.add(CommandEnum.TEACHERCOURSES.getCommand());
        commandTypes.add(CommandEnum.LOGIN.getCommand());
        commandTypes.add(CommandEnum.REGISTERPAGE.getCommand());
        commandTypes.add(CommandEnum.WELCOME.getCommand());
        commandTypes.add(CommandEnum.LOGINPAGE.getCommand());
        commandTypes.add(CommandEnum.USERPAGE.getCommand());
        commandTypes.add(CommandEnum.SUBJECTTEACHERCOURSE.getCommand());
        commandTypes.add(CommandEnum.STUDENTCOURSES.getCommand());

        return commandTypes;
    }

    private List<CommandAction> getTeacherCommands() {
        List<CommandAction> commandTypes = new ArrayList<>();
        commandTypes.add(CommandEnum.ABOUT.getCommand());
        commandTypes.add(CommandEnum.SELECTALL.getCommand());
        commandTypes.add(CommandEnum.REGISTER.getCommand());
        commandTypes.add(CommandEnum.LOGIN.getCommand());
        commandTypes.add(CommandEnum.REGISTERPAGE.getCommand());
        commandTypes.add(CommandEnum.WELCOME.getCommand());
        commandTypes.add(CommandEnum.LOGINPAGE.getCommand());
        commandTypes.add(CommandEnum.MYCOURSES.getCommand());
        commandTypes.add(CommandEnum.TEACHERCOURSES.getCommand());
        commandTypes.add(CommandEnum.USERPAGE.getCommand());
        commandTypes.add(CommandEnum.LOGOUT.getCommand());

        return commandTypes;
    }

    private List<CommandAction> getStudentCommands() {
        List<CommandAction> commandTypes = new ArrayList<>();
        commandTypes.add(CommandEnum.ABOUT.getCommand());
        commandTypes.add(CommandEnum.SELECTALL.getCommand());
        commandTypes.add(CommandEnum.REGISTER.getCommand());
        commandTypes.add(CommandEnum.LOGIN.getCommand());
        commandTypes.add(CommandEnum.REGISTERPAGE.getCommand());
        commandTypes.add(CommandEnum.WELCOME.getCommand());
        commandTypes.add(CommandEnum.LOGINPAGE.getCommand());
        commandTypes.add(CommandEnum.MYCOURSES.getCommand());
        commandTypes.add(CommandEnum.TEACHERCOURSES.getCommand());
        commandTypes.add(CommandEnum.STUDENTCOURSES.getCommand());
        commandTypes.add(CommandEnum.SUBJECTTEACHERCOURSE.getCommand());
        commandTypes.add(CommandEnum.USERPAGE.getCommand());
        commandTypes.add(CommandEnum.LOGOUT.getCommand());
        return commandTypes;
    }

    private List<CommandAction> getAdminCommands() {
        List<CommandAction> commandTypes = new ArrayList<>();

        return commandTypes;
    }
}
