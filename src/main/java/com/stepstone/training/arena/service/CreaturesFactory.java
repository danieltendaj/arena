package com.stepstone.training.arena.service;

import com.stepstone.training.arena.model.*;
import com.stepstone.training.arena.model.creature.*;
import com.stepstone.training.arena.util.CreaturesRandomizer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CreaturesFactory {

    public Creature generate(CreatureType type, String name, int strength, int dexterity, int initiative, int endurance, int lifepoints, Map<ProtectionItem, Integer> protection) {

        Creature.CreatureBuilder creatureBuilder = null;

        switch (type) {
            case ELF:
                creatureBuilder = Elf.builder();
                creatureBuilder.setType(CreatureType.ELF);
                break;
            case ORC:
                creatureBuilder = Orc.builder();
                creatureBuilder.setType(CreatureType.ORC);
                break;
            case DWARF:
                creatureBuilder = Dwarf.builder();
                creatureBuilder.setType(CreatureType.DWARF);
                break;
            case HUMAN:
                creatureBuilder = Human.builder();
                creatureBuilder.setType(CreatureType.HUMAN);
                break;
            case TROLL:
                creatureBuilder = Troll.builder();
                creatureBuilder.setType(CreatureType.TROLL);
                break;
            case HALFLING:
                creatureBuilder = Halfling.builder();
                creatureBuilder.setType(CreatureType.HALFLING);
                break;
        }
        creatureBuilder.setName(name);
        creatureBuilder.setStrength(strength);
        creatureBuilder.setDexterity(dexterity);
        creatureBuilder.setInitiative(initiative);
        creatureBuilder.setEndurance(endurance);
        creatureBuilder.setLifePoints(lifepoints);
        creatureBuilder.setInitialLifePoints(lifepoints);
        creatureBuilder.setMapProtection(protection);
        return creatureBuilder.build();
    }

    CreatureType randomCreatureType() {
        int creatureTypeNumber = CreatureType.values().length;
        Random randomGenerator = new Random();
        return CreatureType.values()[randomGenerator.nextInt(creatureTypeNumber)];
    }

    String randomName() {
        final String LETTERS = "abcdefghijklmnopqrstuvwxyz";

        int length = CreaturesRandomizer.randomCreatureValue(3, 10);

        char[] name = new char[length];

        for (int i = 0; i < length; i++) {
            name[i] = LETTERS.charAt(CreaturesRandomizer.randomCreatureValue(1, LETTERS.length()) - 1);
        }

        return String.valueOf(name);
    }

    Map<ProtectionItem, Integer> randomProtectionItem() {
        Map<ProtectionItem, Integer> map = new HashMap<>();
        int numberArmours;
        for (ProtectionItem protectionItem : ProtectionItem.values()) {
            numberArmours = CreaturesRandomizer.randomCreatureValue(0, 2);
            map.put(protectionItem, numberArmours);
        }
        return map;
    }

    Creature randomCreature() {
        CreatureType randomType = randomCreatureType();
        String randomName = randomName();
        int randomStrength = CreaturesRandomizer.randomCreatureValue(5, 15);
        int randomDexterity = CreaturesRandomizer.randomCreatureValue(5, 15);
        int randomInitiative = CreaturesRandomizer.randomCreatureValue(5, 15);
        int randomEndurance = CreaturesRandomizer.randomCreatureValue(5, 15);
        int randomLifePoints = CreaturesRandomizer.randomCreatureValue(5, 15);
        Map<ProtectionItem, Integer> randomProtectionItem = randomProtectionItem();
        return generate(randomType, randomName, randomStrength, randomDexterity, randomInitiative, randomEndurance, randomLifePoints, randomProtectionItem);
    }

    public List<Creature> randomCreatureList(int listSize) {
        List<Creature> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(randomCreature());
        }
        return list;
    }

}
