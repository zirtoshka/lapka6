package commands;


import IO.ConsoleManager;
import exceptions.ArgsException;

import java.io.IOException;

public class ExecuteScriptCommand extends Command {
    public ExecuteScriptCommand() {
        super("execute_script <file_name>", "use script from file");
    }

    public boolean execute(String arg) {
        try {
            if (arg.isEmpty()) throw new ArgsException();
            ConsoleManager.printSuccess("Script? " + arg + " Yes, something's going on in my head ...");
            return true;
        } catch (ArgsException e) {
            ConsoleManager.printError("I know that silence is golden. But I can't understand a file's name");
        }
        return false;
    }

    @Override
    public boolean execute() throws IOException {
        return true;
    }
}
