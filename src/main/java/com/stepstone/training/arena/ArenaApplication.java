package com.stepstone.training.arena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

public class ArenaApplication {

	public static void main(String[] args) {

		CreaturesFactory creaturesFactory = new CreaturesFactory();

		List<Creature> list = creaturesFactory.randomCreatureList(2);
		for (Creature x:list) {
			System.out.println(x.toString());
		}

		FightService fightService = new FightService();
		fightService.fight(list.get(0), list.get(1));

	}
}
