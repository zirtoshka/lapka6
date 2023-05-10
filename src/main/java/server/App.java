package server;


import IO.ConsoleManager;
import utilities.FileManager;

public class App {
    public static void main(String[] args) throws Exception {
        try {


            Server server = new Server(args[0]);
            while (true) {
                server.runServer();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ConsoleManager.printError("I can't find name of file");
        }
    }
}