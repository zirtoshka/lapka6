import org.example.IO.ConsoleManager;
import org.example.IO.ScannerManager;
import org.example.commands.*;
import org.example.utilities.CollectionManager;
import org.example.utilities.CommandManager;
import org.example.utilities.FileManager;
import org.example.utilities.HistoryWriter;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final String inputCommand = "$ ";
    public static final String inputInfo = "> ";
    public static final int NUMBER_OF_CMD = 12;

    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException {
        Scanner userScanner = new Scanner(System.in);
        HistoryWriter historyWriter = new HistoryWriter();
        ScannerManager scannerManager = new ScannerManager(userScanner);
        try {
            FileManager fileManager = new FileManager(args[0]);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            if (!fileManager.isFileEmpty()) {
                collectionManager.loadFromFile();
            } else {
                collectionManager.createCollection();
            }

            CommandManager commandManager = new CommandManager(
                    new HelpCommand(), new InfoCommand(collectionManager), new ShowCommand(collectionManager),
                    new AddCommand(collectionManager, scannerManager), new UpdateByIdCommand(collectionManager, scannerManager),
                    new RemoveByIdCommand(collectionManager), new ClearCommand(collectionManager), new SaveCommand(collectionManager),
                    new ExecuteScriptCommand(), new ExitCommand(), new HeadCommand(collectionManager), new AddIfMaxCommand(collectionManager, scannerManager),
                    new HistoryCommand(historyWriter, NUMBER_OF_CMD), new FilterContainsNameCommand(collectionManager), new PrintUniqueGroupAdminCommand(collectionManager),
                    new PrintFieldDescendingSemesterCommand(collectionManager));
            ConsoleManager consoleManager = new ConsoleManager(commandManager, scannerManager, userScanner, historyWriter);
            ConsoleManager.printInfoPurple("App  is working. If you need help, enter 'help'");
            consoleManager.toStartMode();
        } catch (ArrayIndexOutOfBoundsException e) {
            ConsoleManager.printError("I can't find name of file");
        }


    }
}