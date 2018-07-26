package com.stepstone.training.arena;

public class Halfing extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature createCreature() {
            super.setType(CreatureType.HALFING);
            return super.createCreature();
        }
    }

}
