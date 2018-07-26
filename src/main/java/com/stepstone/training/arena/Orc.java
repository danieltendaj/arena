package com.stepstone.training.arena;

public class Orc extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature createCreature() {
            super.setType(CreatureType.ORC);
            return super.createCreature();
        }
    }

}
