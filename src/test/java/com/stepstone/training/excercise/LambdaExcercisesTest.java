package com.stepstone.training.excercise;

import com.stepstone.training.arena.Creature;
import com.stepstone.training.arena.CreatureType;
import com.stepstone.training.arena.CreaturesFactory;
import com.stepstone.training.arena.ProtectionItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LambdaExcercisesTest {

    @Test
    public void shouldSortLifePointsWithoutLambdaWorksProperly() {
        //given
        CreaturesFactory creaturesFactory = new CreaturesFactory();
        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 2);

        Creature elf = creaturesFactory.generate(CreatureType.ELF, 6, map);
        Creature human = creaturesFactory.generate(CreatureType.HUMAN, 5, map);
        List<Creature> list = new ArrayList<>();
        list.add(elf);
        list.add(human);

        LambdaExcercises lambdaExcercises = new LambdaExcercises();
        lambdaExcercises.sortLifePointsWithoutLambda(list);

        assertTrue(list.get(0) == human && list.get(1) == elf);
    }

    @Test
    public void shouldSortLifePointsWithLambdaWorksProperly() {
        //given
        CreaturesFactory creaturesFactory = new CreaturesFactory();
        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 2);

        Creature elf = creaturesFactory.generate(CreatureType.ELF, 6, map);
        Creature human = creaturesFactory.generate(CreatureType.HUMAN, 5, map);
        List<Creature> list = new ArrayList<>();
        list.add(elf);
        list.add(human);

        LambdaExcercises lambdaExcercises = new LambdaExcercises();
        lambdaExcercises.sortLifePointsWithLambda(list);

        assertTrue(list.get(0) == human && list.get(1) == elf);
    }
}