package edu.hw8;

import edu.hw8.task1.Client;
import edu.hw8.task1.Dictionary;
import edu.hw8.task1.Server;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    void test() throws InterruptedException {
        Map<String, String> map = Map.of("личности", "Не переходи на личности там, где их нет");
        Dictionary phrases = new Dictionary(map);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        final int port = 3345;
        Server server = new Server(port, phrases);
        Thread serverThread = new Thread(server);
        serverThread.start();

        final int clients = 10;
        try (ExecutorService executorService = Executors.newFixedThreadPool(clients)) {
            for (int i = 0; i < clients; i++) {
                executorService.execute(new Client(port, List.of("личности"), new PrintStream(outputStream)));
            }
        }

        serverThread.join();

        assertThat(outputStream.toString()).isEqualTo(("Не переходи на личности там, где их нет" +
            System.lineSeparator()).repeat(clients));
    }
}
