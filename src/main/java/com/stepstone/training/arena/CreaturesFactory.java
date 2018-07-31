package com.stepstone.training.arena;

import java.util.*;

class CreaturesFactory {

    Creature generate(CreatureType type, int value, Map<ProtectionItem, Integer> protection) {

        switch (type) {
            case ELF:
                Creature.CreatureBuilder elfBuilder = Elf.builder();
                elfBuilder.setStrength(value);
                elfBuilder.setDexterity(value);
                elfBuilder.setInitiative(value);
                elfBuilder.setEndurance(value);
                elfBuilder.setLifePoints(value);
                elfBuilder.setMapProtection(protection);
                elfBuilder.setType(CreatureType.ELF);
                return elfBuilder.build();
            case ORC:
                Creature.CreatureBuilder orcBuilder = Orc.builder();
                orcBuilder.setStrength(value);
                orcBuilder.setDexterity(value);
                orcBuilder.setInitiative(value);
                orcBuilder.setEndurance(value);
                orcBuilder.setLifePoints(value);
                orcBuilder.setMapProtection(protection);
                orcBuilder.setType(CreatureType.ORC);
                return orcBuilder.build();
            case DWARF:
                Creature.CreatureBuilder dwarfBuilder = Dwarf.builder();
                dwarfBuilder.setStrength(value);
                dwarfBuilder.setDexterity(value);
                dwarfBuilder.setInitiative(value);
                dwarfBuilder.setEndurance(value);
                dwarfBuilder.setLifePoints(value);
                dwarfBuilder.setMapProtection(protection);
                dwarfBuilder.setType(CreatureType.DWARF);
                return dwarfBuilder.build();
            case HUMAN:
                Creature.CreatureBuilder humanBuilder = Human.builder();
                humanBuilder.setStrength(value);
                humanBuilder.setDexterity(value);
                humanBuilder.setInitiative(value);
                humanBuilder.setEndurance(value);
                humanBuilder.setLifePoints(value);
                humanBuilder.setMapProtection(protection);
                humanBuilder.setType(CreatureType.HUMAN);
                return humanBuilder.build();
            case TROLL:
                Creature.CreatureBuilder trollBuilder = Troll.builder();
                trollBuilder.setStrength(value);
                trollBuilder.setDexterity(value);
                trollBuilder.setInitiative(value);
                trollBuilder.setEndurance(value);
                trollBuilder.setLifePoints(value);
                trollBuilder.setMapProtection(protection);
                trollBuilder.setType(CreatureType.TROLL);
                return trollBuilder.build();
            case HALFING:
                Creature.CreatureBuilder halfingBuilder = Halfing.builder();
                halfingBuilder.setStrength(value);
                halfingBuilder.setDexterity(value);
                halfingBuilder.setInitiative(value);
                halfingBuilder.setEndurance(value);
                halfingBuilder.setLifePoints(value);
                halfingBuilder.setMapProtection(protection);
                halfingBuilder.setType(CreatureType.HALFING);
                return halfingBuilder.build();
        }
        return null;
    }

    CreatureType randomCreatureType() {
        int creatureTypeNumber = CreatureType.values().length;
        Random randomGenerator = new Random();
        return CreatureType.values()[randomGenerator.nextInt(creatureTypeNumber)];
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
        int randomValue = CreaturesRandomizer.randomCreatureValue(5, 15);
        Map<ProtectionItem, Integer> randomProtectionItem = randomProtectionItem();
        return generate(randomType, randomValue, randomProtectionItem);
    }

    List<Creature> randomCreatureList(int listSize) {
        List<Creature> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(randomCreature());
        }
        return list;
    }

}
