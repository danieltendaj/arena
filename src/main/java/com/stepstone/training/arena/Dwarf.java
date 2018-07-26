package com.stepstone.training.arena;

public class Dwarf extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature createCreature() {
            super.setType(CreatureType.DWARF);
            return super.createCreature();
        }
    }

}
