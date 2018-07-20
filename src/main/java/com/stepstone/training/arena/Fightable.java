package com.stepstone.training.arena;

public interface Fightable {

    int attack(Creature creature);

    void dodge(int injury, Creature creature);

}
