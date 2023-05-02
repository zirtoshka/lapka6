package commands;


public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "display help on available commands");
    }

    @Override
    public boolean execute() {
        System.out.println("doecn't work"+getName());
        return true;
    }
}
