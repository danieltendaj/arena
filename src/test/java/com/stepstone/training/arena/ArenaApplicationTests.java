package com.stepstone.training.arena;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CreaturesRandomizer.class)
public class ArenaApplicationTests {



	@Test
	public void shouldAttackFailedWhenDexterityEqualsShield() {

		//given
		final int POTENTIAL_DAMAGE = 0;

		PowerMockito.mockStatic(CreaturesRandomizer.class);
		when(CreaturesRandomizer.randomCreatureValue(1, 10)).thenReturn(5);
		when(CreaturesRandomizer.randomCreatureValue(1, 100)).thenReturn(66);

		CreaturesFactory creaturesFactory = new CreaturesFactory();

		Map<ProtectionItem, Integer> map = new HashMap<>();
		map.put(ProtectionItem.HELMET, 1);
		Creature elf = creaturesFactory.generate(CreatureType.ELF, 5, map);

		//when
		AttackResult attackResult = elf.attack();

		//then
		assertTrue(attackResult.getPotentialDamage() == POTENTIAL_DAMAGE);

	}

	@Test
	public void shouldAttackFailedWhenDexterityLessThenShield() {

		//given
        final int POTENTIAL_DAMAGE = 0;

		PowerMockito.mockStatic(CreaturesRandomizer.class);
		when(CreaturesRandomizer.randomCreatureValue(1, 10)).thenReturn(6);
		when(CreaturesRandomizer.randomCreatureValue(1, 100)).thenReturn(66);

		CreaturesFactory creaturesFactory = new CreaturesFactory();

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 1);
		Creature elf = creaturesFactory.generate(CreatureType.ELF, 5, map);

		//when
        AttackResult attackResult = elf.attack();

        //then
        assertTrue(attackResult.getPotentialDamage() == POTENTIAL_DAMAGE);

	}

	@Test
	public void shouldAttackSuccessWhenDexterityGreaterThenShield() {

		//given
		final int POTENTIAL_DAMAGE = 5;

		PowerMockito.mockStatic(CreaturesRandomizer.class);
		when(CreaturesRandomizer.randomCreatureValue(1, 10)).thenReturn(4);
		when(CreaturesRandomizer.randomCreatureValue(1, 100)).thenReturn(66);

		CreaturesFactory creaturesFactory = new CreaturesFactory();

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 1);
		Creature elf = creaturesFactory.generate(CreatureType.ELF, 5, map);

		//when
        AttackResult attackResult = elf.attack();

        //then
        assertTrue(attackResult.getPotentialDamage() == POTENTIAL_DAMAGE);

	}

	@Test(expected = Exception.class)
	public void shouldHitReturnExceptionWhenMissed(){

		//given
		PowerMockito.mockStatic(CreaturesRandomizer.class);
		when(CreaturesRandomizer.randomCreatureValue(1, 100)).thenReturn(66);

		CreaturesFactory creaturesFactory = new CreaturesFactory();

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 1);
		Creature elf = creaturesFactory.generate(CreatureType.ELF, 5, map);

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

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 1);
		Creature elf = creaturesFactory.generate(CreatureType.ELF, 5, map);

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


	@Test
	public void shouldHitRandomCorrectly(){

		CreaturesFactory creaturesFactory = new CreaturesFactory();

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.HELMET, 1);
		Creature elf = creaturesFactory.generate(CreatureType.ELF, 5, map);

		BodyPart hitPart = null;

		Map<BodyPart, Integer> drawResults = new HashMap<BodyPart, Integer>();

		for (BodyPart bodyPart:BodyPart.values()){
			drawResults.put(bodyPart, 0);
		}

		for (int i = 1; i < 10000; i++){
			try {
				hitPart = elf.hit();
				drawResults.put(hitPart, drawResults.get(hitPart) + 1);
			}
			catch (Exception e) {
				//System.out.println(e);
			}
		}

		for (Map.Entry<BodyPart, Integer> entry : drawResults.entrySet()){
			System.out.println("BodyPart: " + entry.getKey() + ", Occurences: " + entry.getValue() / 100);
		}

	}

}
