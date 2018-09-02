package com.stepstone.training.arena.model;

public interface Fightable {

    AttackResult attack();

    AttackResult dodge(AttackResult attackResult);

}
