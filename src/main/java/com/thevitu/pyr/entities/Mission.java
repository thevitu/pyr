package com.thevitu.pyr.entities;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;

@Entity
public class Mission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3257445195218096692L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String name;

	@ElementCollection
	@MapKeyJoinColumn(name="monster_id")
	@Column(name="quantity")
	private Map<Monster, Integer> monsters;
	
	private int exp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Monster, Integer> getMonsters() {
		return monsters;
	}

	public void setMonsters(Map<Monster, Integer> monsters) {
		this.monsters = monsters;
	}
	
}
