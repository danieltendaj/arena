package com.stepstone.training.arena.service.model.creature;

public class Orc extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature build() {
            super.setType(CreatureType.ORC);
            return super.build();
        }
    }

}
