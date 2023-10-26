package edu.hw3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Task5 {

    private Task5() {
    }

    public static String[] parseContacts(String[] contacts, String order) {
        if (contacts == null || contacts.length == 0) {
            return new String[0];
        }

        String[] res = Arrays.copyOf(contacts, contacts.length);

        if (Objects.equals(order, "ASC")) {
            Arrays.sort(res, Comparator.comparing(Task5::surname));
        } else if (Objects.equals(order, "DESC")) {
            Arrays.sort(res, Comparator.comparing(Task5::surname).reversed());
        }

        return res;
    }

    private static String surname(String contact) {
        for (int i = 0; i < contact.length() - 1; i++) {
            char c = contact.charAt(i);
            char next = contact.charAt(i + 1);
            if (Character.isSpaceChar(c) && !Character.isSpaceChar(next)) {
                return contact.substring(i + 1);
            }
        }
        return contact;
    }
}
