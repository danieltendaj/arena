package com.stepstone.training.arena;

public enum BodyPart {

    HEAD (5, 3),
    LEFT_ARM (10, 1),
    RIGHT_ARM (10, 1),
    TRUNK (30, 0),
    LEFT_LEG (5, 2),
    RIGHT_LEG (5, 2);

    private final int probability;
    private final int bonus;

    private BodyPart(int probability, int bonus) {
        this.probability = probability;
        this.bonus = bonus;
    }

    public int getProbability() {
        return probability;
    }

    public int getBonus() {
        return bonus;
    }

}
