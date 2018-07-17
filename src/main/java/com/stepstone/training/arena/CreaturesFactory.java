package com.stepstone.training.arena;

class CreaturesFactory {

    Creature generate(CreatureType type) {

        switch (type) {
            case ELF:
                return new Elf(10, 1, 0, 0, 0, 0, 0, 100);
            case ORC:
                return new Orc(20,2,0,0,0,0,0,100);
            case DWARF:
                return new Dwarf(30,1,0,0,0,0,0,100);
            case HUMAN:
                return new Human(10,2,0,0,0,0,0,100);
            case TROLL:
                return new Troll(20,1,0,0,0,0,0,100);
            case HALFING:
                return new Halfing(30,2,0,0,0,0,0,100);
        }
        return null;
    }

}
