package com.stepstone.training.arena.model;

public class Dwarf extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature build() {
            super.setType(CreatureType.DWARF);
            return super.build();
        }
    }

}
