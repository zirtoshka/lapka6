package utilities;


import IO.ScannerManager;
import client.Client;
import commands.*;
import data.StudyGroup;
import exceptions.ArgsException;
import exceptions.IncorrectScriptException;
import exceptions.IncorrectValuesForGroupException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static config.ConfigData.*;
import static data.StudyGroup.wrongId;

public class CommandManager {

    public static final List<Command> commands = new LinkedList<>();

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
        this.client = client;

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
        this.helpCmd = new HelpCommand(infoCmd, showCmd, addCmd, updateByIdCmd, removeByIdCmd, clearCmd, saveCmd,
                executeScriptCmd, exitCmd, headCmd, addIfMaxCmd, historyCmd, filterContainsNameCmd, printUniqueAdminCmd, printFieldDescendingSemesterCmd);
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

    public static String help() {
        String res = "";
        for (Command cmd : commands) {
            res += ("Command name - " + cmd.getName() + ". Command's description: " + cmd.getDescription() + "\n");
        }
        return res;
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

    public void managerWork(String s) throws IOException, IncorrectScriptException, IncorrectValuesForGroupException {
        String[] data = cmdParser(s);
        switch (data[0]) {
            case HELP: {
                System.out.println("Запускаю команду " + helpCmd.getName() + " ...");
                System.out.println(client.run(helpCmd));
                break;
            }
            case INFO: {
                System.out.println("Запускаю команду " + infoCmd.getName() + " ...");
                System.out.println(client.run(infoCmd));
                break;
            }
            case ADD: {
                StudyGroup clientGroup = ScannerManager.askGroup(addCmd.getCollectionManager());
                System.out.println("Запускаю команду " + addCmd.getName() + " ...");
                addCmd.setArgGroup(clientGroup);
                System.out.println(client.run(addCmd));
                break;
            }
            case SHOW: {
                System.out.println("Запускаю команду " + showCmd.getName() + " ...");
                System.out.println(client.run(showCmd));
                break;
            }
            case ADD_IF_MAX: {
                StudyGroup clientGroup = ScannerManager.askGroup(addIfMaxCmd.getCollectionManager());
                System.out.println("Запускаю команду " + addIfMaxCmd.getName() + " ...");
                addIfMaxCmd.setArgGroup(clientGroup);
                System.out.println(client.run(addIfMaxCmd));
                break;
            }
            case CLEAR: {
                System.out.println("Запускаю команду " + clearCmd.getName() + " ...");
                System.out.println(client.run(clearCmd));
                break;
            }
            case HEAD: {
                System.out.println("Запускаю команду " + headCmd.getName() + " ...");
                System.out.println(client.run(headCmd));
                break;
            }
            case REMOVE_BY_ID: {
                LinkedList<String> toId = new LinkedList<String>();
                int lengthData = data.length;
                boolean successGetId = false;
                Integer id = wrongId;
                while (!successGetId) {
                    try {
                        if (lengthData == 1) {
                            lengthData = 0;
                            throw new ArgsException();
                        }
                        if (lengthData > 1) {
                            toId.addLast(data[1]);
                            lengthData = 0;
                        }

                        id = Integer.parseInt(toId.getLast());
                        if (!(id > 0)) {
                            throw new NumberFormatException();
                        }
                        successGetId = true;
                    } catch (NumberFormatException e) {
                        System.out.println("It can't be id\nEnter id:");
                        toId.addLast(ScannerManager.askArgForCmd());
                    } catch (ArgsException e) {
                        System.out.println("what id is? why it is empty?\nEnter id:");
                        toId.addLast(ScannerManager.askArgForCmd());
                    }
                }
                System.out.println("Запускаю команду " + removeByIdCmd.getName() + " " + id + " ...");
                removeByIdCmd.setArgId(id);
                System.out.println(client.run(removeByIdCmd));
                break;
            }
            case EXIT: {
                System.out.println("Запускаю команду " + exitCmd.getName() + " ...");
                exitCmd.setSaveCommand(saveCmd);
                System.out.println(client.run(exitCmd));
                break;
            }
            case FILTER_CONTAINS_NAME: {
                LinkedList<String> toName = new LinkedList<String>();
                int lengthData = data.length;
                boolean successGetName = false;
                while (!successGetName) {
                    try {
                        if (lengthData == 1) {
                            lengthData = 0;
                            throw new ArgsException();
                        }
                        if (lengthData > 1) {
                            toName.addLast(data[1]);
                            lengthData = 0;
                        }
                        successGetName = true;
                    } catch (ArgsException e) {
                        System.out.println("What do I need to find??? why it is empty?\nEnter name:");
                        toName.addLast(ScannerManager.askArgForCmd());
                    }
                }
                System.out.println("Запускаю команду " + filterContainsNameCmd.getName() + " " + toName.getLast() + " ...");
                filterContainsNameCmd.setName(toName.getLast());
                System.out.println(client.run(filterContainsNameCmd));
                break;
            } case UPDATE_BY_ID:{
                LinkedList<String> toId = new LinkedList<String>();
                int lengthData = data.length;
                boolean successGetId = false;
                Integer id = wrongId;
                while (!successGetId) {
                    try {
                        if(lengthData==1)
                        {lengthData=0;
                            throw new ArgsException();
                        }
                        if (lengthData>1){
                            toId.addLast(data[1]);
                            lengthData=0;}

                        id = Integer.parseInt(toId.getLast());
                        if (!(id > 0)) {
                            throw new NumberFormatException();
                        }
                        successGetId = true;
                    } catch (NumberFormatException e) {
                        System.out.println("It can't be id\nEnter id:");
                        toId.addLast(ScannerManager.askArgForCmd());
                    } catch(ArgsException e){
                        System.out.println("what id is? why it is empty?\nEnter id:");
                        toId.addLast(ScannerManager.askArgForCmd());
                    }}
                System.out.println("Запускаю команду " + updateByIdCmd.getName() + " " + id + " ...");
                StudyGroup clientGroup = ScannerManager.askQuestionForUpdate();
                updateByIdCmd.setArgGroup(clientGroup);
                updateByIdCmd.setId(id);
                System.out.println(client.run(updateByIdCmd));
                break;
            }case PRINT_FIELD_DESCENDING_SEMESTER: {
                System.out.println("Запускаю команду " + printFieldDescendingSemesterCmd.getName() + " ...");
                System.out.println(client.run(printFieldDescendingSemesterCmd));
                break;
            }case PRINT_UNIQUE_GROUP_ADMIN: {
                System.out.println("Запускаю команду " + printUniqueAdminCmd.getName() + " ...");
                System.out.println(client.run(printUniqueAdminCmd));
                break;
            }
            default:
                System.out.println("Команда не распознана.");
                break;
        }

    }

    public String[] cmdParser(String s) {
        try {
            Scanner scanner = new Scanner(s);
            if (!(s.indexOf(" ") == -1)) {
                scanner.useDelimiter("\\s");
                String command = scanner.next();
                String data = "";
                if (scanner.hasNext()) {
                    data = scanner.next();
                }
                return new String[]{command, data};
            } else {
                String commandwodata = scanner.next();
                return new String[]{commandwodata};
            }
        } catch (NoSuchElementException e) {
            return new String[]{"  "};
        }
    }

}
