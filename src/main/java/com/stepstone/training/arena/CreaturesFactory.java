package com.stepstone.training.arena;

import java.util.*;

class CreaturesFactory {

    Creature generate(CreatureType type, int value, Map<Armour, Integer> armour) {

        switch (type) {
            case ELF:
                Creature.CreatureBuilder elfBuilder = Elf.builder();
                elfBuilder.setStrength(value);
                elfBuilder.setDexterity(value);
                elfBuilder.setInitiative(value);
                elfBuilder.setVelocity(value);
                elfBuilder.setEndurance(value);
                elfBuilder.setNumberOfAttacks(value);
                elfBuilder.setNumberOfDodges(value);
                elfBuilder.setLifePoints(value);
                elfBuilder.setMapArmour(armour);
                elfBuilder.setType(CreatureType.ELF);
                return elfBuilder.build();
            case ORC:
                Creature.CreatureBuilder orcBuilder = Orc.builder();
                orcBuilder.setStrength(value);
                orcBuilder.setDexterity(value);
                orcBuilder.setInitiative(value);
                orcBuilder.setVelocity(value);
                orcBuilder.setEndurance(value);
                orcBuilder.setNumberOfAttacks(value);
                orcBuilder.setNumberOfDodges(value);
                orcBuilder.setLifePoints(value);
                orcBuilder.setMapArmour(armour);
                orcBuilder.setType(CreatureType.ORC);
                return orcBuilder.build();
            case DWARF:
                Creature.CreatureBuilder dwarfBuilder = Dwarf.builder();
                dwarfBuilder.setStrength(value);
                dwarfBuilder.setDexterity(value);
                dwarfBuilder.setInitiative(value);
                dwarfBuilder.setVelocity(value);
                dwarfBuilder.setEndurance(value);
                dwarfBuilder.setNumberOfAttacks(value);
                dwarfBuilder.setNumberOfDodges(value);
                dwarfBuilder.setLifePoints(value);
                dwarfBuilder.setMapArmour(armour);
                dwarfBuilder.setType(CreatureType.DWARF);
                return dwarfBuilder.build();
            case HUMAN:
                Creature.CreatureBuilder humanBuilder = Human.builder();
                humanBuilder.setStrength(value);
                humanBuilder.setDexterity(value);
                humanBuilder.setInitiative(value);
                humanBuilder.setVelocity(value);
                humanBuilder.setEndurance(value);
                humanBuilder.setNumberOfAttacks(value);
                humanBuilder.setNumberOfDodges(value);
                humanBuilder.setLifePoints(value);
                humanBuilder.setMapArmour(armour);
                humanBuilder.setType(CreatureType.HUMAN);
                return humanBuilder.build();
            case TROLL:
                Creature.CreatureBuilder trollBuilder = Troll.builder();
                trollBuilder.setStrength(value);
                trollBuilder.setDexterity(value);
                trollBuilder.setInitiative(value);
                trollBuilder.setVelocity(value);
                trollBuilder.setEndurance(value);
                trollBuilder.setNumberOfAttacks(value);
                trollBuilder.setNumberOfDodges(value);
                trollBuilder.setLifePoints(value);
                trollBuilder.setMapArmour(armour);
                trollBuilder.setType(CreatureType.TROLL);
                return trollBuilder.build();
            case HALFING:
                Creature.CreatureBuilder halfingBuilder = Halfing.builder();
                halfingBuilder.setStrength(value);
                halfingBuilder.setDexterity(value);
                halfingBuilder.setInitiative(value);
                halfingBuilder.setVelocity(value);
                halfingBuilder.setEndurance(value);
                halfingBuilder.setNumberOfAttacks(value);
                halfingBuilder.setNumberOfDodges(value);
                halfingBuilder.setLifePoints(value);
                halfingBuilder.setMapArmour(armour);
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

    Map<Armour, Integer> randomArmour(){
        Map<Armour, Integer> map = new HashMap<>();
        int numberArmours;
        for (Armour armour:Armour.values()) {
            numberArmours = CreaturesRandomizer.randomCreatureValue(0, 2);
            map.put(armour, numberArmours);
        }
        return map;
    }

    Creature randomCreature() {
        CreatureType randomType = randomCreatureType();
        int randomValue = CreaturesRandomizer.randomCreatureValue(5, 15);
        Map<Armour, Integer> randomArmour = randomArmour();
        return generate(randomType, randomValue, randomArmour);
    }

    List<Creature> randomCreatureList(int listSize) {
        List<Creature> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(randomCreature());
        }
        return list;
    }

}
