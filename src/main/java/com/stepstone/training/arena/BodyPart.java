package com.stepstone.training.arena;

public enum BodyPart {

    HEAD (0.05, 3),
    LEFT_ARM (0.1, 1),
    RIGHT_ARM (0.1, 1),
    TRUNK (0.3, 0),
    LEFT_LEG (0.05, 2),
    RIGHT_LEG (0.05, 2);

    private final double probability;
    private final int bonus;

    BodyPart(double probability, int bonus) {
        this.probability = probability;
        this.bonus = bonus;
    }

    private double probability(){return probability;}
    private int bonus(){return bonus;}

}
