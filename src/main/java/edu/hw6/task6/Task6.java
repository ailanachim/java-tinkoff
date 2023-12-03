package edu.hw6.task6;

import java.io.PrintStream;

public class Task6 {

    private Task6() {
    }

    public static void portsUsage(Port[] ports, PrintStream printStream) {
        printStream.println("Протокол  Порт   Сервис");

        for (var port : ports) {
            if (port.isUsed()) {
                String user = port.user();
                if (user == null) {
                    user = "N/A";
                }

                printStream.printf("%-8s  %-5s  %s%n", port.protocol(), port.port(), user);
            }
        }
    }
}
