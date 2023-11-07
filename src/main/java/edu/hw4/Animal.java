package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER;

        @SuppressWarnings("MagicNumber")
        public int paws() {
            return switch (this) {
                case CAT, DOG -> 4;
                case BIRD -> 2;
                case FISH -> 0;
                case SPIDER -> 8;
            };
        }
    }

    enum Sex {
        M, F
    }

    public int paws() {
        return type.paws();
    }
}
