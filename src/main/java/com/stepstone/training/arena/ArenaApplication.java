package com.stepstone.training.arena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class ArenaApplication {

	public static void main(String[] args) {

		CreaturesFactory humanFactory = new CreaturesFactory();
		Human humanoid1 = (Human) humanFactory.generate(CreatureType.HUMAN);
		Human humanoid2 = (Human) humanFactory.generate(CreatureType.HUMAN);
		System.out.println("Ludzik pierwszy :" + humanoid1.toString());
		System.out.println("Ludzik drugi :" + humanoid2.toString());
	}
}
