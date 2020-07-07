package com.thevitu.pyr.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3418073611411622505L;

	public static final int MAX_GOLD_QUANTITY = 999999999;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String name;

	private boolean rerollAvailable;

	private int recruitmentPosters;
	
	private int gold;

	@ElementCollection
	@MapKeyJoinColumn(name="item_id")
	@Column(name="quantity")
	private Map<Item, Integer> items;
	
	@OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
	private List<UserHero> heroes;

	@ManyToMany
	private List<Place> unlockedPlaces;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRecruitmentPosters() {
		return recruitmentPosters;
	}

	public void setRecruitmentPosters(int recruitmentPosters) {
		this.recruitmentPosters = recruitmentPosters;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isRerollAvailable() {
		return rerollAvailable;
	}

	public void setRerollAvailable(boolean rerollAvailable) {
		this.rerollAvailable = rerollAvailable;
	}

	public List<UserHero> getHeroes() {
		return heroes;
	}

	public void setHeroes(List<UserHero> heroes) {
		this.heroes = heroes;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold < MAX_GOLD_QUANTITY ? gold : MAX_GOLD_QUANTITY;
	}

	public List<Place> getUnlockedPlaces() {
		return unlockedPlaces;
	}

	public void setUnlockedPlaces(List<Place> unlockedPlaces) {
		this.unlockedPlaces = unlockedPlaces;
	}

	public Map<Item, Integer> getItems() {
		return items;
	}

	public void setItems(Map<Item, Integer> items) {
		this.items = items;
	}

}
