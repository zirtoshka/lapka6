package commands;


import utilities.CommandManager;
import utilities.Module;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static utilities.CommandManager.help;

public class HelpCommand extends Command {
    private final List<Command> commands = new LinkedList<>();

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


    public HelpCommand(InfoCommand infoCmd, ShowCommand showCmd, AddCommand addCmd, UpdateByIdCommand updateByIdCmd, RemoveByIdCommand removeByIdCmd, ClearCommand clearCmd,
                       SaveCommand saveCmd, ExecuteScriptCommand executeScriptCmd, ExitCommand exitCmd, HeadCommand headCmd, AddIfMaxCommand addIfMaxCmd, HistoryCommand historyCmd,
                       FilterContainsNameCommand filterContainsNameCmd, PrintUniqueGroupAdminCommand printUniqueAdminCmd, PrintFieldDescendingSemesterCommand printFieldDescendingSemesterCmd) {
        super("help", "display help on available commands");
        this.infoCmd=infoCmd;
        this.showCmd=showCmd;
        this.addCmd=addCmd;
        this.updateByIdCmd=updateByIdCmd;
        this.removeByIdCmd=removeByIdCmd;
        this.clearCmd=clearCmd;
        this.saveCmd=saveCmd;
        this.executeScriptCmd=executeScriptCmd;
        this.exitCmd=exitCmd;
        this.headCmd=headCmd;
        this.addIfMaxCmd=addIfMaxCmd;
        this.historyCmd=historyCmd;
        this.filterContainsNameCmd=filterContainsNameCmd;
        this.printUniqueAdminCmd=printUniqueAdminCmd;
        this.printFieldDescendingSemesterCmd=printFieldDescendingSemesterCmd;
        commands.add(this);
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


    @Override
    public boolean execute() {
        String res="";
        for (Command cmd : commands) {
            res+=(cmd.getName() + " Desc: " + cmd.getDescription() + "\n");
        }
        Module.addMessage(res);
        return true;
    }
}
