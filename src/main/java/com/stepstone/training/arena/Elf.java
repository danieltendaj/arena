package com.stepstone.training.arena;

public class Elf extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature createCreature() {
            super.setType(CreatureType.ELF);
            return super.createCreature();
        }
    }

}
