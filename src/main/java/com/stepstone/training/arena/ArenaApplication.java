package com.stepstone.training.arena;

import java.util.List;

public class ArenaApplication {

	public static void main(String[] args) {

		CreaturesFactory creaturesFactory = new CreaturesFactory();

		List<Creature> list = creaturesFactory.randomCreatureList(100);

		FightService fightService = new FightService();
		fightService.tournament(list);

	}
}
