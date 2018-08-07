package com.stepstone.training.arena;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class CreaturesFactoryTest {

    @Test
    public void shouldFactoryReturnElf() {
        //given
        CreaturesFactory creaturesFactory = new CreaturesFactory();

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 1);

        //when
        Creature elf = creaturesFactory.generate(CreatureType.ELF, "Legolas", 5, 5, 5, 5, 5, map);

        //then
        assertTrue(elf.getType() == CreatureType.ELF);
    }

}