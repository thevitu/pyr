package com.thevitu.pyr.util;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.thevitu.pyr.entities.BattleChar;
import com.thevitu.pyr.entities.Monster;
import com.thevitu.pyr.entities.UserHero;

public class BattleUtils {

	public static BattleChar userHeroToBattleChar(UserHero userHero) {
		BattleChar battleChar = new BattleChar();
		battleChar.setHit(userHero.getCurrentHit());
		battleChar.setHp(userHero.getCurrentHp());
		battleChar.setIntelligence(userHero.getCurrentIntelligence());
		battleChar.setMp(userHero.getCurrentMp());
		battleChar.setRHit(userHero.getCurrentRHit());
		battleChar.setStrength(userHero.getCurrentStrength());
		return battleChar;
	}
	
	public static List<BattleChar> initializeBattleMonsters(Map<Monster, Integer> mosterMap) {
		return mosterMap.entrySet().stream().flatMap(mm -> range(0, mm.getValue()).mapToObj(i -> monsterToBattleChar(mm.getKey()))).collect(toList());
	}

	public static BattleChar monsterToBattleChar(Monster monster) {
		BattleChar battleChar = new BattleChar();
		battleChar.setHit(monster.getHit());
		battleChar.setHp(monster.getHp());
		battleChar.setIntelligence(monster.getIntelligence());
		battleChar.setMp(monster.getMp());
		battleChar.setRHit(monster.getRHit());
		battleChar.setStrength(monster.getStrength());
		return battleChar;
	}
	
	public static List<BattleChar> initializeBattleHeroes(List<UserHero> userHeroes) {
		return userHeroes.stream().map(BattleUtils::userHeroToBattleChar).collect(toList());
	}

	
	public static boolean isDefeated(List<BattleChar> chars) {
		return chars.stream().filter(m -> m.getHp() > 0).count() == 0;
	}
	
	public static void battle(List<BattleChar> battleHeroes, List<BattleChar> battleMonsters) {
		shuffle(battleHeroes);
		shuffle(battleMonsters);
		for (BattleChar hero : battleHeroes) {
			Optional<BattleChar> monster = battleMonsters.stream().filter(m -> m.getHp() > 0).findAny();
			if (monster.isPresent()) {
				monster.get().setHp(monster.get().getHp() - hero.getStrength());
			}
		}
		shuffle(battleHeroes);
		shuffle(battleMonsters);
		for (BattleChar monster : battleMonsters) {
			Optional<BattleChar> hero = battleHeroes.stream().filter(m -> m.getHp() > 0).findAny();
			if (hero.isPresent()) {
				hero.get().setHp(hero.get().getHp() - monster.getStrength());
			}
		}
	}
		
}
