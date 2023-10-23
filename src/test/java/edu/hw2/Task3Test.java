package edu.hw2;

import edu.hw2.task3.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task3Test {

    void useConnection(Connection connection) throws Exception {
        connection.execute("command");
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

        assertThat(manager.getConnection()).isInstanceOf(FaultyConnection.class);
        assertThat(manager.getConnection()).isInstanceOf(StableConnection.class);
        assertThat(manager.getConnection()).isInstanceOf(StableConnection.class);
        assertThat(manager.getConnection()).isInstanceOf(StableConnection.class);
        assertThat(manager.getConnection()).isInstanceOf(StableConnection.class);
    }

    @Test void faultyConnectionManagerTest() {
        var manager = new FaultyConnectionManager();

        assertThat(manager.getConnection()).isInstanceOf(FaultyConnection.class);
    }

    @Test
    @DisplayName("Using DefaultConnectionManager with one attempt")
    void popularCommandExecutorTest1() {
        var executor = new PopularCommandExecutor(new DefaultConnectionManager(), 1);

        assertThatThrownBy(executor::updatePackages).isInstanceOf(ExceededAttemptsNumberException.class)
            .hasCauseInstanceOf(ConnectionException.class);
    }

    @Test
    @DisplayName("Using DefaultConnectionManager with many attempts")
    void popularCommandExecutorTest2() {
        var executor = new PopularCommandExecutor(new DefaultConnectionManager(), 10);

        assertThatNoException().isThrownBy(executor::updatePackages);
    }

    @Test
    @DisplayName("Using FaultyConnectionManager")
    void popularCommandExecutorTest3() {
        var executor = new PopularCommandExecutor(new FaultyConnectionManager(), 10);

        assertThatThrownBy(executor::updatePackages).isInstanceOf(ExceededAttemptsNumberException.class);
        assertThatThrownBy(executor::updatePackages).hasCauseInstanceOf(ConnectionException.class);
    }
}
