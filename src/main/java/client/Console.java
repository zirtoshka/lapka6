package client;


import IO.ConsoleManager;
import IO.ScannerManager;
import utilities.CommandManager;
import utilities.HistoryWriter;

public class Console {

    public static void run(CommandManager commandManager) throws Exception {
        System.out.println("App is working, to get more information enter \"help\"");
        String input = "run";
        while (!(input.equals("exit"))){
            input = ScannerManager.askCommand();
            ConsoleManager consoleManager = new ConsoleManager(commandManager, new HistoryWriter());
            consoleManager.toStartMode();
            System.out.println("");
        }
    }
}