package com.stepstone.training.arena;

import java.util.*;

public class CreaturesFactory {

    public Creature generate(CreatureType type, String name, int strength, int dexterity, int initiative, int endurance, int lifepoints, Map<ProtectionItem, Integer> protection) {

        switch (type) {
            case ELF:
                Creature.CreatureBuilder elfBuilder = Elf.builder();
                elfBuilder.setName(name);
                elfBuilder.setStrength(strength);
                elfBuilder.setDexterity(dexterity);
                elfBuilder.setInitiative(initiative);
                elfBuilder.setEndurance(endurance);
                elfBuilder.setLifePoints(lifepoints);
                elfBuilder.setMapProtection(protection);
                elfBuilder.setType(CreatureType.ELF);
                return elfBuilder.build();
            case ORC:
                Creature.CreatureBuilder orcBuilder = Orc.builder();
                orcBuilder.setName(name);
                orcBuilder.setStrength(strength);
                orcBuilder.setDexterity(dexterity);
                orcBuilder.setInitiative(initiative);
                orcBuilder.setEndurance(endurance);
                orcBuilder.setLifePoints(lifepoints);
                orcBuilder.setMapProtection(protection);
                orcBuilder.setType(CreatureType.ORC);
                return orcBuilder.build();
            case DWARF:
                Creature.CreatureBuilder dwarfBuilder = Dwarf.builder();
                dwarfBuilder.setName(name);
                dwarfBuilder.setStrength(strength);
                dwarfBuilder.setDexterity(dexterity);
                dwarfBuilder.setInitiative(initiative);
                dwarfBuilder.setEndurance(endurance);
                dwarfBuilder.setLifePoints(lifepoints);
                dwarfBuilder.setMapProtection(protection);
                dwarfBuilder.setType(CreatureType.DWARF);
                return dwarfBuilder.build();
            case HUMAN:
                Creature.CreatureBuilder humanBuilder = Human.builder();
                humanBuilder.setName(name);
                humanBuilder.setStrength(strength);
                humanBuilder.setDexterity(dexterity);
                humanBuilder.setInitiative(initiative);
                humanBuilder.setEndurance(endurance);
                humanBuilder.setLifePoints(lifepoints);
                humanBuilder.setMapProtection(protection);
                humanBuilder.setType(CreatureType.HUMAN);
                return humanBuilder.build();
            case TROLL:
                Creature.CreatureBuilder trollBuilder = Troll.builder();
                trollBuilder.setName(name);
                trollBuilder.setStrength(strength);
                trollBuilder.setDexterity(dexterity);
                trollBuilder.setInitiative(initiative);
                trollBuilder.setEndurance(endurance);
                trollBuilder.setLifePoints(lifepoints);
                trollBuilder.setMapProtection(protection);
                trollBuilder.setType(CreatureType.TROLL);
                return trollBuilder.build();
            case HALFLING:
                Creature.CreatureBuilder halflingBuilder = Halfling.builder();
                halflingBuilder.setName(name);
                halflingBuilder.setStrength(strength);
                halflingBuilder.setDexterity(dexterity);
                halflingBuilder.setInitiative(initiative);
                halflingBuilder.setEndurance(endurance);
                halflingBuilder.setLifePoints(lifepoints);
                halflingBuilder.setMapProtection(protection);
                halflingBuilder.setType(CreatureType.HALFLING);
                return halflingBuilder.build();
        }
        return null;
    }

    CreatureType randomCreatureType() {
        int creatureTypeNumber = CreatureType.values().length;
        Random randomGenerator = new Random();
        return CreatureType.values()[randomGenerator.nextInt(creatureTypeNumber)];
    }

    String randomName(){
        final String LETTERS = "abcdefghijklmnopqrstuvwxyz";

        int length = CreaturesRandomizer.randomCreatureValue(3, 10);

        char[] name = new char[length];

        for (int i = 0; i < length; i++){
            name[i] = LETTERS.charAt(CreaturesRandomizer.randomCreatureValue(1, LETTERS.length()) - 1);
        }

        return String.valueOf(name);
    }

    Map<ProtectionItem, Integer> randomProtectionItem(){
        Map<ProtectionItem, Integer> map = new HashMap<>();
        int numberArmours;
        for (ProtectionItem protectionItem:ProtectionItem.values()) {
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
