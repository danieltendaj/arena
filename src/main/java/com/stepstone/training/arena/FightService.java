package com.stepstone.training.arena;

public class FightService {
    public void fight(Creature creatureFirst, Creature creatureSecond){
        creatureSecond.dodge(creatureFirst.attack(creatureSecond), creatureSecond);
    }
}
