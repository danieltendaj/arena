package com.stepstone.training.arena.model.creature;

public class Elf extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature build() {
            super.setType(CreatureType.ELF);
            return super.build();
        }
    }

}
