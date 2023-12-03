package edu.hw6;

import edu.hw6.task6.Port;
import edu.hw6.task6.Task6;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.hw6.task6.Port.Protocol.TCP;
import static edu.hw6.task6.Port.Protocol.UDP;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @Test
    void portTest() throws IOException {
        var ports = new Port[] {
            new Port(1, "TCPMUX (TCP Port Service Multiplexer)", TCP),
            new Port(2, "COMPRESSNET", TCP),
            new Port(3, "COMPRESSNET", UDP),
            new Port(5, "RJE (Remote Job Entry)", TCP),
            new Port(7, "ECHO", TCP),
            new Port(9, "DISCARD", TCP),
            new Port(11, "SYSTAT", TCP),
            new Port(13, "DAYTIME", UDP),
            new Port(17, "QOTD (Quote of the Day)", TCP),
            new Port(18, "MSP (Message Send Protocol)", TCP),
            new Port(19, "CHARGEN (Character Generator)", TCP),
            new Port(20, "FTP-DATA", TCP),
        };

        List<ServerSocket> tcpSockets = new ArrayList<>();
        List<DatagramSocket> udpSockets = new ArrayList<>();

        for (var port : ports) {
            if (port.protocol() == TCP) {
                try {
                    var socket = new ServerSocket(port.port());
                    tcpSockets.add(socket);
                } catch (Exception ignored) {

                }
            } else {
                try {
                    var socket = new DatagramSocket(port.port());
                    udpSockets.add(socket);
                } catch (Exception ignored) {
                }
            }

        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        Task6.portsUsage(ports, printStream);

        String expected = """
            Протокол  Порт   Сервис
            TCP       1      TCPMUX (TCP Port Service Multiplexer)
            TCP       2      COMPRESSNET
            UDP       3      COMPRESSNET
            TCP       5      RJE (Remote Job Entry)
            TCP       7      ECHO
            TCP       9      DISCARD
            TCP       11     SYSTAT
            UDP       13     DAYTIME
            TCP       17     QOTD (Quote of the Day)
            TCP       18     MSP (Message Send Protocol)
            TCP       19     CHARGEN (Character Generator)
            TCP       20     FTP-DATA
            """.replace("\n", System.lineSeparator());

        for (var socket : tcpSockets) {
            socket.close();
        }

        for (var socket : udpSockets) {
            socket.close();
        }

        assertThat(outputStream.toString()).isEqualTo(expected);
    }
}
