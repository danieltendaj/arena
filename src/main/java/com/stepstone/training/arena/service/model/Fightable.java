package com.stepstone.training.arena.service.model;

public interface Fightable {

    AttackResult attack();

    AttackResult dodge(AttackResult attackResult);

}
