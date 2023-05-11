package client;

import IO.ScannerManager;
import exceptions.Disconnect;
import exceptions.IncorrectValuesForGroupException;
import utilities.CommandManager;


import java.io.IOException;


public class App {
    public static void main(String[] args) throws IOException {
        int port = ScannerManager.askPort();
        String host=ScannerManager.askHost();
        try {
            Client client = new Client(host, port);
            CommandManager commandManager = new CommandManager(client);

            try {
                Console.run(commandManager);

            } catch (Exception e) {
                e.printStackTrace();
            } catch (IncorrectValuesForGroupException e) {
                System.out.println("Wrong data");
            }
        } catch (Disconnect e) {
            System.out.println(e.getMessage());

        }
    }
}
