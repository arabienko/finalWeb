package by.arabienko.onlineSchool.controller.test;

import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.service.ScheduleService;
import by.arabienko.onlineSchool.service.impl.ScheduleServiceImpl;
import by.arabienko.onlineSchool.service.xmlparser.ServiceException;

public class TestSchedule {
    public static void main(String[] args) throws ServiceException, ExceptionService {
        ScheduleService service = new ScheduleServiceImpl();
        System.out.println(service.findScheduleByTeacher(4l));
    }
}
