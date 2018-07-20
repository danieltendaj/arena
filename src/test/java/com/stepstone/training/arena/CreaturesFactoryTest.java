package com.stepstone.training.arena;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CreaturesFactoryTest {

    static CreaturesFactory creaturesFactory;

    @BeforeAll
    static void setUp(){
        creaturesFactory = new CreaturesFactory();
    }

    @Test
    void isValueGreaterThanMin(){

        int value = creaturesFactory.randomCreatureValue(10, 20);
        assertTrue(value >= 10);
    }

    @Test
    void isValueLessThanMax(){

        int value = creaturesFactory.randomCreatureValue(10, 20);
        assertTrue(value <= 20);
    }

    @Test
    void isValueTheSameMinMax(){

        int value = creaturesFactory.randomCreatureValue(10, 10);
        assertTrue(value == 10);
    }

}