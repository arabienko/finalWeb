package by.arabienko.onlineSchool.controller;

import by.arabienko.onlineSchool.dao.*;
import by.arabienko.onlineSchool.dao.mysql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DAOFactory {
    private static final Logger LOGGER =
            LogManager.getLogger(DAOFactory.class);
    private static final DAOFactory instance = new DAOFactory();

    private final static SubjectDao subjectDAO = new SubjectDaoImpl();
    private final static UserDao userDAO = new UserDaoImpl();
    private final static UserInfoDao userInfoDAO = new UserInfoDaoImpl();
    private final static TeacherSubjectDao teacherSubjectDAO = new TeacherSubjectDaoImpl();
    private final static TeacherCourseDao teacherCourseDAO = new TeacherCourseDaoImpl();
    private final static StudentCourseDao studentCourseDAO = new StudentCourseDaoImpl();
    private final static ScheduleDao scheduleDao = new ScheduleDaoImpl();

    private DAOFactory() {
    }
    public static DAOFactory getInstance(){
        LOGGER.debug("Start DAOFactory.");
        return instance;
    }
    public ScheduleDao getScheduleDao(){
        return scheduleDao;
    }
    public StudentCourseDao getStudentCourseDAO(){
        return studentCourseDAO;
    }
    public TeacherCourseDao getTeacherCourseDAO(){
        return teacherCourseDAO;
    }
    public TeacherSubjectDao getTeacherSubjectDAO(){
        return teacherSubjectDAO;
    }
    public UserInfoDao getUserInfoDAO(){
        return userInfoDAO;
    }
    public UserDao getUserDAO(){
        return userDAO;
    }
    public SubjectDao getSubjectDAO(){
        return subjectDAO;
    }
}
