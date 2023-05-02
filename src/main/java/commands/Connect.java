package commands;

import java.io.IOException;

public class Connect extends Command{
    public Connect(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute() throws IOException {
        System.out.println("Новый клиент подключился к серверу.");
        return true;
    }
}
