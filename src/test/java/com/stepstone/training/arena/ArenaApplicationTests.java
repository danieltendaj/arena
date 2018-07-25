package com.stepstone.training.arena;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

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
		when(CreaturesRandomizer.randomCreatureValue(1, 100)).thenReturn(31);

		CreaturesFactory creaturesFactory = new CreaturesFactory();

		Creature elf = creaturesFactory.generate(CreatureType.ELF, 5);
		Creature human = creaturesFactory.generate(CreatureType.HUMAN, 5);

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
		when(CreaturesRandomizer.randomCreatureValue(1, 100)).thenReturn(31);

		CreaturesFactory creaturesFactory = new CreaturesFactory();

		Creature elf = creaturesFactory.generate(CreatureType.ELF, 5);
		Creature human = creaturesFactory.generate(CreatureType.HUMAN, 5);

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
		when(CreaturesRandomizer.randomCreatureValue(1, 100)).thenReturn(31);

		CreaturesFactory creaturesFactory = new CreaturesFactory();

		Creature elf = creaturesFactory.generate(CreatureType.ELF, 5);
		Creature human = creaturesFactory.generate(CreatureType.HUMAN, 5);

		//when
		int realInjury = elf.attack(human);

		//then
		assertTrue(realInjury == POTENTIAL_INJURY);

	}

	@Test(expected = Exception.class)
	public void shouldHitReturnExceptionWhenMissed(){

		//given
		PowerMockito.mockStatic(CreaturesRandomizer.class);
		when(CreaturesRandomizer.randomCreatureValue(1, 100)).thenReturn(31);

		CreaturesFactory creaturesFactory = new CreaturesFactory();

		Creature elf = creaturesFactory.generate(CreatureType.ELF, 5);

		BodyPart hitPart = null;
		//when
		try {
			hitPart = elf.hit();
		}
		catch (Exception e) {
			System.out.println(e);
		}

		System.out.println(hitPart.getBonus());

	}

	@Test
	public void shouldHitReturnThreeWhenHead(){

		//given
		PowerMockito.mockStatic(CreaturesRandomizer.class);
		when(CreaturesRandomizer.randomCreatureValue(1, 100)).thenReturn(5);

		CreaturesFactory creaturesFactory = new CreaturesFactory();

		Creature elf = creaturesFactory.generate(CreatureType.ELF, 5);

		BodyPart hitPart = null;
		//when
		try {
			hitPart = elf.hit();
		}
		catch (Exception e) {
			System.out.println(e);
		}

		//then
		assertTrue(hitPart.getBonus() == 3);


	}

}
