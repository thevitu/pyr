package com.thevitu.pyr.entities;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
public class UserHero implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7018114840124674847L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@RestResource(exported = false)
	@ManyToOne(fetch = EAGER)
	private BaseHero baseHero;
	
	@RestResource(exported = false)
	@ManyToOne(fetch = EAGER)
	private Hero hero;
	
	@ManyToOne(fetch = LAZY)
	private User user;	
	
	private int bonusHp;
	private int bonusMp;
	private int bonusStrength;
	private int bonusIntelligence;
	private int bonusHit;
	private int bonusRHit;
	private int currentHp;
	private int currentMp;
	private int currentStrength;
	private int currentIntelligence;
	private int currentHit;
	private int currentRHit;
	private int exp;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getBonusHp() {
		return bonusHp;
	}
	public void setBonusHp(int bonusHp) {
		this.bonusHp = bonusHp;
	}
	public int getBonusMp() {
		return bonusMp;
	}
	public void setBonusMp(int bonusMp) {
		this.bonusMp = bonusMp;
	}
	public int getBonusStrength() {
		return bonusStrength;
	}
	public void setBonusStrength(int bonusStrength) {
		this.bonusStrength = bonusStrength;
	}
	public int getBonusIntelligence() {
		return bonusIntelligence;
	}
	public void setBonusIntelligence(int bonusIntelligence) {
		this.bonusIntelligence = bonusIntelligence;
	}
	public int getBonusHit() {
		return bonusHit;
	}
	public void setBonusHit(int bonusHit) {
		this.bonusHit = bonusHit;
	}
	public int getBonusRHit() {
		return bonusRHit;
	}
	public void setBonusRHit(int bonusRHit) {
		this.bonusRHit = bonusRHit;
	}
	public int getCurrentHp() {
		return currentHp;
	}
	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}
	public int getCurrentMp() {
		return currentMp;
	}
	public void setCurrentMp(int currentMp) {
		this.currentMp = currentMp;
	}
	public int getCurrentStrength() {
		return currentStrength;
	}
	public void setCurrentStrength(int currentStrength) {
		this.currentStrength = currentStrength;
	}
	public int getCurrentIntelligence() {
		return currentIntelligence;
	}
	public void setCurrentIntelligence(int currentIntelligence) {
		this.currentIntelligence = currentIntelligence;
	}
	public int getCurrentHit() {
		return currentHit;
	}
	public void setCurrentHit(int currentHit) {
		this.currentHit = currentHit;
	}
	public int getCurrentRHit() {
		return currentRHit;
	}
	public void setCurrentRHit(int currentRHit) {
		this.currentRHit = currentRHit;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public BaseHero getBaseHero() {
		return baseHero;
	}
	public void setBaseHero(BaseHero baseHero) {
		this.baseHero = baseHero;
	}
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
