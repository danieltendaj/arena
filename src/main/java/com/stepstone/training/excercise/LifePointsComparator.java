package com.stepstone.training.excercise;

import com.stepstone.training.arena.Creature;

import java.util.Comparator;

public class LifePointsComparator implements Comparator<Creature> {

    @Override
    public int compare(Creature c1, Creature c2) {
        return c1.getLifePoints() - c2.getLifePoints();
    }

}
