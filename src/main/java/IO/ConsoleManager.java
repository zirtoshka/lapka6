package IO;


import exceptions.IncorrectScriptException;
import exceptions.NoAccessToFileException;
import exceptions.ScriptRecurentException;
import utilities.CommandManager;
import utilities.HistoryWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static config.ConfigData.inputCommand;
import static config.ConfigData.*;

public class ConsoleManager {
    private final CommandManager commandManager;

    private final HistoryWriter historyWriter;
    private final List<String> script = new LinkedList<>();
    private final int NAME_CMD = 0;
    private final int ARG_CMD = 1;
    private final int SUCCESSFUL_EXECUTION = 1;
    private final int NOT_SUCCESSFUL_EXECUTION = 0;





    public ConsoleManager(CommandManager commandManager, HistoryWriter historyWriter) {
        this.commandManager = commandManager;

        this.historyWriter = historyWriter;
    }

    public static void printInfoPurple(Object message) {
        System.out.println("\u001B[35m" + message + "\u001B[0m");
    }

    public static void printInfoPurpleBackground(Object message) {
        System.out.println("\u001B[45m" + message + "\u001B[0m");
    }


    public static void printError(Object message) {
        System.out.println("\u001B[33m" + message + "\u001B[0m");
    }

    public int launchCmd(String[] userCmd) throws IOException, IOException {
        String cmd = userCmd[NAME_CMD];
        String arg = userCmd[ARG_CMD];
        switch (cmd) {
            case HELP:{

                    historyWriter.addInHistory(HELP);
                    return SUCCESSFUL_EXECUTION;

                }
            case "":
                ConsoleManager.printError("Well... This is an empty line... Maybe you want to ask something?");
                break;
            case INFO:
                if (commandManager.info(arg)) {
                    historyWriter.addInHistory(INFO);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case SHOW:
                if (commandManager.show(arg)) {
                    historyWriter.addInHistory(SHOW);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case ADD:
                if (commandManager.add(arg)) {
                    historyWriter.addInHistory(ADD);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case UPDATE_BY_ID:
                if (commandManager.updateById(arg)) {
                    historyWriter.addInHistory(UPDATE_BY_ID);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case REMOVE_BY_ID:
                if (commandManager.removeById(arg)) {
                    historyWriter.addInHistory(REMOVE_BY_ID);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case CLEAR:
                if (commandManager.clear(arg)) {
                    historyWriter.addInHistory(CLEAR);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case SAVE:
                if (commandManager.save(arg)) {
                    historyWriter.addInHistory(SAVE);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case EXECUTE_SCRIPT:
                if (commandManager.executeScript(arg)) {
                    historyWriter.addInHistory(EXECUTE_SCRIPT);
                    return scriptMode(arg);
                }
            case EXIT:
                if (commandManager.exit(arg)) {
                    historyWriter.addInHistory(EXIT);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case HEAD:
                if (commandManager.head(arg)) {
                    historyWriter.addInHistory(HEAD);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case ADD_IF_MAX:
                if (commandManager.addIfMax(arg)) {
                    historyWriter.addInHistory(ADD_IF_MAX);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case HISTORY:
                if (commandManager.history(arg)) {
                    historyWriter.addInHistory(HISTORY);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case FILTER_CONTAINS_NAME:
                if (commandManager.filterContainsName(arg)) {
                    historyWriter.addInHistory(FILTER_CONTAINS_NAME);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case PRINT_UNIQUE_GROUP_ADMIN:
                if (commandManager.printUniqueAdmin(arg)) {
                    historyWriter.addInHistory(PRINT_UNIQUE_GROUP_ADMIN);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            case PRINT_FIELD_DESCENDING_SEMESTER:
                if (commandManager.printFieldDescendingSemester(arg)) {
                    historyWriter.addInHistory(PRINT_FIELD_DESCENDING_SEMESTER);
                    return SUCCESSFUL_EXECUTION;
                }
                break;
            default:
                historyWriter.addInHistory("I don't know this command:((");
                ConsoleManager.printError("No such command as in list");
                break;
        }
        return NOT_SUCCESSFUL_EXECUTION;
    }


//    public void toStartMode() throws IOException {
//        String[] userCmd = {"", ""};
//        int cmdStatus;
//        try {
//            do {
//                System.out.print(inputCommand);
//                Scanner scanner = new Scanner(System.in);
//                userCmd = (scanner.nextLine().trim() + " ").split(" ", 2);
//                userCmd[ARG_CMD] = userCmd[ARG_CMD].trim();
//                cmdStatus = launchCmd(userCmd);
//            } while (cmdStatus != 2);
//        }catch (NoSuchElementException e){
//            ConsoleManager.printError("You've entered ctrl+D, bye");
//        }




//    }

    public int scriptMode(String arg) throws IOException {
        String path;
        String[] userCmd = {"", ""};
        int cmdStatus;
        script.add(arg);
        try {
            path = System.getenv("PWD") + "/" + arg;
            File file = new File(path);
            if (file.exists() && !file.canRead()) throw new NoAccessToFileException();
            Scanner scriptScanner = new Scanner(file);
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
//            Scanner tmpScanner = ScannerManager.getScanner();
            ScannerManager.setScanner(scriptScanner);
//            ScannerManager.setFileMode();
            do {
                userCmd = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCmd[ARG_CMD] = userCmd[ARG_CMD].trim();
                while (scriptScanner.hasNextLine() && userCmd[NAME_CMD].isEmpty()) {
                    userCmd = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCmd[ARG_CMD] = userCmd[ARG_CMD].trim();
                }
                System.out.println(inputCommand + String.join(" ", userCmd));
                if (userCmd[NAME_CMD].equals(EXECUTE_SCRIPT)) {
                    for (String scri : script) {
                        if (userCmd[ARG_CMD].equals(scri)) throw new ScriptRecurentException();
                    }
                }
                cmdStatus = launchCmd(userCmd);
            } while (cmdStatus == SUCCESSFUL_EXECUTION && scriptScanner.hasNextLine());
//            ScannerManager.setScanner(tmpScanner);
//            ScannerManager.setUserMode();
            if (cmdStatus == NOT_SUCCESSFUL_EXECUTION && !userCmd[NAME_CMD].equals(EXECUTE_SCRIPT) && userCmd[ARG_CMD].isEmpty())
                throw new IncorrectScriptException();
            return cmdStatus;
        } catch (NoAccessToFileException e) {
            ConsoleManager.printError("No rules");
        } catch (NoSuchElementException e) {
            ConsoleManager.printError("I can't do anything with empty file");
        } catch (FileNotFoundException e) {
            ConsoleManager.printError("No such file with script");
        } catch (ScriptRecurentException e) {
            ConsoleManager.printError("Recurrent is cool, but I don't know how to use it");
        } catch (IncorrectScriptException e) {
            ConsoleManager.printError("Script is incorrect");
        } finally {
            script.remove(script.size() - 1);
        }
        return SUCCESSFUL_EXECUTION;
    }
}


























