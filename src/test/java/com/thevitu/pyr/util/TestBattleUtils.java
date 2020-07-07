package com.thevitu.pyr.util;

import static com.thevitu.pyr.util.BattleUtils.initializeBattleHeroes;
import static com.thevitu.pyr.util.BattleUtils.initializeBattleMonsters;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.thevitu.pyr.entities.BattleChar;
import com.thevitu.pyr.entities.Loot;
import com.thevitu.pyr.entities.Mission;
import com.thevitu.pyr.entities.Monster;
import com.thevitu.pyr.entities.UserHero;

public class TestBattleUtils {
	
	
	private static UserHero userHero;
	private static UserHero userHero2;
	private static Monster monster;
	private static Mission mission;
	private static Monster monster2;

	@BeforeAll
	public static void init() {
		// init user hero
		userHero = new UserHero();
		userHero.setCurrentHit(70);
		userHero.setCurrentHp(100);
		userHero.setCurrentIntelligence(20);
		userHero.setCurrentMp(30);
		userHero.setCurrentRHit(60);
		userHero.setCurrentStrength(40);
		
		userHero2 = new UserHero();
		userHero2.setCurrentHit(71);
		userHero2.setCurrentHp(101);
		userHero2.setCurrentIntelligence(21);
		userHero2.setCurrentMp(31);
		userHero2.setCurrentRHit(61);
		userHero2.setCurrentStrength(41);
		// init monster
		monster = new Monster();
		monster.setHit(65);
		monster.setHp(75);
		monster.setIntelligence(15);
		monster.setMp(15);
		monster.setRHit(55);
		monster.setStrength(22);
		monster.setLoots(new ArrayList<Loot>());
		
		monster2 = new Monster();
		monster2.setHit(66);
		monster2.setHp(76);
		monster2.setIntelligence(16);
		monster2.setMp(16);
		monster2.setRHit(56);
		monster2.setStrength(26);
		monster2.setLoots(new ArrayList<Loot>());
		
		// init mission
		mission = new Mission();
		mission.setExp(10);
		mission.setMonsters(new HashMap<Monster, Integer>());
		
		// init mission monster
		mission.getMonsters().put(monster, 3);
		mission.getMonsters().put(monster2, 2);
	}
	
	@Test
	public void testUserHeroToBattleChar() {
		BattleChar battleChar = BattleUtils.userHeroToBattleChar(userHero);
		assertEquals(battleChar.getHit(), userHero.getCurrentHit());
		assertEquals(battleChar.getHp(), userHero.getCurrentHp());
		assertEquals(battleChar.getIntelligence(), userHero.getCurrentIntelligence());
		assertEquals(battleChar.getMp(), userHero.getCurrentMp());
		assertEquals(battleChar.getRHit(), userHero.getCurrentRHit());
		assertEquals(battleChar.getStrength(), userHero.getCurrentStrength());
	}

	@Test
	public void testMonsterToBattleChar() {
		BattleChar battleChar = BattleUtils.monsterToBattleChar(monster);
		assertEquals(battleChar.getHit(), monster.getHit());
		assertEquals(battleChar.getHp(), monster.getHp());
		assertEquals(battleChar.getIntelligence(), monster.getIntelligence());
		assertEquals(battleChar.getMp(), monster.getMp());
		assertEquals(battleChar.getRHit(), monster.getRHit());
		assertEquals(battleChar.getStrength(), monster.getStrength());
	}
	
	@Test
	public void testInitializeBattleHeroes() {
		List<UserHero> userHeroes = new ArrayList<UserHero>();
		List<BattleChar> battleHeroes = BattleUtils.initializeBattleHeroes(userHeroes);
		assertTrue(battleHeroes != null);
		assertTrue(battleHeroes.isEmpty());
		userHeroes.add(userHero);
		battleHeroes = BattleUtils.initializeBattleHeroes(userHeroes);
		assertFalse(battleHeroes.isEmpty());
		assertTrue(battleHeroes.size() == 1);
		userHeroes.add(userHero2);
		battleHeroes = BattleUtils.initializeBattleHeroes(userHeroes);
		assertFalse(battleHeroes.isEmpty());
		assertTrue(battleHeroes.size() == 2);
		assertNotEquals(battleHeroes.get(0), battleHeroes.get(1));
		assertNotEquals(battleHeroes.get(0).getHp(), battleHeroes.get(1).getHp());
	}
	
	@Test
	public void testInitializeBattleMonsters() {
		List<BattleChar> battleMonsters = BattleUtils.initializeBattleMonsters(new HashMap<Monster, Integer>());
		assertTrue(battleMonsters != null);
		assertTrue(battleMonsters.isEmpty());
		battleMonsters = BattleUtils.initializeBattleMonsters(mission.getMonsters());
		assertFalse(battleMonsters.isEmpty());
		assertTrue(battleMonsters.size() == 5);
	}

	@Test
	public void testIsDefeated() {
		 assertFalse(BattleUtils.isDefeated(BattleUtils.initializeBattleMonsters(mission.getMonsters())));
	}
	
	@Test
	public void battle() {
		List<UserHero> userHeroes = new ArrayList<UserHero>();
		userHeroes.add(userHero);
		userHeroes.add(userHero2);
		List<BattleChar> battleHeroes = initializeBattleHeroes(userHeroes);
		List<BattleChar> battleMonsters = initializeBattleMonsters(mission.getMonsters());
		BattleUtils.battle(battleHeroes, battleMonsters);
		assertTrue(battleHeroes.stream().mapToInt(b -> b.getHp()).sum() == 83);
	}

}
