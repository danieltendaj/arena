package com.stepstone.training.arena;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FightService {

    Map<Creature, Integer> tournamentResults = new HashMap<>();

    public void fight(Creature creatureFirst, Creature creatureSecond){

        int MAX_ROUND = 10;
        int round = 0;

        Map<Creature, List<AttackResult>> attacks = new HashMap<>();
        attacks.put(creatureFirst, new ArrayList<>());
        attacks.put(creatureSecond, new ArrayList<>());

        while (creatureFirst.isAlive() && creatureSecond.isAlive() && round < MAX_ROUND){
            AttackResult result = creatureSecond.dodge(creatureFirst.attack());

            attacks.get(creatureFirst).add(result);

            if (creatureSecond.isAlive()){
                result = creatureFirst.dodge(creatureSecond.attack());
                attacks.get(creatureSecond).add(result);
            }

            round++;
        }

        if (!creatureFirst.isAlive()){
            tournamentResults.put(creatureSecond, tournamentResults.get(creatureSecond) + 2);
            System.out.println(creatureSecond.getName() + " won. Remained life points: " + creatureSecond.getLifePoints());
        }
        else{
            if (!creatureSecond.isAlive()) {
                tournamentResults.put(creatureFirst, tournamentResults.get(creatureFirst) + 2);
                System.out.println(creatureFirst.getName() + " won. Remained life points: " + creatureFirst.getLifePoints());
            }
            else {
                tournamentResults.put(creatureFirst, tournamentResults.get(creatureFirst) + 1);
                tournamentResults.put(creatureSecond, tournamentResults.get(creatureSecond) + 1);
                System.out.println("Game ended in a draw. Remained life points: " + creatureFirst.getName() + ": " + creatureFirst.getLifePoints() + ", " + creatureSecond.getName() + ": " + creatureSecond.getLifePoints());
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

                if (creature1 != ((LinkedList<Creature>) list1).getLast()){

                    for (Creature creature2 : list2) {
                        Fighters fighters = new Fighters();
                        fighters.setFirstFighter(creature1);
                        fighters.setSecondFighter(creature2);
                        creaturePairList.add(fighters);
                    }

                    ((LinkedList<Creature>) list2).removeFirst();

                }

            }

        }

        return creaturePairList;
    }

    public void tournament(List<Creature> creaturesList) {

        for (Creature creature:creaturesList){
            tournamentResults.put(creature, 0);
        }

        List<Fighters> fightersList = pairs(creaturesList);

        System.out.println("Tournament results:");
        Runnable fights = () -> {
            for (Fighters fighters : fightersList) {
                System.out.println("Fight between " + fighters.getFirstFighter().toString() + " and " + fighters.getSecondFighter().toString());
                fight(fighters.getFirstFighter(), fighters.getSecondFighter());
            }
        };

        Executor executor = Executors.newCachedThreadPool();
        executor.execute(fights);
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
        for (Map.Entry<Creature, Integer> entry : tournamentSortedResults.entrySet()){
            System.out.println("Creature: " + entry.getKey().getName() + ", Points: " + entry.getValue());
        }
    }

}
