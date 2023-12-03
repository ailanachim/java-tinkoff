package edu.hw8.task3;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class PasswordHacker {

    protected final Map<String, String> userPasswords = new HashMap<>();
    protected final Map<String, String> hashToUser = new HashMap<>();
    protected final static int PASSWORDS_COUNT = 2_000_000;

    public PasswordHacker(String[] userHashPasswords) throws NoSuchAlgorithmException {
        parseData(userHashPasswords);
        searchPasswords();
    }

    public Map<String, String> getUserPasswords() {
        return userPasswords;
    }

    protected void searchPasswords() throws NoSuchAlgorithmException {
        Encryptor encryptor = new Encryptor();

        for (int i = 0; i < PASSWORDS_COUNT && !hashToUser.isEmpty(); i++) {
            String password = PasswordGenerator.getPassword(i);
            String hash = encryptor.getHash(password);
            String user = hashToUser.remove(hash);
            if (user != null) {
                userPasswords.put(user, password);
            }
        }
    }

    private void parseData(String[] userHashPasswords) {
        if (userHashPasswords == null) {
            throw new IllegalArgumentException();
        }

        for (String s : userHashPasswords) {
            String[] data = s.split("\\s+");
            if (data.length != 2) {
                throw new IllegalArgumentException("Wrong format of data");
            }
            String user = data[0];
            String hash = data[1];
            hashToUser.put(hash, user);
        }
    }
}
