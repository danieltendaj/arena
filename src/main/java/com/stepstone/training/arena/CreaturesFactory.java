package com.stepstone.training.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class CreaturesFactory {

    Creature generate(CreatureType type, int value) {

        switch (type) {
            case ELF:
                Creature.CreatureBuilder elfBuilder = Elf.CreatureBuilder.newInstance();
                elfBuilder.setStrength(value);
                elfBuilder.setDexterity(value);
                elfBuilder.setInitiative(value);
                elfBuilder.setVelocity(value);
                elfBuilder.setEndurance(value);
                elfBuilder.setNumberOfAttacks(value);
                elfBuilder.setNumberOfDodges(value);
                elfBuilder.setLifePoints(value);
                return elfBuilder.createCreature();
            case ORC:
                Creature.CreatureBuilder orcBuilder = Orc.CreatureBuilder.newInstance();
                orcBuilder.setStrength(value);
                orcBuilder.setDexterity(value);
                orcBuilder.setInitiative(value);
                orcBuilder.setVelocity(value);
                orcBuilder.setEndurance(value);
                orcBuilder.setNumberOfAttacks(value);
                orcBuilder.setNumberOfDodges(value);
                orcBuilder.setLifePoints(value);
                return orcBuilder.createCreature();
            case DWARF:
                Creature.CreatureBuilder dwarfBuilder = Dwarf.CreatureBuilder.newInstance();
                dwarfBuilder.setStrength(value);
                dwarfBuilder.setDexterity(value);
                dwarfBuilder.setInitiative(value);
                dwarfBuilder.setVelocity(value);
                dwarfBuilder.setEndurance(value);
                dwarfBuilder.setNumberOfAttacks(value);
                dwarfBuilder.setNumberOfDodges(value);
                dwarfBuilder.setLifePoints(value);
                return dwarfBuilder.createCreature();
            case HUMAN:
                Creature.CreatureBuilder humanBuilder = Human.CreatureBuilder.newInstance();
                humanBuilder.setStrength(value);
                humanBuilder.setDexterity(value);
                humanBuilder.setInitiative(value);
                humanBuilder.setVelocity(value);
                humanBuilder.setEndurance(value);
                humanBuilder.setNumberOfAttacks(value);
                humanBuilder.setNumberOfDodges(value);
                humanBuilder.setLifePoints(value);
                return humanBuilder.createCreature();
            case TROLL:
                Creature.CreatureBuilder trollBuilder = Troll.CreatureBuilder.newInstance();
                trollBuilder.setStrength(value);
                trollBuilder.setDexterity(value);
                trollBuilder.setInitiative(value);
                trollBuilder.setVelocity(value);
                trollBuilder.setEndurance(value);
                trollBuilder.setNumberOfAttacks(value);
                trollBuilder.setNumberOfDodges(value);
                trollBuilder.setLifePoints(value);
                return trollBuilder.createCreature();
            case HALFING:
                Creature.CreatureBuilder halfingBuilder = Halfing.CreatureBuilder.newInstance();
                halfingBuilder.setStrength(value);
                halfingBuilder.setDexterity(value);
                halfingBuilder.setInitiative(value);
                halfingBuilder.setVelocity(value);
                halfingBuilder.setEndurance(value);
                halfingBuilder.setNumberOfAttacks(value);
                halfingBuilder.setNumberOfDodges(value);
                halfingBuilder.setLifePoints(value);
                return halfingBuilder.createCreature();
        }
        return null;
    }

    CreatureType randomCreatureType() {
        int creatureTypeNumber = CreatureType.values().length;
        Random randomGenerator = new Random();
        return CreatureType.values()[randomGenerator.nextInt(creatureTypeNumber)];
    }

    Creature randomCreature() {
        CreatureType randomType = randomCreatureType();
        int randomValue = CreaturesRandomizer.randomCreatureValue(5, 15);
        return generate(randomType, randomValue);
    }

    List<Creature> randomCreatureList(int listSize) {
        List<Creature> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(randomCreature());
        }
        return list;
    }

}
