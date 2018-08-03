package com.stepstone.training.excercise;

import com.stepstone.training.arena.Creature;

import java.util.Collections;
import java.util.List;

public class LambdaExcercises {

    public List<Creature> sortLifePointsWithoutLambda(List<Creature> list) {

        Collections.sort(list, new LifePointsComparator());
        return list;
    }

    public List<Creature> sortLifePointsWithLambda(List<Creature> list) {

        list.sort((Creature c1, Creature c2) -> c1.getLifePoints() - c2.getLifePoints());
        return list;
    }



}
