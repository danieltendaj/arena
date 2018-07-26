package com.stepstone.training.arena;

public class Human extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature createCreature() {
            super.setType(CreatureType.HUMAN);
            return super.createCreature();
        }
    }

}

