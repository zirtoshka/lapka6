package utilities;
import static config.ConfigData.NUMBER_OF_CMD;

public class HistoryWriter {
    private String[] history;

    public HistoryWriter() {
        this.history = new String[NUMBER_OF_CMD];
    }

    public String[] getHistory() {
        return history;
    }

    public void addInHistory(String newCommand) {
        for (int i = 0; i < Main.NUMBER_OF_CMD -1; i++) {
            history[i] = history[i + 1];
        }
        history[Main.NUMBER_OF_CMD -1] = newCommand;
    }
}
