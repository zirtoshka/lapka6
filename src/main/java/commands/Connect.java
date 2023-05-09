package commands;

import java.io.IOException;

public class Connect extends Command {
    public Connect(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean execute() throws IOException {
        System.out.println("A new client has connected to the server");
        return true;
    }
}
