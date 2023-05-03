package server;


import commands.Command;
import commands.SaveCommand;
import utilities.CollectionManager;
import utilities.FileManager;
import utilities.Module;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int port;
    private Socket socket;

    private ServerSocket server;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private InputStream stream;

    public Server() {
        this.port = 2022;
        boolean connect = false;
        while (!connect) {
            try {
                server = new ServerSocket(port);
                connect = true;
                System.out.println("Сервер поднят и доступен по порту " + port + " .");
            } catch (Exception e) {
                port = (int) (Math.random() * 20000 + 10000);
            }
        }
        stream = System.in;
        Module.setCollectionManager(new CollectionManager(new FileManager("test.yml")));
    }

    public void run() {
        try {
            connect();
            Command command = null;
            while (command == null) {
                try {
                    command = (Command) getObject();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("ffff");
//                    System.exit(0);
                }
            }
            boolean result = Module.running(command);
            if (result) {
                Module.addMessage("Выполнение успешно.");
            } else {
                Module.addMessage("Выполнить команду не удалось.");
            }
            sendObject(Module.messageFlush());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (stream.available() > 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                if (reader.readLine().equals("save")) {
                    SaveCommand save = new SaveCommand();
                    save.setCollectionManager(Module.getCollectionManager());
                    save.execute();
                    System.out.println("Коллекция сохранена.");
                }
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

    private void connect() throws IOException {
        socket = server.accept();
    }

    private Object getObject() throws Exception {
        inputStream = new ObjectInputStream(socket.getInputStream());
        return inputStream.readObject();
    }

    public void close() throws IOException {
        server.close();
    }

    private void sendObject(Object o) throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(o);
        outputStream.flush();
    }
}