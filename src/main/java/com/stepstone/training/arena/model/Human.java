package com.stepstone.training.arena.model;

import com.stepstone.training.arena.model.Creature;
import com.stepstone.training.arena.model.CreatureType;

public class Human extends Creature {

    public static class CreatureBuilder extends Creature.CreatureBuilder{
        @Override
        public Creature build() {
            super.setType(CreatureType.HUMAN);
            return super.build();
        }
    }

}

