package com.stepstone.training.arena;

abstract class CreatureBuilder {
    private Integer strength;
    private Integer dexterity;
    private Integer initiative;
    private Integer velocity;
    private Integer endurance;
    private Integer numberOfAttacks;
    private Integer numberOfDodges;
    private Integer lifePoints;
    private CreatureType type;

    public CreatureBuilder setStrength(Integer strength) {
        this.strength = strength;
        return this;
    }

    public CreatureBuilder setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
        return this;
    }

    public CreatureBuilder setInitiative(Integer initiative) {
        this.initiative = initiative;
        return this;
    }

    public CreatureBuilder setVelocity(Integer velocity) {
        this.velocity = velocity;
        return this;
    }

    public CreatureBuilder setEndurance(Integer endurance) {
        this.endurance = endurance;
        return this;
    }

    public CreatureBuilder setNumberOfAttacks(Integer numberOfAttacks) {
        this.numberOfAttacks = numberOfAttacks;
        return this;
    }

    public CreatureBuilder setNumberOfDodges(Integer numberOfDodges) {
        this.numberOfDodges = numberOfDodges;
        return this;
    }

    public CreatureBuilder setLifePoints(Integer lifePoints) {
        this.lifePoints = lifePoints;
        return this;
    }

    public CreatureBuilder setType(CreatureType type) {
        this.type = type;
        return this;
    }

    public Creature createCreature() {
        return new Creature(strength, dexterity, initiative, velocity, endurance, numberOfAttacks, numberOfDodges, lifePoints, type);
    }
}