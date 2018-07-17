package com.stepstone.training.arena;

public class CreaturesFactory {

    Creature generate(CreatureType type) {

        switch (type) {
            case ELF:
                return new Elf(0, 0, 0, 0, 0, 0, 0, 0);
            case ORC:
                return new Orc(0,0,0,0,0,0,0,0)
            case DWARF:
                return new Dwarf(0,0,0,0,0,0,0,0)
            case HUMAN:
                return new Human(0,0,0,0,0,0,0,0)
            case TROLL:
                return new Troll(0,0,0,0,0,0,0,0)
            case HALFING:
                return new Halfing(0,0,0,0,0,0,0,0)
        }
        return null;
    }

}
