package com.thevitu.pyr.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.thevitu.pyr.entities.Hero;
import com.thevitu.pyr.entities.UserHero;
import com.thevitu.pyr.user.repositories.BaseHeroRepository;
import com.thevitu.pyr.user.repositories.HeroRepository;

@RestController
public class HeroService {
	
	public static final int GROUP_SIZE = 5;

	private static final int INITIAL_LVL = 1;

	private static List<Hero> allHeroes = null;

	@Autowired
	private BaseHeroRepository baseHeroRepository;
	
	@Autowired
	private HeroRepository heroRepository;

	public Optional<Hero> getHero(long id) {
		return this.heroRepository.findById(id);
	}
	
	public List<Hero> getAllHeroes() {
		if (HeroService.allHeroes == null) {
			allHeroes = this.heroRepository.findAll();
		}
		return HeroService.allHeroes;
	}
	
	public Hero getRandomHero() {
		return this.getAllHeroes().get(new Random().nextInt(this.getAllHeroes().size()));
	}
	
	public UserHero generateNewUserHero(Hero hero) {
		UserHero userHero = new UserHero();
		
		userHero.setHero(hero);
		userHero.setBaseHero(this.baseHeroRepository.getByLvl(INITIAL_LVL));
		
		userHero.setBonusHp(hero.getBonusHp());
		userHero.setBonusMp(hero.getBonusMp());
		userHero.setBonusStrength(hero.getBonusStrength());
		userHero.setBonusIntelligence(hero.getBonusIntelligence());
		userHero.setBonusHit(hero.getBonusHit());
		userHero.setBonusRHit(hero.getBonusRHit());

		userHero.setCurrentHp(userHero.getBaseHero().getHp() + hero.getBonusHp());
		userHero.setCurrentMp(userHero.getBaseHero().getMp() + hero.getBonusMp());
		userHero.setCurrentStrength(userHero.getBaseHero().getStrength() + hero.getBonusStrength());
		userHero.setCurrentIntelligence(userHero.getBaseHero().getIntelligence() + hero.getBonusIntelligence());
		userHero.setCurrentHit(userHero.getBaseHero().getHit() + hero.getBonusHit());
		userHero.setCurrentRHit(userHero.getBaseHero().getRHit() + hero.getBonusRHit());

		userHero.setExp(0);
		
		return userHero;
	}
	
}
