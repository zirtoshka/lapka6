package commands;


import utilities.HistoryWriter;

public class HistoryCommand extends Command {
    private final HistoryWriter historyWriter;
    private final int numberOfCmd;

    public HistoryCommand(HistoryWriter historyWriter, int numberOfCmd) {
        super("history", "View last " + numberOfCmd + " commands");
        this.historyWriter = historyWriter;
        this.numberOfCmd = numberOfCmd;
    }

    @Override
    public boolean execute() {
        System.out.println("doesn't work"+getName());
        return true;
    }
}

