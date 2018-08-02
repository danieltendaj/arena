package com.stepstone.training.arena;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CreaturesFactoryTest {

    @Test
    public void shouldFactoryReturnElf() {
        //given
        CreaturesFactory creaturesFactory = new CreaturesFactory();

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 1);

        //when
        Creature elf = creaturesFactory.generate(CreatureType.ELF,10, map);

        //then
        assertTrue(elf.getType() == CreatureType.ELF);
    }

}