package by.arabienko.onlineSchool.controller.command.impl;

import by.arabienko.onlineSchool.controller.command.CommandAction;
import by.arabienko.onlineSchool.controller.command.CommandResult;
import by.arabienko.onlineSchool.enumeration.Page;
import by.arabienko.onlineSchool.exception.ExceptionService;
import by.arabienko.onlineSchool.exception.PersistentException;
import by.arabienko.onlineSchool.view.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Empty command:
 * to WELCOME page
 */
public class EmptyCommandImpl implements CommandAction {
    @Override
    public CommandResult execute(
            HttpServletRequest request, HttpServletResponse response)
            throws PersistentException, ExceptionService {
        request.setAttribute("temper", MessageManager.getMessages("client"));
        return new CommandResult(Page.WELCOME_PAGE,true);
    }
}
