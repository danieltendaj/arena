package com.stepstone.training.arena.service.model.creature;

public class Human extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature build() {
            super.setType(CreatureType.HUMAN);
            return super.build();
        }
    }

}

