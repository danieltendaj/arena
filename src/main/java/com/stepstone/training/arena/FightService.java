package com.stepstone.training.arena;

import java.util.*;

public class FightService {

    public void fight(Creature creatureFirst, Creature creatureSecond){

        Map<Creature, List<AttackResult>> attacks = new HashMap<>();
        attacks.put(creatureFirst, new ArrayList<>());
        attacks.put(creatureSecond, new ArrayList<>());

        while (creatureFirst.isAlive() && creatureSecond.isAlive()){
            AttackResult result = creatureSecond.dodge(creatureFirst.attack());

            attacks.get(creatureFirst).add(result);

            if (creatureSecond.isAlive()){
                result = creatureFirst.dodge(creatureSecond.attack());
                attacks.get(creatureSecond).add(result);
            }
        }

    }

    public List<Fighters> pairs(List<Creature> creaturesList){

        Fighters fighters = new Fighters();
        List<Fighters> creaturePairList = new ArrayList<Fighters>();

        if (creaturesList.size() == 1){
            fighters.setFirstFighter(creaturesList.get(0));
            fighters.setSecondFighter(creaturesList.get(0));
            creaturePairList.add(fighters);
        }

        if (creaturesList.size() > 1){
            fighters.setFirstFighter(creaturesList.get(0));
            fighters.setSecondFighter(creaturesList.get(1));
            creaturePairList.add(fighters);
        }

        return creaturePairList;
    }

}
