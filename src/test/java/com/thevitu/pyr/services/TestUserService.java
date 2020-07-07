package com.thevitu.pyr.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.thevitu.pyr.entities.Hero;
import com.thevitu.pyr.entities.Place;
import com.thevitu.pyr.entities.User;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TestUserService {

	@Autowired
	private UserService userService;
	
	private static Long userId;
		
	@Test
	@Order(1)
	public void testCreateNewUser() {
		User user = this.userService.createNewUser("test" + new Random().nextInt());
		assertTrue(user.getId() != null);
		assertTrue(user.getId() > 0);
		userId = user.getId(); // populate id to next tests
	}

	@Test
	@Order(2)
	public void testGetUser() {		
		assertTrue(this.userService.getUser(userId).isPresent());
	}
	
	@RepeatedTest(5)
	@Order(3)
	public void testReroll() {
		List<Hero> heroes = this.userService.reroll(userId);		
		assertTrue(heroes != null);
		assertFalse(heroes.isEmpty());
		assertEquals(heroes.size(), HeroService.GROUP_SIZE);	
		//TODO: test exceptions
	}

	@Test
	@Order(4)
	@Transactional
	public void testAfterReroll() {
		assertEquals(this.userService.getUser(userId).get().getHeroes().size(), HeroService.GROUP_SIZE);
	}

	@Test
	@Order(5)
	public void rerollFinish() {
		try {
			this.userService.rerollFinish(userId);
		} catch (Throwable e) {
			fail();
		}
	}
	
	@Test
	@Order(6)
	public void testRecruitGroup() {
		try {
			this.userService.recruitGroup(userId);
			fail();
		} catch (Throwable e) {
			assertTrue(e instanceof IllegalAccessError);
		}
		//TODO: test success
	}

	@Test
	@Order(7)
	public void testRecruitHero() {
		try {
			this.userService.recruitGroup(userId);
			fail();
		} catch (Throwable e) {
			assertTrue(e instanceof IllegalAccessError);
		}
		//TODO: test success
	}

	@Test
	@Order(8)
	@Transactional
	public void tesGetUnlockedPlaces() {
		List<Place> places = this.userService.getUser(userId).get().getUnlockedPlaces();
		assertTrue(places != null);
		assertFalse(places.isEmpty());
		assertTrue(places.size() == 1);
		assertEquals(UserService.INITIAL_PLACE_ID, places.get(0).getId());
	}
	
}
