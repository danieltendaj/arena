package com.stepstone.training.arena.model;

public enum BodyPart {

    HEAD (5, 3, ProtectionItem.HELMET),
    LEFT_ARM (10, 1, ProtectionItem.GLOVES),
    RIGHT_ARM (10, 1, ProtectionItem.GLOVES),
    TRUNK (30, 0, ProtectionItem.CHESTPLATE),
    LEFT_LEG (5, 2, ProtectionItem.GREAVES),
    RIGHT_LEG (5, 2, ProtectionItem.GREAVES),
    MISSED (0, 0, null);

    private final int probability;
    private final int bonus;
    private final ProtectionItem protectionItem;

    private BodyPart(int probability, int bonus, ProtectionItem protectionItem) {
        this.probability = probability;
        this.bonus = bonus;
        this.protectionItem = protectionItem;
    }

    public int getProbability() {
        return probability;
    }

    public int getBonus() {
        return bonus;
    }

    public ProtectionItem getProtectionItem() {
        return protectionItem;
    }
}
