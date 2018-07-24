package com.stepstone.training.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class CreaturesFactory {

    Creature generate(CreatureType type, int value) {

        switch (type) {
            case ELF:
                Elf elf = new Elf(value,  value, value, value, value, value, value,value);
                return elf.createCreature();
            case ORC:
                Orc orc = new Orc(value,  value, value, value, value, value, value,value);
                return orc.createCreature();
            case DWARF:
                Dwarf dwarf = new Dwarf(value,  value, value, value, value, value, value,value);
                return dwarf.createCreature();
            case HUMAN:
                Human human = new Human(value,  value, value, value, value, value, value,value);
                return human.createCreature();
            case TROLL:
                Troll troll = new Troll(value,  value, value, value, value, value, value,value);
                return troll.createCreature();
            case HALFING:
                Halfing halfing = new Halfing(value,  value, value, value, value, value, value,value);
                return halfing.createCreature();
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
