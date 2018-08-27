package com.stepstone.training.arena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ArenaApplication {

	static CreaturesFactory creaturesFactory;
	static List<Creature> list = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(ArenaApplication.class, args);
	}

	public static void main2(String[] args) {

		creaturesFactory = new CreaturesFactory();

		addFighter("ELF", "Legolas",100,1,1,1,1,"HELMET");
		addFighter("HUMAN", "Aragorn",1,1,1,1,1,"HELMET");
		addFighter("DWARF", "Gimli",1,1,1,1,1,"HELMET");

		runTournament();
	}

	public static void addFighter(String type, String name, int strength, int dexterity, int initiative, int endurance, int lifepoints, String protection) {

		Map<ProtectionItem, Integer> map = new HashMap<>();
		map.put(ProtectionItem.valueOf(protection), 1);

		list.add(creaturesFactory.generate(CreatureType.valueOf(type), name, strength, dexterity, initiative, endurance, lifepoints, map));
	}

	public static void runTournament(){
		FightService fightService = new FightService();
		fightService.tournament(list);
	}


}
