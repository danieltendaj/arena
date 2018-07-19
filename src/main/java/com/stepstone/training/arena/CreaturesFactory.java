package com.stepstone.training.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class CreaturesFactory {

    Creature generate(CreatureType type, int value) {

        switch (type) {
            case ELF:
                return new Elf(value,  value, value, value, value, value, value,value);
            case ORC:
                return new Orc(value,value,value,value,value,value,value,value);
            case DWARF:
                return new Dwarf(value,value,value,value,value,value,value,value);
            case HUMAN:
                return new Human(value,value,value,value,value,value,value,value);
            case TROLL:
                return new Troll(value,value,value,value,value,value,value,value);
            case HALFING:
                return new Halfing(value,value,value,value,value,value,value,value);
        }
        return null;
    }

    int randomCreatureValue(int min, int max) {
        Random randomGenerator = new Random();

        int i = 0;
        while (i == 0) {
            i = randomGenerator.nextInt(max);
            if (i < min) {
                i = 0;
            }
        }
        return i;
    }

    CreatureType randomCreatureType() {
        int creatureTypeNumber = CreatureType.values().length;
        Random randomGenerator = new Random();
        return CreatureType.values()[randomGenerator.nextInt(creatureTypeNumber)];
    }

    Creature randomCreature() {
        CreatureType randomType = randomCreatureType();
        int randomValue = randomCreatureValue(10, 20);
        return generate(randomType, randomValue);
    }

    List<Creature> randomCreatureList(int listSize) {
        List<Creature> list = new ArrayList<>();
        for (int i = 0; i<10; i++) {
            list.add(randomCreature());
        }
        return list;
    }

}
