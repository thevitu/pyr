package com.thevitu.pyr.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Monster implements Char, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2688899146372547300L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String name;
	
	private int hp;
	private int mp;
	private int strength;
	private int intelligence;
	private int hit;
	private int rHit;
	
	@OneToMany
	private List<Loot> loots;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getRHit() {
		return rHit;
	}
	public void setRHit(int rHit) {
		this.rHit = rHit;
	}
	public List<Loot> getLoots() {
		return loots;
	}
	public void setLoots(List<Loot> loots) {
		this.loots = loots;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
