package edu.hw8.task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private final Dictionary dictionary;
    final static String EXIT_COMMAND = "exit";
    private final int port;

    public Server(int port, Dictionary dictionary) {
        this.port = port;
        this.dictionary = dictionary;
    }

    @Override
    public void run() {
        try (ServerSocket socket = new ServerSocket(port)) {
            final int waitingTime = 1000;
            socket.setSoTimeout(waitingTime);

            while (!socket.isClosed()) {
                Socket client = socket.accept();
                executorService.execute(new ClientHandler(client));
            }

            executorService.shutdown();
        } catch (SocketTimeoutException e) {
            executorService.shutdown();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class ClientHandler implements Runnable {

        private final Socket client;

        ClientHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                var outputStream = new DataOutputStream(client.getOutputStream());
                var inputStream = new DataInputStream(client.getInputStream());

                while (!client.isClosed()) {
                    String input = inputStream.readUTF();

                    if (input.equals(Server.EXIT_COMMAND)) {
                        outputStream.close();
                        inputStream.close();
                        client.close();
                        break;
                    }

                    String reply = dictionary.getReply(input);
                    outputStream.writeUTF(reply);
                    outputStream.flush();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
