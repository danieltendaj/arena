package com.stepstone.training.arena.model;

import com.stepstone.training.arena.model.Creature;

public class Fighters {
    private Creature firstFighter;
    private Creature secondFighter;

    public Creature getFirstFighter() {
        return firstFighter;
    }

    public void setFirstFighter(Creature firstFighter) {
        this.firstFighter = firstFighter;
    }

    public Creature getSecondFighter() {
        return secondFighter;
    }

    public void setSecondFighter(Creature secondFighter) {
        this.secondFighter = secondFighter;
    }
}