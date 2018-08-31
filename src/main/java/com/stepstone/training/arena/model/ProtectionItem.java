package com.stepstone.training.arena.model;

public enum ProtectionItem {

    HELMET (0, 2),
    CHESTPLATE (0, 4),
    GLOVES (0, 3),
    GREAVES (0, 2),
    SHIELD (0, 1);

    private final int minimum;
    private final int maximum;

    private ProtectionItem(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }

}
