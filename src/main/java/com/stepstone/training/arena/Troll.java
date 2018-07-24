package com.stepstone.training.arena;

public class Troll extends CreatureBuilder{
    public Troll(Integer strength, Integer dexterity, Integer initiative, Integer velocity, Integer endurance, Integer numberOfAttacks, Integer numberOfDodges, Integer lifePoints) {
        setStrength(strength);
        setDexterity(dexterity);
        setInitiative(initiative);
        setVelocity(velocity);
        setEndurance(endurance);
        setNumberOfAttacks(numberOfAttacks);
        setNumberOfDodges(numberOfDodges);
        setLifePoints(lifePoints);
        setType(CreatureType.TROLL);
    }
}
