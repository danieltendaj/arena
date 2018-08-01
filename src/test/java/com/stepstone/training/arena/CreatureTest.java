package com.stepstone.training.arena;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

public class CreatureTest {

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
    }

}