package com.stepstone.training.arena;

public interface Fightable {

    int attack(Creature assaulted);

    boolean dodge(int potentialInjury, Creature aggressor);

}
