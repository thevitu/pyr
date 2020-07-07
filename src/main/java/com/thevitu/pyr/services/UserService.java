package com.thevitu.pyr.services;

import static com.thevitu.pyr.services.HeroService.GROUP_SIZE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.thevitu.pyr.entities.Hero;
import com.thevitu.pyr.entities.Place;
import com.thevitu.pyr.entities.User;
import com.thevitu.pyr.entities.UserHero;
import com.thevitu.pyr.user.repositories.PlaceRepository;
import com.thevitu.pyr.user.repositories.UserRepository;

@RestController
@Transactional
public class UserService {
	
	public static final Long INITIAL_PLACE_ID = 1l;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PlaceRepository placeRepository;
	
	@Autowired
	private HeroService heroService;
	
	public Optional<User> getUser(Long id) {
		return this.userRepository.findById(id);
	}
	
	public User createNewUser(String userName) {
		User user = new User();
		user.setName(userName);
		user.setRecruitmentPosters(0);
		user.setRerollAvailable(true);
		user.setUnlockedPlaces(new ArrayList<Place>());
		user.getUnlockedPlaces().add(this.placeRepository.getOne(INITIAL_PLACE_ID));
		return this.userRepository.save(user);
	}

	
	public Hero recruitHero(Long userId) {
		Hero hero = this.heroService.getRandomHero();
		User user = this.userRepository.getOne(userId);
		if (user.getRecruitmentPosters() < 1) {
			throw new IllegalAccessError("this user does not have enough recruitment posters");
		}
		user.setRecruitmentPosters(user.getRecruitmentPosters() - 1);
		addRandomHeroToUser(hero, user);		
		this.userRepository.save(user);
		return hero;
	}
	
	public List<Hero> recruitGroup(Long userId) {
		User user = this.userRepository.getOne(userId);
		if (user.getRecruitmentPosters() < GROUP_SIZE) {
			throw new IllegalAccessError("this user does not have enough recruitment posters");
		}
		user.setRecruitmentPosters(user.getRecruitmentPosters() - GROUP_SIZE);
		List<Hero> group = new ArrayList<Hero>();
		for (int x = 0; x < GROUP_SIZE; x++) {
			Hero hero = this.heroService.getRandomHero();
			addRandomHeroToUser(hero, user);
			group.add(hero);
		}		
		this.userRepository.save(user);
		return group;
	}

	private void addRandomHeroToUser(Hero hero, User user) {
		UserHero userHero =  this.heroService.generateNewUserHero(hero);		
		userHero.setUser(user);
		if (user.getHeroes() == null) {
			user.setHeroes(new ArrayList<UserHero>());
		}
		user.getHeroes().add(userHero);
	}
	
	@Transactional
	public List<Hero> reroll(Long userId) {
		User user = this.userRepository.getOne(userId);
		if (! user.isRerollAvailable()) {
			throw new IllegalAccessError("this user cannot do rerolls");
		}
		user.setRecruitmentPosters(5);
		user.getHeroes().clear();
		return recruitGroup(userId);
	}
	
	public void rerollFinish(Long userId) {
		User user = this.userRepository.getOne(userId);
		user.setRerollAvailable(false);
		this.userRepository.save(user);
	}
	
}
