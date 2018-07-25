package com.stepstone.training.arena;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;


import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;


class CreatureTest {

    @Test
    public void getPart(){

        CreaturesFactory creaturesFactory = new CreaturesFactory();

        Creature elf = creaturesFactory.generate(CreatureType.ELF, 5);

        BodyPart hitPart = null;
        try {
            hitPart = elf.hit();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(hitPart.getBonus());

    }

}