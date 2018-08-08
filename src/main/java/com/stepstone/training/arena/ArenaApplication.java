package com.stepstone.training.arena;

import java.util.List;

public class ArenaApplication {

	public static void main(String[] args) {

		CreaturesFactory creaturesFactory = new CreaturesFactory();

		List<Creature> list = creaturesFactory.randomCreatureList(5);

		System.out.println("Creatures in the tournament:");
		for (Creature x:list) {
			System.out.println(x.toString());
		}
		System.out.println();

		FightService fightService = new FightService();
		fightService.tournament(list);

	}
}
