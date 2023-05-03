package client;

import IO.ScannerManager;
import exceptions.Disconnect;
import utilities.CommandManager;
import utilities.Module;


import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        int port = ScannerManager.askPort();
        try {
            Client client = new Client("localhost", port);
            CommandManager commandManager = new CommandManager(client);

            try {
                Console.run(commandManager);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Disconnect e) {
            System.out.println(e.getMessage());
            System.out.println(3);

        }
    }
}