package client;


import IO.ConsoleManager;
import IO.ScannerManager;
import exceptions.IncorrectValuesForGroupException;
import utilities.CommandManager;
import utilities.HistoryWriter;
import utilities.Module;

public class Console {

    public static void run(CommandManager commandManager) throws Exception, IncorrectValuesForGroupException {
        System.out.println("App is working, to get more information enter \"help\""+commandManager);
        String input = "run";
        while (!(input.equals("exit"))){
            input = ScannerManager.askCommand();
            commandManager.managerWork(input);
            System.out.println("");
        }
    }
}