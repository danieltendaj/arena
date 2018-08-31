package com.stepstone.training.arena.service;

import com.stepstone.training.arena.model.Creature;
import com.stepstone.training.arena.model.CreatureType;
import com.stepstone.training.arena.model.ProtectionItem;
import com.stepstone.training.arena.util.CreaturesRandomizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CreaturesRandomizer.class)

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

    @Test
    public void shouldNameBeGivenLength() {
        //given
        PowerMockito.mockStatic(CreaturesRandomizer.class);
        when(CreaturesRandomizer.randomCreatureValue(3, 10)).thenReturn(5);
        when(CreaturesRandomizer.randomCreatureValue(1, 26)).thenReturn(1);

        CreaturesFactory creaturesFactory = new CreaturesFactory();
        //when
        String name = creaturesFactory.randomName();
        //then
        assertTrue(name.length() == 5);

        System.out.println(name);
    }

    @Test
    public void shouldNameBeAtLeastTreeLongAndNotLongerThanTen() {
        //given
        CreaturesFactory creaturesFactory = new CreaturesFactory();
        //when
        String name = creaturesFactory.randomName();
        //then
        assertTrue(name.length() >= 3);
        assertTrue(name.length() <= 10);

        System.out.println(name);
    }

}