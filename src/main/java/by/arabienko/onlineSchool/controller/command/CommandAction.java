package by.arabienko.onlineSchool.controller.command;

import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {
  void execute(HttpServletRequest request,
               HttpServletResponse response) throws PersistentException, ExceptionService;
}
