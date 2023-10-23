package edu.hw2.task3;

public class CommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public CommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    void tryExecute(String command) {
        for (int i = 0; i < maxAttempts; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
            } catch (Exception e) {

                if (i == maxAttempts - 1) {
                    throw new ExceededAttemptsNumberException(e);
                }

            }
        }
    }
}
