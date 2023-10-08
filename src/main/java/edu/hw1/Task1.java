package edu.hw1;

class Task1 {

    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        String[] strings = time.split(":");
        if (strings.length != 2) {
            return -1;
        }

        int seconds;
        int minutes;

        try {
            seconds = Integer.parseInt(strings[1]);
            minutes = Integer.parseInt(strings[0]);
        } catch (NumberFormatException exception) {
            return -1;
        }

        final int secondsPerMinute = 60;

        if (seconds >= secondsPerMinute) {
            return -1;
        }

        seconds += minutes * secondsPerMinute;
        return seconds;
    }

}
