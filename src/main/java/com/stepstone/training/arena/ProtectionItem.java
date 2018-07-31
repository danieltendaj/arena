package com.stepstone.training.arena;

public enum ProtectionItem {

    HELMET (0, 2, new BodyPart[] {BodyPart.HEAD}),
    CHESTPLATE (0, 4, new BodyPart[] {BodyPart.TRUNK}),
    GLOVES (0, 3, new BodyPart[] {BodyPart.LEFT_ARM, BodyPart.RIGHT_ARM}),
    GREAVES (0, 2, new BodyPart[] {BodyPart.LEFT_LEG, BodyPart.RIGHT_LEG}),
    SHIELD (0, 1, BodyPart.values());

    private final int minimum;
    private final int maximum;
    private BodyPart[] protectedParts;

    private ProtectionItem(int minimum, int maximum, BodyPart[] protectedParts) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.protectedParts = protectedParts;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public BodyPart[] getProtectedParts(){
        return protectedParts;
    }

}
