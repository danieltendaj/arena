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
		list.get(1).dodge(list.get(0).attack(list.get(1)), list.get(1));
	}
}
