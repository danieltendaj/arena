package com.stepstone.training.arena.service;

import com.google.gson.Gson;
import com.stepstone.training.arena.model.Creature;
import com.stepstone.training.arena.model.CreatureType;
import com.stepstone.training.arena.model.ProtectionItem;
import com.stepstone.training.arena.model.Tournament;
import com.stepstone.training.arena.service.CreaturesFactory;
import com.stepstone.training.arena.service.FightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
public class FightController {

    @Autowired
    CreaturesFactory creaturesFactory;

    @Autowired
    FightService fightService;

    Tournament tournament;

    List<Creature> list = new ArrayList<>();

    private int capacity = 0;

    @PostMapping("/fighter")
    public String addFighter(String type, String name, int strength, int dexterity, int initiative, int endurance, int lifepoints, String protection) {

        if (tournament == null){
            return "There is no tournament yet";
        }

        if (!verifyCapacity()){
            return "Too many fighters. Maximum capacity is " + tournament.getCapacity();
        }

        if (!verifyPoints(strength, dexterity, initiative, endurance, lifepoints)){
            return "Too many points. Maximum allowed is " + tournament.getPoints();
        }

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.valueOf(protection), 1);

        Creature creature = creaturesFactory.generate(CreatureType.valueOf(type), name, strength, dexterity, initiative, endurance, lifepoints, map);

        if (list.contains(creature)){
            return name + " - there is already such creature";
        }
        else {
            list.add(creature);
            capacity++;
            return name + " succesfully created";
        }

    }

    @DeleteMapping("/fighter/{name}")
    public String removeFighter(@PathVariable String name) {

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.valueOf("HELMET"), 1);

        Creature creature = creaturesFactory.generate(CreatureType.valueOf("ELF"), name, 1, 1, 1, 1, 1, map);

        if (list.contains(creature)){
            if (list.remove(creature)) {
                capacity--;
                return name + " is no longer in the tournament";
            }
            else {
                return "Removing " + name + " from the tournament failed";
            }
        }
        else {
            return name + " was not found";
        }

    }

    @GetMapping("/fighter/{name}")
    public String getFighter(@PathVariable String name) {

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.valueOf("HELMET"), 1);

        Creature creature = creaturesFactory.generate(CreatureType.valueOf("ELF"), name, 1, 1, 1, 1, 1, map);

        if (list.contains(creature)){
            return list.get(list.indexOf(creature)).toString();
        }
        else {
            return name + " was not found";
        }

    }

    @PutMapping("/fighter/{name}")
    public String amendFighter(@PathVariable String name, String type, Integer strength, Integer dexterity, Integer initiative, Integer endurance, Integer lifepoints, String protection) {

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.valueOf(protection), 1);

        Creature creature = creaturesFactory.generate(CreatureType.valueOf(type), name, strength, dexterity, initiative, endurance, lifepoints, map);

        if (list.contains(creature)){
            list.set(list.indexOf(creature), creature);
            return name + " parameters were changed";
        }
        else {
            return name + " was not found";
        }
    }

    @PostMapping("/tournament")
    public String createTournament(int capacity, int points){

        tournament = Tournament.getInstance("Tournament");
        tournament.setCapacity(capacity);
        tournament.setPoints(points);
        return "Tournament created";
    }

    @GetMapping("/tournament")
    public String getTournament(){
        return fightService.results();
    }


    @PostMapping("/start")
    public String runTournament(){
        return fightService.tournament(list);
    }

    @CrossOrigin
    @GetMapping("/species")
    public String getCreatureTypes(){

        Gson gson = new Gson();
        return gson.toJson(CreatureType.values());

    }

    @CrossOrigin
    @GetMapping("/protections")
    public String getProtectionItems(){

        Gson gson = new Gson();
        return gson.toJson(ProtectionItem.values());

    }

    @CrossOrigin
    @GetMapping("/fighters")
    public String getFighters(){

        Gson gson = new Gson();
        return gson.toJson(list.stream().map(Creature::getName).collect(toList()));

    }

    private boolean verifyPoints(int strength, int dexterity, int initiative, int endurance, int lifepoints){

        if (strength + dexterity + initiative + endurance + lifepoints > tournament.getPoints())
            return false;
        else
            return true;
    }

    private boolean verifyCapacity(){

        if (capacity >= tournament.getCapacity())
            return false;
        else
            return true;
    }

}
