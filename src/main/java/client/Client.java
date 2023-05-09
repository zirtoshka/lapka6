package client;


import commands.Connect;
import exceptions.Disconnect;

import static config.ConfigData.CAPACITY_BUFFER;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    private String host;
    private int port;
    private SocketChannel client;
    private Serializer serializer;
    private Deserializer deserializer;
    private ByteBuffer buffer;

    public Client(String h, int p) throws Disconnect {
        this.host = h;
        this.port = p;
        serializer = new Serializer();
        deserializer = new Deserializer();
        buffer = ByteBuffer.allocate(CAPACITY_BUFFER);
        System.out.println(1);
        findServer();
        System.out.println(2);
    }

    public String run(Object o1) {
        String out = "";
        try {
            connect();
            sendObject(o1);
            out = (String) getObject();
            close();
        } catch (IOException e) {
            System.out.println(e);
            return "ohh(( No connection with the server";
        }
        return out;
    }

    private void connect() throws IOException {
        client = SocketChannel.open(new InetSocketAddress(host, port));
        client.configureBlocking(false);
    }

    private void sendObject(Object object) throws IOException {
        client.write(serializer.serialize(object));
    }

    private Object getObject() {
        while (true) {
            try {
                client.read(buffer);
                Object o = deserializer.deserialize(buffer);
                buffer = ByteBuffer.allocate(CAPACITY_BUFFER);
                return o;
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }

    private void close() throws IOException {
        client.close();
    }

    private void findServer() throws Disconnect {
        System.out.println("Connecting to the server...");
        String result = run(new Connect("connect", "Connecting to the server"));
        if (!(result.equals("Execution is successful\n"))) {
            System.out.println(result);
            throw new Disconnect("No connection");
        }
        System.out.println(result);
    }


}