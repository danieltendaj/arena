package com.stepstone.training.arena.model.creature;

public class Human extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature build() {
            super.setType(CreatureType.HUMAN);
            return super.build();
        }
    }

}

