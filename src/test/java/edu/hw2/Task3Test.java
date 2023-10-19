package edu.hw2;

import edu.hw2.task3.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task3Test {

    void useConnection(Connection connection) throws Exception {
        for (int i = 0; i < 10; i++) {
            connection.execute("command");
        }
        connection.close();
    }

    @Test void stableConnectionTest() {
        assertThatNoException().isThrownBy(() -> useConnection(new StableConnection()));
    }

    @Test void faultyConnectionTest() {
        assertThatThrownBy(() -> useConnection(new FaultyConnection())).isInstanceOf(ConnectionException.class);
    }

    @Test void defaultConnectionManagerTest() {
        var manager = new DefaultConnectionManager();

        for (int i = 0; i < 10; i++) {
            assertThat(manager.getConnection()).isInstanceOfAny(StableConnection.class, FaultyConnection.class);
        }
    }

    @Test void faultyConnectionManagerTest() {
        var manager = new FaultyConnectionManager();

        for (int i = 0; i < 10; i++) {
            assertThat(manager.getConnection()).isInstanceOf(FaultyConnection.class);
        }
    }

    @Test
    @DisplayName("Using DefaultConnectionManager")
    void popularCommandExecutorTest1() {
        var executor = new PopularCommandExecutor(new DefaultConnectionManager(), 10);

        for (int i = 0; i < 100; i++) {
            assertThatNoException().isThrownBy(executor::updatePackages);
        }
    }

    @Test
    @DisplayName("Using FaultyConnectionManager")
    void popularCommandExecutorTest2() {
        var executor = new PopularCommandExecutor(new FaultyConnectionManager(), 2);

        for (int i = 0; i < 100; i++) {
            try {
                executor.updatePackages();
            } catch (Exception exception) {
                assertThat(exception).isInstanceOf(ConnectionException.class);
                assertThat(exception).hasCauseInstanceOf(ConnectionException.class);
            }
        }
    }
}
