package by.arabienko.onlineSchool.controller.command;

import by.arabienko.onlineSchool.controller.command.impl.*;
import by.arabienko.onlineSchool.enumeration.JspCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class CommandFactory {
    private static final Logger LOGGER =
            LogManager.getLogger(CommandFactory.class);
    private static CommandFactory commandInstance;
    private HashMap<String, CommandAction> commands;

    public CommandFactory() {
        commands = new HashMap<>();

        commands.put(JspCommand.COURSES, new CommandAllStudentCourseImpl());
        commands.put(JspCommand.FIND_COURSE, new CommandFindCourseBySubjectImpl());
        commands.put(JspCommand.CREATE_USER, new CommandCreateUserImpl());
        commands.put(JspCommand.LOGIN, new CommandLoginImpl());
        commands.put(JspCommand.WELCOME, new EmptyCommandImpl());
        commands.put(JspCommand.FIND_ALL_COURSES, new CommandFindTeacherCoursesImpl());
        commands.put(JspCommand.LOGIN_PAGE, new LoginPageCommandImpl());
        commands.put(JspCommand.LOGOUT, new CommandLogoutImpl());
        commands.put(JspCommand.REGISTER, new CommandRegisterImpl());
        commands.put(JspCommand.MY_COURSES, new CommandMyCoursesImpl());
        commands.put(JspCommand.FIND_COURSES_BY_SUBJECT, new CommandTeacherCourseBySubjectImpl());
        commands.put(JspCommand.ABOUT, new CommandAboutImpl());
        commands.put(JspCommand.USER_PAGE, new CommandUserPageImpl());
    }

    public static CommandFactory getInstance() {
        if (commandInstance==null) {
            commandInstance = new CommandFactory();
        }
        LOGGER.debug("Start working command factory.");
        return commandInstance;
    }

    public CommandAction getCommand(String command) {
        return commands.get(command);
    }

}
