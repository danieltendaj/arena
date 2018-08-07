package com.stepstone.training.arena;

public class Halfling extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature build() {
            super.setType(CreatureType.HALFLING);
            return super.build();
        }
    }

}
