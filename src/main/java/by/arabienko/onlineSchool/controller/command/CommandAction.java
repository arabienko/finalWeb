package by.arabienko.onlineSchool.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {
  void execute(HttpServletRequest request,
               HttpServletResponse response);
}
