package by.arabienko.onlineSchool.controller.command;

import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CommandAction {
  CommandResult execute(HttpServletRequest request,
               HttpServletResponse response) throws PersistentException, ExceptionService, ServletException, IOException;
}
