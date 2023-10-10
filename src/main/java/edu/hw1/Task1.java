package edu.hw1;

class Task1 {

    private static final int SECONDS_PER_MINUTE = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        if (!validTime(time)) {
            return -1;
        }
        String[] strings = time.split(":");
        int seconds = Integer.parseInt(strings[1]);
        int minutes = Integer.parseInt(strings[0]);

        return minutes * SECONDS_PER_MINUTE + seconds;
    }

    private static boolean validTime(String time) {
        if (time == null) {
            return false;
        }

        int delimiterIndex = -1;
        final char delimiter = ':';

        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) == delimiter && delimiterIndex == -1) {
                delimiterIndex = i;
            } else if (!Character.isDigit(time.charAt(i))) {
                return false;
            }
        }

        if (delimiterIndex <= 0 && delimiterIndex >= time.length() - 1) {
            return false;
        }

        int seconds = Integer.parseInt(time.substring(delimiterIndex + 1));
        return seconds < SECONDS_PER_MINUTE;
    }

}
