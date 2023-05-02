package utilities;

import commands.*;

import java.io.IOException;
import java.util.Scanner;

import static config.ConfigData.*;

public class Module {
    public static boolean running(Command command) throws IOException {
        String currentCmd = command.getName();
        Scanner scanner = new Scanner(currentCmd);
        scanner.useDelimiter("\\s");
        currentCmd = scanner.next();
        switch (currentCmd) {
            case HELP: {
                HelpCommand helpCommand = (HelpCommand) command;
                return helpCommand.execute();
            }
            case ADD: {
                AddCommand addCommand = (AddCommand) command;
                return addCommand.execute();
            }
            case ADD_IF_MAX: {
                AddIfMaxCommand addIfMaxCommand = (AddIfMaxCommand) command;
                return addIfMaxCommand.execute();
            }
            case CLEAR: {
                ClearCommand clearCommand = (ClearCommand) command;
                return clearCommand.execute();
            }
            case EXECUTE_SCRIPT: {
                ExecuteScriptCommand executeScriptCommand = (ExecuteScriptCommand) command;
                return executeScriptCommand.execute();
            }
            case EXIT: {
                ExitCommand exitCommand = (ExitCommand) command;
                return exitCommand.execute();
            }
            case FILTER_CONTAINS_NAME: {
                FilterContainsNameCommand filterContainsNameCommand = (FilterContainsNameCommand) command;
                return filterContainsNameCommand.execute();
            }
        }

    }


