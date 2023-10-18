package edu.project1;

sealed interface GuessResult {

    String message();

    record SuccessGuess(String wordState) implements GuessResult {
        @Override
        public String message() {
            return "Верно! Текущее слово:" + wordState;
        }
    }

    record FailedGuess(String wordState, int mistakes, int maxMistakes) implements GuessResult {
        @Override
        public String message() {
            return String.format("К сожалению, такой буквы нет. Неудачных попыток %d из %d\n"
                    + "Текущее слово: %s", mistakes, maxMistakes, wordState);
        }
    }

    record Win(String word) implements GuessResult {
        @Override
        public String message() {
            return "Ура, Вы отгадали слово! Загаданное слово: " + word;
        }
    }

    record Defeat(String word) implements GuessResult {
        @Override
        public String message() {
            return "Вы проиграли, загаданное слово было: " + word;
        }
    }
}
