package com.thevitu.pyr.services;

import static com.thevitu.pyr.util.BattleUtils.battle;
import static com.thevitu.pyr.util.BattleUtils.initializeBattleHeroes;
import static com.thevitu.pyr.util.BattleUtils.initializeBattleMonsters;
import static com.thevitu.pyr.util.BattleUtils.isDefeated;
import static java.lang.Math.round;
import static java.util.stream.Collectors.toList;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.thevitu.pyr.entities.Battle;
import com.thevitu.pyr.entities.BattleChar;
import com.thevitu.pyr.entities.Item;
import com.thevitu.pyr.entities.Loot;
import com.thevitu.pyr.entities.Mission;
import com.thevitu.pyr.entities.Monster;
import com.thevitu.pyr.entities.User;
import com.thevitu.pyr.entities.UserHero;
import com.thevitu.pyr.user.repositories.BattleRepository;
import com.thevitu.pyr.user.repositories.MissionRepository;
import com.thevitu.pyr.user.repositories.UserHeroRepository;
import com.thevitu.pyr.user.repositories.UserRepository;

@RestController
public class MissionService {
	
	@Autowired
	private MissionRepository missionRepository;

	@Autowired
	private UserHeroRepository userHeroRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BattleRepository battleRepository;
	
	private static Random rand = new Random();
	
	@Transactional
	public Battle startMission(Long userId, List<Long> heroesIds, Long missionId) {
		if (heroesIds == null || heroesIds.isEmpty() || missionId == null) {
			throw new IllegalArgumentException("Invalid mission or heroes ids");
		}
		LocalDateTime now = LocalDateTime.now();
		Mission mission = this.missionRepository.getOne(missionId);
		User user = this.userRepository.getOne(userId);
		List<UserHero> userHeroes = heroesIds.stream().map(this.userHeroRepository::getOne).collect(toList());
		if (! validateUserHeroes(user, userHeroes)) {
			throw new IllegalArgumentException("Invalid user heros");
		}
		Battle battle = new Battle();
		battle.setExp(round(mission.getExp() * 5 / userHeroes.size()));
		battle.setExpGained(0);
		battle.setStartDate(now);
		battle.setEndDate(null);
		battle.setLastCalcDate(now);
		battle.setWins(0);
		battle.setLooses(0);
		battle.setUser(user);
		battle.setHeroes(userHeroes);
		battle.setMonsters(new HashMap<Monster, Integer>()); // initialize a new collection to avoid HibernateException: Found shared references to a collection
		battle.getMonsters().putAll(mission.getMonsters());
		battle.setLootItems(new HashMap<Item, Integer>());
		this.battleRepository.save(battle);
		return battle;
	}
	
	@Transactional
	public Battle battleReport(Long battleId) {
		Battle battle = battleSimulation(battleId);
		this.battleRepository.save(battle);
		return battle;
	}
	
	@Transactional
	public Battle finishMission(Long battleId) {
		Battle battle = battleSimulation(battleId);
		battle.setExpGained(battle.getExp() * battle.getWins());
		battle.setEndDate(battle.getLastCalcDate());
		//TODO: loot itens, need refactory 
		battle.setLootItems(new HashMap<Item, Integer>());
		ArrayList<Loot> loots = new ArrayList<Loot>();
		for (Monster monster : battle.getMonsters().keySet()) {
			for (int x = 0; x < battle.getMonsters().get(monster); x++) {
				loots.addAll(monster.getLoots());
			}
		}
		for (int x = 0; x < battle.getWins(); x++) {
			for (Loot loot : loots) {
				if (rand.nextInt(1000000) < loot.getChance() * 10000) {
					battle.getLootItems().merge(loot.getItem(), 1, Integer::sum);
				}
			}
		}
		battle.getHeroes().forEach(h -> h.setExp(h.getExp() + battle.getExpGained()));
		battle.getLootItems().forEach((k, v) -> battle.getUser().getItems().merge(k, v, Integer::sum));
		this.battleRepository.save(battle);
		return battle;
	}
	
	private Battle battleSimulation(Long battleId) {
		Battle battle = this.battleRepository.getOne(battleId);
		List<BattleChar> battleHeroes = initializeBattleHeroes(battle.getHeroes());
		List<BattleChar> battleMonsters = initializeBattleMonsters(battle.getMonsters());
		LocalDateTime now = LocalDateTime.now();		
		long rounds = Duration.between(battle.getLastCalcDate(), now).getSeconds();
		for (long x = 0; x < rounds; x++) {
			if (isDefeated(battleMonsters)) {
				battle.setWins(battle.getWins() + 1);
				battleHeroes = initializeBattleHeroes(battle.getHeroes());
				battleMonsters = initializeBattleMonsters(battle.getMonsters());
			} else if (isDefeated(battleHeroes)) {
				battle.setLooses(battle.getLooses() + 1);
				battleHeroes = initializeBattleHeroes(battle.getHeroes());
				battleMonsters = initializeBattleMonsters(battle.getMonsters());
			} else {
				battle(battleHeroes, battleMonsters);
			}
		}
		battle.setLastCalcDate(now);		
		return battle;
	}

	private boolean validateUserHeroes(User user, List<UserHero> userHeroes) {
		 //TODO: refactory to perform
		boolean valid = false;
		if (userHeroes.stream().filter(u -> u.getUser().equals(user)).count() ==  userHeroes.size()) {
			valid = true;
		}
		return valid;
	}
	
}
