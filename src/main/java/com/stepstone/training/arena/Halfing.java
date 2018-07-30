package com.stepstone.training.arena;

public class Halfing extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature build() {
            super.setType(CreatureType.HALFING);
            return super.build();
        }
    }

}
