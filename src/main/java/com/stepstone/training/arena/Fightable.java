package com.stepstone.training.arena;

public interface Fightable {

    AttackResult attack();

    AttackResult dodge(AttackResult attackResult);

}
