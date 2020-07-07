package com.thevitu.pyr.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;


@Entity
public class Battle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2514909134444007760L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime startDate;
	
	private LocalDateTime lastCalcDate;
	
	private LocalDateTime endDate;
	
	private int exp;
	
	private int expGained;
	
	private int wins;
	
	private int looses;
	
	@ManyToOne
	private User user;
	
	@ManyToMany
	private List<UserHero> heroes;
	
	@ElementCollection
	@MapKeyJoinColumn(name="monster_id")
	@Column(name="quantity")
	private Map<Monster, Integer> monsters;
	
	@ElementCollection
	@MapKeyJoinColumn(name="item_id")
	@Column(name="quantity")
	private Map<Item, Integer> lootItems;

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getExpGained() {
		return expGained;
	}

	public void setExpGained(int expGained) {
		this.expGained = expGained;
	}

	public LocalDateTime getLastCalcDate() {
		return lastCalcDate;
	}

	public void setLastCalcDate(LocalDateTime lastCalcDate) {
		this.lastCalcDate = lastCalcDate;
	}

	public List<UserHero> getHeroes() {
		return heroes;
	}

	public void setHeroes(List<UserHero> heroes) {
		this.heroes = heroes;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLooses() {
		return looses;
	}

	public void setLooses(int looses) {
		this.looses = looses;
	}

	public Map<Monster, Integer> getMonsters() {
		return monsters;
	}

	public void setMonsters(Map<Monster, Integer> monsters) {
		this.monsters = monsters;
	}

	public Map<Item, Integer> getLootItems() {
		return lootItems;
	}

	public void setLootItems(Map<Item, Integer> lootItems) {
		this.lootItems = lootItems;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}
