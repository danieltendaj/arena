package com.stepstone.training.arena;

public class FightService {
    public void fight(Creature creatureFirst, Creature creatureSecond){
        AttackResult result;

        while (creatureFirst.isAlive() && creatureSecond.isAlive()){
            result = creatureSecond.dodge(creatureFirst.attack());
            if (creatureSecond.isAlive()){
                result = creatureFirst.dodge(creatureSecond.attack());
            }
        }

    }
}
