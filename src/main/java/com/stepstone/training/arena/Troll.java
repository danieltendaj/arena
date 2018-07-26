package com.stepstone.training.arena;

public class Troll extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature createCreature() {
            super.setType(CreatureType.TROLL);
            return super.createCreature();
        }
    }

}
