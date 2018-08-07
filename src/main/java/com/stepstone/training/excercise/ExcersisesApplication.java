package com.stepstone.training.excercise;

import com.stepstone.training.arena.Creature;
import com.stepstone.training.arena.CreaturesFactory;

import java.util.List;

public class ExcersisesApplication {

	public static void main(String[] args) {

		CreaturesFactory creaturesFactory = new CreaturesFactory();

		List<Creature> list = creaturesFactory.randomCreatureList(10);

		System.out.println("Before sorting");
		for (Creature x:list) {
			System.out.println(x.toString());
		}

		LambdaExcercises lambdaExcercises = new LambdaExcercises();
		lambdaExcercises.sortLifePointsWithoutLambda(list);

		System.out.println("After sorting");
		for (Creature x:list) {
			System.out.println(x.toString());
		}

	}
}