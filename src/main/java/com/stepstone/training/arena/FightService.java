package com.stepstone.training.arena;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FightService {

    Map<Creature, Integer> tournamentResults = new HashMap<>();

    private void fight(Creature creatureFirst, Creature creatureSecond){

        int MAX_ROUND = 10;
        int round = 0;

        Map<Creature, List<AttackResult>> attacks = new HashMap<>();
        attacks.put(creatureFirst, new ArrayList<>());
        attacks.put(creatureSecond, new ArrayList<>());

        while (creatureFirst.isAlive() && creatureSecond.isAlive() && round < MAX_ROUND){
            AttackResult result = creatureSecond.dodge(creatureFirst.attack());

            System.out.println("Fight between " + creatureFirst.toString() + " and " + creatureSecond.toString());

            attacks.get(creatureFirst).add(result);

            if (creatureSecond.isAlive()){
                result = creatureFirst.dodge(creatureSecond.attack());
                attacks.get(creatureSecond).add(result);
            }

            round++;
        }

        if (!creatureFirst.isAlive()){
            System.out.println(creatureSecond.getName() + " won. Remained life points: " + creatureSecond.getLifePoints());
            tournamentResults.put(creatureSecond, tournamentResults.get(creatureSecond) + 2);

        }
        else{
            if (!creatureSecond.isAlive()) {
                System.out.println(creatureFirst.getName() + " won. Remained life points: " + creatureFirst.getLifePoints());
                tournamentResults.put(creatureFirst, tournamentResults.get(creatureFirst) + 2);
            }
            else {
                System.out.println("Game ended in a draw. Remained life points: " + creatureFirst.getName() + ": " + creatureFirst.getLifePoints() + ", " + creatureSecond.getName() + ": " + creatureSecond.getLifePoints());
                tournamentResults.put(creatureFirst, tournamentResults.get(creatureFirst) + 1);
                tournamentResults.put(creatureSecond, tournamentResults.get(creatureSecond) + 1);
            }
        }

    }

    List<Fighters> pairs(List<Creature> creaturesList) {

        List<Fighters> creaturePairList = new ArrayList<Fighters>();

        if (creaturesList.size() > 0) {

            List<Creature> list1 = new LinkedList(creaturesList);
            List<Creature> list2 = new LinkedList(creaturesList);

            ((LinkedList<Creature>) list2).removeFirst();

            for (Creature creature1 : list1) {

                if (creature1 != ((LinkedList<Creature>) list1).getLast()){

                    for (Creature creature2 : list2) {
                        Fighters fighters = new Fighters();
                        fighters.setFirstFighter(creature1.copy());
                        fighters.setSecondFighter(creature2.copy());
                        creaturePairList.add(fighters);
                    }

                    ((LinkedList<Creature>) list2).removeFirst();

                }

            }

        }

        return creaturePairList;
    }

    public void tournament(List<Creature> creaturesList) {

        System.out.println("Creatures in the tournament:");
        for (Creature creature:creaturesList){
            System.out.println(creature.getName());
            tournamentResults.put(creature, 0);
        }
        System.out.println();

        List<Fighters> fightersList = pairs(creaturesList);

        System.out.println("Tournament results:");
        Executor executor = Executors.newCachedThreadPool();
        for (Fighters fighters : fightersList) {
            Runnable fights = () -> {
                fight(fighters.getFirstFighter(), fighters.getSecondFighter());
            };
            executor.execute(fights);
        };

        ((ExecutorService) executor).shutdown();
        try {
            ((ExecutorService) executor).awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        Map<Creature, Integer> tournamentSortedResults = tournamentResults.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println();
        System.out.println("Tournament classification:");
        Integer sumPoints = new Integer(0);
        for (Map.Entry<Creature, Integer> entry : tournamentSortedResults.entrySet()){
            sumPoints = sumPoints + entry.getValue();
            System.out.println("Creature: " + entry.getKey().getName() + ", Points: " + entry.getValue());
        }

        System.out.println("Sum of figths: " + fightersList.size());
        System.out.println("Sum of points: " + sumPoints);
    }

}
