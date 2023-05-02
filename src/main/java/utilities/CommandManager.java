package utilities;


import IO.ConsoleManager;
import client.Client;
import commands.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static config.ConfigData.NUMBER_OF_CMD;

public class CommandManager {

    private final List<Command> commands = new LinkedList<>();

    private final HelpCommand helpCmd;
    private final InfoCommand infoCmd;
    private final ShowCommand showCmd;
    private final AddCommand addCmd;
    private final UpdateByIdCommand updateByIdCmd;
    private final RemoveByIdCommand removeByIdCmd;
    private final ClearCommand clearCmd;
    private final SaveCommand saveCmd;
    private final ExecuteScriptCommand executeScriptCmd;
    private final ExitCommand exitCmd;
    private final HeadCommand headCmd;
    private final AddIfMaxCommand addIfMaxCmd;
    private final HistoryCommand historyCmd;
    private final FilterContainsNameCommand filterContainsNameCmd;
    private final PrintUniqueGroupAdminCommand printUniqueAdminCmd;
    private final PrintFieldDescendingSemesterCommand printFieldDescendingSemesterCmd;

    private final Client client;

    public CommandManager(Client client) {
        this.client=client;
        this.helpCmd = new HelpCommand();
        this.infoCmd = new InfoCommand();
        this.showCmd = new ShowCommand();
        this.addCmd = new AddCommand();
        this.updateByIdCmd = new UpdateByIdCommand();
        this.removeByIdCmd = new RemoveByIdCommand();
        this.clearCmd = new ClearCommand();
        this.saveCmd = new SaveCommand();
        this.executeScriptCmd = new ExecuteScriptCommand();
        this.exitCmd = new ExitCommand();
        this.addIfMaxCmd = new AddIfMaxCommand();
        this.historyCmd = new HistoryCommand(new HistoryWriter(), NUMBER_OF_CMD);
        this.headCmd = new HeadCommand();
        this.filterContainsNameCmd = new FilterContainsNameCommand();
        this.printFieldDescendingSemesterCmd = new PrintFieldDescendingSemesterCommand();
        this.printUniqueAdminCmd = new PrintUniqueGroupAdminCommand();

        commands.add(helpCmd);
        commands.add(infoCmd);
        commands.add(showCmd);
        commands.add(addCmd);
        commands.add(updateByIdCmd);
        commands.add(removeByIdCmd);
        commands.add(clearCmd);
        commands.add(saveCmd);
        commands.add(executeScriptCmd);
        commands.add(exitCmd);
        commands.add(addIfMaxCmd);
        commands.add(historyCmd);
        commands.add(filterContainsNameCmd);
        commands.add(printFieldDescendingSemesterCmd);
        commands.add(printUniqueAdminCmd);
    }

    public boolean info(String arg) throws IOException {
        return infoCmd.execute();
    }

    public boolean show(String arg) throws IOException {
        return showCmd.execute();
    }

    public boolean help(String arg) throws IOException {
        if (helpCmd.execute()) {
            for (Command cmd : commands) {
                ConsoleManager.printInfoPurpleBackground("Command name - " + cmd.getName() + ". Command's description: " + cmd.getDescription());
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean add(String arg) throws IOException {
        return addCmd.execute();
    }

    public boolean updateById(String arg) throws IOException {
        return updateByIdCmd.execute();
    }

    public boolean removeById(String arg) throws IOException {
        return removeByIdCmd.execute();
    }

    public boolean clear(String arg) throws IOException {
        return clearCmd.execute();
    }

    public boolean save(String arg) throws IOException {
        return saveCmd.execute();
    }

    public boolean executeScript(String arg) throws IOException {
        return executeScriptCmd.execute();
    }

    public boolean exit(String arg) throws IOException {
        return exitCmd.execute();
    }

    public boolean head(String arg) throws IOException {
        return headCmd.execute();
    }

    public boolean addIfMax(String arg) throws IOException {
        return addIfMaxCmd.execute();
    }

    public boolean history(String arg) throws IOException {
        return historyCmd.execute();
    }

    public boolean filterContainsName(String arg) throws IOException {
        return filterContainsNameCmd.execute();
    }

    public boolean printUniqueAdmin(String arg) throws IOException {
        return printUniqueAdminCmd.execute();
    }

    public boolean printFieldDescendingSemester(String arg) throws IOException {
        return printFieldDescendingSemesterCmd.execute();
    }

}
