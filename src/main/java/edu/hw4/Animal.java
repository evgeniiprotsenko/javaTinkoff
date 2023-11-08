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
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    private static final int CATNDOGPAWS = 4;
    private static final int BIRDPAWS = 2;
    private static final int FISHPAWS = 0;
    private static final int SPIDERPAWS = 8;

    public int paws() {
        return switch (type) {
            case CAT, DOG -> CATNDOGPAWS;
            case BIRD -> BIRDPAWS;
            case FISH -> FISHPAWS;
            case SPIDER -> SPIDERPAWS;
        };
    }
}
