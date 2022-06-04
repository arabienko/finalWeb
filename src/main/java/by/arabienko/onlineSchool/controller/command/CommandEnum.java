package by.arabienko.onlineSchool.controller.command;

import by.arabienko.onlineSchool.controller.command.impl.*;

/**
 * The enum Command enum.
 */
public enum CommandEnum {
    /**
     * The ABOUT.
     */
    ABOUT(new CommandAboutImpl()),
    /**
     * The  ALL_ST_COURSE.
     */
    SELECTALL(new CommandAllStudentCourseImpl()),
    /**
     * The CREATE_USER.
     */
    REGISTER(new CommandCreateUserImpl()),
    /**
     * The FIND_COURSE_BY_SUBJECT.
     */
    STUDENTCOURSES(new CommandFindCourseBySubjectImpl()),
    /**
     * The FIND_TEACH_COURSES.
     */
    TEACHERCOURSES(new CommandFindTeacherCoursesImpl()),
    /**
     * The LOGIN.
     */
    LOGIN(new CommandLoginImpl()),
    /**
     * The LOGOUT.
     */
    LOGOUT(new CommandLogoutImpl()),
    /**
     * The MY_COURSES.
     */
    MYCOURSES(new CommandMyCoursesImpl()),
    /**
     * The REGISTER.
     */
    REGISTERPAGE(new CommandRegisterImpl()),
    /**
     * The TEACH_COURSE_BY_SUBJECT.
     */
    SUBJECTTEACHERCOURSE(new CommandTeacherCourseBySubjectImpl()),
    /**
     * The EMPTY page.
     */
    WELCOME(new EmptyCommandImpl()),
    /**
     * The LOGIN_PAGE.
     */
    LOGINPAGE(new LoginPageCommandImpl()),
    /**
     * The USERINFO.
     */
    USERPAGE(new CommandUserPageImpl());

    private CommandAction command;

    CommandEnum(CommandAction command) {
        this.command = command;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public CommandAction getCommand() {
        return command;
    }

    /**
     * Gets current command.
     *
     * @param action the action
     * @return the current command
     */
    public static CommandAction getCurrentCommand(String action) {
        System.out.println(CommandEnum.valueOf(action.toUpperCase()).command);
        return CommandEnum.valueOf(action.toUpperCase()).command;
    }
}
