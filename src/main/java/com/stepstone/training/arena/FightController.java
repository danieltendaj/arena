package com.stepstone.training.arena;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FightController {

    static CreaturesFactory creaturesFactory = new CreaturesFactory();;
    static List<Creature> list = new ArrayList<>();

    @PostMapping("/addfighter")
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

    @GetMapping("/tournament")
    public static String runTournament(){
        FightService fightService = new FightService();
        return fightService.tournament(list);
    }

}
