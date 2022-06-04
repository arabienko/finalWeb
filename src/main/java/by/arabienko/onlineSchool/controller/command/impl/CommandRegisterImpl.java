package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandResult;
import by.arabienko.onlineSchool.enumeration.Page;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandRegisterImpl implements CommandAction {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, ExceptionService {
        return new CommandResult(Page.REGISTER_PAGE, true);
    }
}
