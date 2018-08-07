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

    public List<Fighters> pairs(List<Creature> creaturesList) {


        List<Fighters> creaturePairList = new ArrayList<Fighters>();

        if (creaturesList.size() > 0) {

            List<Creature> list1 = new LinkedList(creaturesList);
            List<Creature> list2 = new LinkedList(creaturesList);

            ((LinkedList<Creature>) list2).removeFirst();

            for (Creature creature1 : list1) {
                Fighters fighters = new Fighters();

                if (creature1 != ((LinkedList<Creature>) list1).getLast()){

                    fighters.setFirstFighter(creature1);
                    for (Creature creature2 : list2) {
                        fighters.setSecondFighter(creature2);
                        creaturePairList.add(fighters);
                    }

                    ((LinkedList<Creature>) list2).removeFirst();

                }

            }

        }

        return creaturePairList;
    }

    public void tournament(List<Creature> creaturesList){

        List<Fighters> fightersList = pairs(creaturesList);
        for (Fighters fighters:fightersList){
            System.out.println("Fight between " + fighters.getFirstFighter().toString() + " and " + fighters.getSecondFighter().toString());
            fight(fighters.getFirstFighter(), fighters.getSecondFighter());
        }
    }

}
