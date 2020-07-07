package com.thevitu.pyr.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hero implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8395975201135499124L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String name;
	private int bonusHp;
	private int bonusMp;
	private int bonusStrength;
	private int bonusIntelligence;
	private int bonusHit;
	private int bonusRHit;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

}
