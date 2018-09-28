package com.stepstone.training.arena.model;

import com.stepstone.training.arena.model.creature.Creature;
import com.stepstone.training.arena.model.creature.CreatureType;
import com.stepstone.training.arena.model.creature.Elf;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

public class CreatureTest {

    @Test
    public void shouldAttackDecreasedProtection(){

        //given
        AttackResult mockAttackResult = mock(AttackResult.class);
        when(mockAttackResult.getHitBodyPart()).thenReturn(BodyPart.HEAD);

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 2);

        Creature.CreatureBuilder elfBuilder = Elf.builder();
        elfBuilder.setMapProtection(map);
        elfBuilder.setType(CreatureType.ELF);
        Creature elf = elfBuilder.build();

        //when
        int protection = elf.calculateProtection(mockAttackResult);

        //then
        assertTrue(elf.getMapProtection().get(ProtectionItem.HELMET) == 1);
    }

    @Test
    public void shouldHelmetProtectHeadWithValue2(){

        //given
        AttackResult mockAttackResult = mock(AttackResult.class);
        when(mockAttackResult.getHitBodyPart()).thenReturn(BodyPart.HEAD);

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 1);

        Creature.CreatureBuilder elfBuilder = Elf.builder();
        elfBuilder.setMapProtection(map);
        elfBuilder.setType(CreatureType.ELF);
        Creature elf = elfBuilder.build();

        //when
        int protection = elf.calculateProtection(mockAttackResult);

        //then
        assertTrue(protection == 2);
        assertTrue(elf.getMapProtection().get(ProtectionItem.HELMET) == 0);
    }

    @Test
    public void shouldHelmetNotProtectWhenUsedAll(){

        //given
        AttackResult mockAttackResult = mock(AttackResult.class);
        when(mockAttackResult.getHitBodyPart()).thenReturn(BodyPart.HEAD);

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 0);

        Creature.CreatureBuilder elfBuilder = Elf.builder();
        elfBuilder.setMapProtection(map);
        elfBuilder.setType(CreatureType.ELF);
        Creature elf = elfBuilder.build();

        //when
        int protection = elf.calculateProtection(mockAttackResult);

        //then
        assertTrue(protection == 0);
        assertTrue(elf.getMapProtection().get(ProtectionItem.HELMET) == 0);
    }

    @Test
    public void shouldElfsCopyEqualsOriginal(){

        //given
        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 0);

        Creature.CreatureBuilder elfBuilder = Elf.builder();

        elfBuilder.setName("ulaya");
        elfBuilder.setStrength(1);
        elfBuilder.setDexterity(2);
        elfBuilder.setInitiative(3);
        elfBuilder.setEndurance(4);
        elfBuilder.setLifePoints(5);
        elfBuilder.setInitialLifePoints(5);
        elfBuilder.setMapProtection(map);
        elfBuilder.setType(CreatureType.ELF);
        Creature elf = elfBuilder.build();

        //then
        assertTrue(elf.equals(elf.copy()));

    }


}