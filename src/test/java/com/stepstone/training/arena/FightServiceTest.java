package com.stepstone.training.arena;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class FightServiceTest {

    @Test
    public void shouldPairsReturnListOfPairs() {

        //given
        CreaturesFactory creaturesFactory = new CreaturesFactory();
        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 2);

        Creature elf = creaturesFactory.generate(CreatureType.ELF, 5, map);
        Creature human = creaturesFactory.generate(CreatureType.HUMAN, 5, map);

        FightService fightService = new FightService();
        List<Creature> list = new ArrayList<>();

        //then
        assertTrue(fightService.pairs(list).size() == 0);

    }

    @Test
    public void shouldTwoCreaturesReturnOnePair() {

        //given
        CreaturesFactory creaturesFactory = new CreaturesFactory();
        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 2);

        Creature elf = creaturesFactory.generate(CreatureType.ELF, 5, map);
        Creature human = creaturesFactory.generate(CreatureType.HUMAN, 5, map);

        FightService fightService = new FightService();
        List<Creature> list = new ArrayList<>();
        list.add(elf);
        list.add(human);

        //then
        assertTrue(fightService.pairs(list).size() == 1);

    }
}