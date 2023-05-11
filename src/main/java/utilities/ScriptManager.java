package utilities;

import commands.*;


public class ScriptManager {
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
    private CollectionManager collectionManager;
    private CommandManager commandManager;
    private final HistoryWriter historyWriter;

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public ScriptManager(HelpCommand helpCmd, InfoCommand infoCmd, ShowCommand showCmd, AddCommand addCmd, UpdateByIdCommand updateByIdCmd,
                         RemoveByIdCommand removeByIdCmd, ClearCommand clearCmd, SaveCommand saveCmd, ExecuteScriptCommand executeScriptCmd,
                         ExitCommand exitCmd, HeadCommand headCmd, AddIfMaxCommand addIfMaxCmd, HistoryCommand historyCmd, FilterContainsNameCommand filterContainsNameCmd,
                         PrintUniqueGroupAdminCommand printUniqueAdminCmd, PrintFieldDescendingSemesterCommand printFieldDescendingSemesterCmd, HistoryWriter historyWriter) {
        this.helpCmd = helpCmd;
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
        this.historyWriter=historyWriter;
        this.historyCmd=historyCmd;
        this.filterContainsNameCmd=filterContainsNameCmd;
        this.printFieldDescendingSemesterCmd=printFieldDescendingSemesterCmd;
        this.printUniqueAdminCmd=printUniqueAdminCmd;

    }

}
