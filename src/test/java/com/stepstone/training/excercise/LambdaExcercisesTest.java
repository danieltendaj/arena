package com.stepstone.training.excercise;

import com.stepstone.training.arena.model.Creature;
import com.stepstone.training.arena.model.CreatureType;
import com.stepstone.training.arena.service.CreaturesFactory;
import com.stepstone.training.arena.model.ProtectionItem;
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

        Creature elf = creaturesFactory.generate(CreatureType.ELF, "Legolas", 6, 6, 6, 6, 6, map);
        Creature human = creaturesFactory.generate(CreatureType.HUMAN, "Aragorn", 5,5, 5, 5, 5, map);
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

        Creature elf = creaturesFactory.generate(CreatureType.ELF, "Legolas", 6, 6, 6, 6, 6, map);
        Creature human = creaturesFactory.generate(CreatureType.HUMAN, "Aragorn", 5,5, 5, 5, 5, map);

        List<Creature> list = new ArrayList<>();
        list.add(elf);
        list.add(human);

        LambdaExcercises lambdaExcercises = new LambdaExcercises();
        lambdaExcercises.sortLifePointsWithLambda(list);

        assertTrue(list.get(0) == human && list.get(1) == elf);
    }

    @Test
    public void shouldMaxPointsCreatureReturnProperValue() {
        //given
        CreaturesFactory creaturesFactory = new CreaturesFactory();
        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 2);

        Creature elf = creaturesFactory.generate(CreatureType.ELF, "Legolas", 6, 6, 6, 6, 6, map);
        Creature human = creaturesFactory.generate(CreatureType.HUMAN, "Aragorn", 5,5, 5, 5, 5, map);

        List<Creature> list = new ArrayList<>();
        list.add(elf);
        list.add(human);

        LambdaExcercises lambdaExcercises = new LambdaExcercises();

        assertTrue(lambdaExcercises.maxPointsCreature(list).getSumPoints() == 24);
    }

    @Test
    public void shouldMapCreaturesReturnProperMap() {
        //given
        CreaturesFactory creaturesFactory = new CreaturesFactory();
        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 2);

        Creature elf1 = creaturesFactory.generate(CreatureType.ELF, "Legolas", 6, 6, 6, 6, 6, map);
        Creature elf2 = creaturesFactory.generate(CreatureType.ELF, "Elrond", 7, 7, 7, 7, 7, map);
        Creature elf3 = creaturesFactory.generate(CreatureType.ELF, "Thranduil", 8, 8, 8, 8, 8, map);
        Creature human = creaturesFactory.generate(CreatureType.HUMAN, "Aragorn", 5,5, 5, 5, 5, map);

        List<Creature> list = new ArrayList<>();
        list.add(elf1);
        list.add(human);
        list.add(elf2);
        list.add(elf3);

        LambdaExcercises lambdaExcercises = new LambdaExcercises();

        Map<CreatureType, List<Creature>> mapa = lambdaExcercises.mapCreatures(list);

        for (Map.Entry<CreatureType, List<Creature>> entry : mapa.entrySet()){
            System.out.println("Creature Type : " + entry.getKey() + " Quantity : " + entry.getValue().size());
        }

        assertTrue(mapa.size() == 2);
    }

}