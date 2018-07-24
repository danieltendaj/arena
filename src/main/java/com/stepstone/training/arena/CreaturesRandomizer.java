package com.stepstone.training.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class CreaturesRandomizer {

    static int randomCreatureValue(int min, int max) {
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
