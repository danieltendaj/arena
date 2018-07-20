package com.stepstone.training.arena;

public interface Fightable {

    int attack(Creature assaulted);

    void dodge(int potentialInjury, Creature aggressor);

}
