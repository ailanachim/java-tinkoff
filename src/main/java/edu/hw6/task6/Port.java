package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class Port {
    private final int port;
    private final String user;
    private final Protocol protocol;

    public Port(int port, String user, Protocol protocol) {
        this.port = port;
        this.user = user;
        this.protocol = protocol;
    }

    public enum Protocol {
        TCP,
        UDP
    }

    public boolean isUsed() {
        if (protocol == Protocol.TCP) {
            try (var socket = new ServerSocket(port)) {
                return false;
            } catch (IOException e) {
                return true;
            }
        } else {
            try (var socket = new DatagramSocket(port)) {
                return false;
            } catch (IOException e) {
                return true;
            }
        }
    }

    public int port() {
        return port;
    }

    public String user() {
        return user;
    }

    public Protocol protocol() {
        return protocol;
    }
}
