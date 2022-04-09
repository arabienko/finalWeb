package by.arabienko.onlineSchool.controller.command;

import by.arabienko.onlineSchool.controller.command.impl.CommandAllStudentCourse;
import by.arabienko.onlineSchool.controller.command.impl.CommandCreateUser;
import by.arabienko.onlineSchool.controller.command.impl.CommandFindByNameSubject;
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

        commands.put("selectALL", new CommandAllStudentCourse());
        commands.put("course", new CommandFindByNameSubject());
        commands.put("createUser", new CommandCreateUser());

    }

    public static CommandFactory getInstance(){
        if (commandInstance == null) {
            commandInstance = new CommandFactory();
        }
        LOGGER.debug("Start working command factory.");
        return commandInstance;
    }

    public CommandAction getCommand(String command) {
        return commands.get(command);
    }

}
