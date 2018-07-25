package com.stepstone.training.arena;

public class FightService {
    public void fight(Creature creatureFirst, Creature creatureSecond){
        boolean stillAlive;
        do {
            stillAlive = creatureSecond.dodge(creatureFirst.attack(creatureSecond), creatureFirst);
            if (stillAlive){
                stillAlive = creatureFirst.dodge(creatureSecond.attack(creatureFirst), creatureSecond);
            }
        } while (stillAlive);

    }
}
