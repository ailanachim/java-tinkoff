package edu.hw8.task3;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelPasswordHacker extends PasswordHacker {

    public ParallelPasswordHacker(String[] userHashPasswords) throws NoSuchAlgorithmException {
        super(userHashPasswords);
    }

    @Override
    protected void searchPasswords() {
        final int threadsCount = 4;
        final int threadPasswordsCount = 100;
        try (ExecutorService executor = Executors.newFixedThreadPool(threadsCount)) {
            for (int i = 0; i < PASSWORDS_COUNT / threadPasswordsCount; i++) {
                int finalI = i;
                executor.execute(() -> {

                    Encryptor encryptor;
                    try {
                        encryptor = new Encryptor();
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }

                    int offset = finalI * threadPasswordsCount;
                    for (int j = 0; j < threadPasswordsCount && !hashToUser.isEmpty(); j++) {
                        String password = PasswordGenerator.getPassword(j + offset);
                        String hash = encryptor.getHash(password);
                        if (hashToUser.containsKey(hash)) {
                            synchronized (this) {
                                String user = hashToUser.remove(hash);
                                userPasswords.put(user, password);
                            }
                        }
                    }
                });
            }
        }
    }
}
