package com.thevitu.pyr.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BaseHero implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -408123887551780563L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private int lvl;
	private int expToLvlUp;
	private int hp;
	private int mp;
	private int strength;
	private int intelligence;
	private int hit;
	private int rHit;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
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
	public int getExpToLvlUp() {
		return expToLvlUp;
	}
	public void setExpToLvlUp(int expToLvlUp) {
		this.expToLvlUp = expToLvlUp;
	}
	
}
