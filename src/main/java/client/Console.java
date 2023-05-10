package client;


import IO.ScannerManager;
import exceptions.IncorrectValuesForGroupException;
import utilities.CommandManager;

import java.util.Scanner;


public class Console {

    public static void run(CommandManager commandManager) throws Exception, IncorrectValuesForGroupException {
        System.out.println("App is working, to get more information enter \"help\"" + commandManager);
        String input = "run";
        while (!(input.equals("exit"))) {
            input = ScannerManager.askCommand();
            commandManager.managerWork(input);
            System.out.println("");
        }
    }
}