package com.stepstone.training.arena;

import com.google.gson.Gson;
import com.stepstone.training.arena.model.Creature;
import com.stepstone.training.arena.model.CreatureType;
import com.stepstone.training.arena.model.ProtectionItem;
import com.stepstone.training.arena.service.CreaturesFactory;
import com.stepstone.training.arena.service.FightService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FightController {

    static CreaturesFactory creaturesFactory = new CreaturesFactory();;
    static List<Creature> list = new ArrayList<>();

    @PostMapping("/fighter")
    public static String addFighter(String type, String name, int strength, int dexterity, int initiative, int endurance, int lifepoints, String protection) {

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.valueOf(protection), 1);

        Creature creature = creaturesFactory.generate(CreatureType.valueOf(type), name, strength, dexterity, initiative, endurance, lifepoints, map);

        if (list.contains(creature)){
            return name + " - there is already such creature";
        }
        else {
            list.add(creature);
            return name + " succesfully created";
        }

    }

    @DeleteMapping("/fighter/{name}")
    public static String removeFighter(@PathVariable String name) {

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.valueOf("HELMET"), 1);

        Creature creature = creaturesFactory.generate(CreatureType.valueOf("ELF"), name, 1, 1, 1, 1, 1, map);

        if (list.contains(creature)){
            if (list.remove(creature)) {
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
    public static String getFighter(@PathVariable String name) {

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
    public static String amendFighter(String type, @PathVariable String name, int strength, int dexterity, int initiative, int endurance, int lifepoints, String protection) {

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


    @GetMapping("/tournament")
    public static String runTournament(){
        FightService fightService = new FightService();
        return fightService.tournament(list);
    }

    @GetMapping("/species")
    public static String getCreatureTypes(){

        String species = "";
        int counter = CreatureType.values().length;
        for (int i = 0; i<counter; i++){
            species = species.concat(CreatureType.values()[i].toString());
            if (i < counter - 1) {
                species = species.concat(", ");
            }
        }
        return species;
    }

    @GetMapping("/protections")
    public static String getProtectionItems(){

        String protections = "";
        int counter = ProtectionItem.values().length;
        for (int i = 0; i<counter; i++){
            protections = protections.concat(ProtectionItem.values()[i].toString());
            if (i < counter - 1) {
                protections = protections.concat(", ");
            }
        }
        return protections;
    }

    @GetMapping("/fighters")
    public static String getFighters(){

        Gson gson = new Gson();
        return gson.toJson(list);

    }

}
