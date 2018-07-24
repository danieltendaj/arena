package com.stepstone.training.arena;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CreaturesRandomizer.class)
public class ArenaApplicationTests {

	@Test
	public void isValueGreaterThanMin(){

		int value = CreaturesRandomizer.randomCreatureValue(10, 20);
		assertTrue(value >= 10);
	}

	@Test
	public void isValueLessThanMax(){

		int value = CreaturesRandomizer.randomCreatureValue(10, 20);
		assertTrue(value <= 20);
	}

	@Test
	public void isValueTheSameMinMax(){

		int value = CreaturesRandomizer.randomCreatureValue(10, 10);
		assertTrue(value == 10);
	}

	@Test
	public void shouldAttackFailedWhenDexterityEqualsShield() {

		//given
		final int POTENTIAL_INJURY = 0;

		PowerMockito.mockStatic(CreaturesRandomizer.class);
		when(CreaturesRandomizer.randomCreatureValue(1, 10)).thenReturn(5);

		CreaturesFactory creaturesFactory = new CreaturesFactory();

		Elf elf = (Elf) creaturesFactory.generate(CreatureType.ELF, 5);
		Human human = (Human) creaturesFactory.generate(CreatureType.HUMAN, 5);

		//when
		int realInjury = elf.attack(human);

		//then
		assertTrue(realInjury == POTENTIAL_INJURY);

	}

	@Test
	public void shouldAttackFailedWhenDexterityLessThenShield() {

		//given
		final int POTENTIAL_INJURY = 0;

		PowerMockito.mockStatic(CreaturesRandomizer.class);
		when(CreaturesRandomizer.randomCreatureValue(1, 10)).thenReturn(6);

		CreaturesFactory creaturesFactory = new CreaturesFactory();

		Elf elf = (Elf) creaturesFactory.generate(CreatureType.ELF, 5);
		Human human = (Human) creaturesFactory.generate(CreatureType.HUMAN, 5);

		//when
		int realInjury = elf.attack(human);

		//then
		assertTrue(realInjury == POTENTIAL_INJURY);

	}

	@Test
	public void shouldAttackSuccessWhenDexterityGreaterThenShield() {

		//given
		final int POTENTIAL_INJURY = 5;

		PowerMockito.mockStatic(CreaturesRandomizer.class);
		when(CreaturesRandomizer.randomCreatureValue(1, 10)).thenReturn(4);

		CreaturesFactory creaturesFactory = new CreaturesFactory();

		Elf elf = (Elf) creaturesFactory.generate(CreatureType.ELF, 5);
		Human human = (Human) creaturesFactory.generate(CreatureType.HUMAN, 5);

		//when
		int realInjury = elf.attack(human);

		//then
		assertTrue(realInjury == POTENTIAL_INJURY);

	}

}
