package edu.hw8.task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class Client implements Runnable {
    private final List<String> words;
    private final PrintStream writer;
    private final int port;

    public Client(int port, List<String> words, PrintStream outputStream) {
        if (words == null || outputStream == null) {
            throw new IllegalArgumentException();
        }
        this.port = port;
        this.words = words;
        writer = new PrintStream(outputStream);
    }

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", port)) {
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            for (String word : words) {
                outputStream.writeUTF(word);
                String reply = inputStream.readUTF();
                writer.println(reply);
            }

            outputStream.writeUTF(Server.EXIT_COMMAND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
