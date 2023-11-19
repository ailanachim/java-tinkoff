package edu.hw6;

import edu.hw6.task6.Port;
import edu.hw6.task6.Task6;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static edu.hw6.task6.Port.Protocol.TCP;
import static edu.hw6.task6.Port.Protocol.UDP;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @Test
    void test() {
        var ports = new Port[] {
            new UsedPort(1, "TCPMUX (TCP Port Service Multiplexer)", TCP),
            new UsedPort(2, "COMPRESSNET", TCP),
            new UsedPort(3, "COMPRESSNET", UDP),
            new UsedPort(5, "RJE (Remote Job Entry)", TCP),
            new UsedPort(7, "ECHO", TCP),
            new UsedPort(9, "DISCARD", TCP),
            new UsedPort(11, "SYSTAT", TCP),
            new UsedPort(13, "DAYTIME", UDP),
            new UsedPort(17, "QOTD (Quote of the Day)", TCP),
            new UsedPort(18, "MSP (Message Send Protocol)", TCP),
            new UsedPort(19, "CHARGEN (Character Generator)", TCP),
            new UsedPort(20, "FTP-DATA", TCP),
        };

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

        assertThat(outputStream.toString()).isEqualTo(expected);
    }

    class UsedPort extends Port {

        public UsedPort(int port, String user, Protocol protocol) {
            super(port, user, protocol);
        }

        @Override
        public boolean isUsed() {
            return true;
        }
    }
}
