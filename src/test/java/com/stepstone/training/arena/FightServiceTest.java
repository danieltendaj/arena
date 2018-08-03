package com.stepstone.training.arena;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class FightServiceTest {

    @Test
    public void shouldMethodPairsReturnListOfPairs() {

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

    @Test
    public void shouldThreeCreaturesReturnThreePairs() {

        //given
        CreaturesFactory creaturesFactory = new CreaturesFactory();
        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 2);

        Creature elf = creaturesFactory.generate(CreatureType.ELF, 5, map);
        Creature human = creaturesFactory.generate(CreatureType.HUMAN, 5, map);
        Creature halfing = creaturesFactory.generate(CreatureType.HALFING, 5, map);

        FightService fightService = new FightService();
        List<Creature> list = new ArrayList<>();
        list.add(elf);
        list.add(human);
        list.add(halfing);

        //then
        assertTrue(fightService.pairs(list).size() == 3);

    }

    @Test
    public void shouldSixCreaturesReturnFifteenPairs() {

        //given
        CreaturesFactory creaturesFactory = new CreaturesFactory();
        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 2);

        Creature elf = creaturesFactory.generate(CreatureType.ELF, 5, map);
        Creature human = creaturesFactory.generate(CreatureType.HUMAN, 5, map);
        Creature halfing = creaturesFactory.generate(CreatureType.HALFING, 5, map);
        Creature orc = creaturesFactory.generate(CreatureType.ORC, 5, map);
        Creature troll = creaturesFactory.generate(CreatureType.TROLL, 5, map);
        Creature dwarf = creaturesFactory.generate(CreatureType.DWARF, 5, map);

        FightService fightService = new FightService();
        List<Creature> list = new ArrayList<>();
        list.add(elf);
        list.add(human);
        list.add(halfing);
        list.add(orc);
        list.add(troll);
        list.add(dwarf);

        //then
        assertTrue(fightService.pairs(list).size() == 15);

    }

}