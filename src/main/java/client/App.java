package client;

import IO.ConsoleManager;
import IO.ScannerManager;
import exceptions.Disconnect;
import exceptions.IncorrectScriptException;
import exceptions.IncorrectValuesForGroupException;
import utilities.CommandManager;


import java.io.IOException;
import java.nio.channels.UnresolvedAddressException;


public class App {
    public static void main(String[] args) {
        int port = ScannerManager.askPort();
        String host = ScannerManager.askHost();
        try {
            Client client = new Client(host, port);
            CommandManager commandManager = new CommandManager(client);
            try {
                Console.run(commandManager);
            } catch (IncorrectValuesForGroupException e) {
                ConsoleManager.printError("Wrong data");
            } catch (IncorrectScriptException e) {
                ConsoleManager.printError("BAd script");
            }
        } catch (Disconnect | UnresolvedAddressException e) {
            ConsoleManager.printError(e.getMessage());
        }
    }
}
