package com.stepstone.training.arena.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stepstone.training.arena.service.model.AttackResult;
import com.stepstone.training.arena.service.model.creature.Creature;
import com.stepstone.training.arena.service.model.Fighters;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

import static java.util.Comparator.comparing;
import static java.util.Map.Entry.comparingByValue;

@Service
public class FightService {

    Map<Creature, Integer[]> tournamentResults = new HashMap<>();

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
            tournamentResults.put(creatureSecond, new Integer[]{tournamentResults.get(creatureSecond)[0] + 2, creatureSecond.getLifePoints()});

        }
        else{
            if (!creatureSecond.isAlive()) {
                System.out.println(creatureFirst.getName() + " won. Remained life points: " + creatureFirst.getLifePoints());
                tournamentResults.put(creatureFirst, new Integer[]{tournamentResults.get(creatureFirst)[0] + 2, creatureFirst.getLifePoints()});
            }
            else {
                System.out.println("Game ended in a draw. Remained life points: " + creatureFirst.getName() + ": " + creatureFirst.getLifePoints() + ", " + creatureSecond.getName() + ": " + creatureSecond.getLifePoints());
                tournamentResults.put(creatureFirst, new Integer[]{tournamentResults.get(creatureFirst)[0] + 1, creatureFirst.getLifePoints()});
                tournamentResults.put(creatureSecond, new Integer[]{tournamentResults.get(creatureSecond)[0] + 1, creatureSecond.getLifePoints()});
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

    public String tournament(List<Creature> creaturesList) {

        System.out.println("Creatures in the tournament:");
        for (Creature creature:creaturesList){
            System.out.println(creature.getName());
            tournamentResults.put(creature, new Integer[]{0, 0});
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

        return "Tournament started";

    }

    public String results() {
        List<Map.Entry<Creature, Integer[]>> toSort = new ArrayList<>();
        for (Map.Entry<Creature, Integer[]> creatureEntry : tournamentResults.entrySet()) {
            toSort.add(creatureEntry);
        }
        toSort.sort(Map.Entry.<Creature, Integer[]>comparingByValue(comparing(v -> v[0]))
        .thenComparing(v -> v.getValue()[1]).reversed());
        Map<String, Integer[]> tournamentSortedResults = new LinkedHashMap<>();
        for (Map.Entry<Creature, Integer[]> creatureEntry : toSort) {
            tournamentSortedResults.put(creatureEntry.getKey().getName(), creatureEntry.getValue());
        }
        //                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println();
        System.out.println("Tournament classification:");
        Integer sumPoints = new Integer(0);
        for (Map.Entry<String, Integer[]> entry : tournamentSortedResults.entrySet()){
            sumPoints = sumPoints + entry.getValue()[0];
            System.out.println("Creature: " + entry.getKey() + ", Points: " + entry.getValue()[0] + ", Remained life points: " + entry.getValue()[1]);
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .create();
        return gson.toJson(tournamentSortedResults);
    }

}
