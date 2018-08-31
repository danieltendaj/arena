package com.stepstone.training.arena.util;

import java.util.Random;

public class CreaturesRandomizer {

    public static int randomCreatureValue(int min, int max) {
        Random randomGenerator = new Random();

        int i = 0;
        while (i == 0) {
            i = randomGenerator.nextInt(max + 1);
            if (i < min) {
                i = 0;
            }
        }
        return i;
    }

}
