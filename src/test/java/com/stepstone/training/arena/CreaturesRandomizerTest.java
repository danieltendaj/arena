package com.stepstone.training.arena;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreaturesRandomizerTest {

    @Test
    public void isValueGreaterThanMin(){

        int value = CreaturesRandomizer.randomCreatureValue(10, 20);
        assertTrue(value >= 10);
    }

    @Test
    public void isValueLessThanMax(){

        int value = CreaturesRandomizer.randomCreatureValue(10, 20);
        assertTrue(value <= 20);
    }

    @Test
    public void isValueTheSameMinMax(){

        int value = CreaturesRandomizer.randomCreatureValue(10, 10);
        assertTrue(value == 10);
    }
}