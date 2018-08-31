package com.stepstone.training.excercise;

import com.stepstone.training.arena.model.Creature;
import com.stepstone.training.arena.model.CreatureType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdaExcercises {

    public List<Creature> sortLifePointsWithoutLambda(List<Creature> list) {

        Collections.sort(list, new LifePointsComparator());
        return list;
    }

    public List<Creature> sortLifePointsWithLambda(List<Creature> list) {

        list.sort((Creature c1, Creature c2) -> c1.getLifePoints() - c2.getLifePoints());
        return list;
    }

    public Creature maxPointsCreature(List<Creature> list) {

        return list.stream().max(Comparator.comparingInt(Creature::getSumPoints)).get();
    }

    public Map<CreatureType, List<Creature>> mapCreatures(List<Creature> list) {

        return list.stream().collect(Collectors.groupingBy(Creature::getType));
    }

}
