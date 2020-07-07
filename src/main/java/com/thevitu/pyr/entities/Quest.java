package com.thevitu.pyr.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Quest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6387035281235658761L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String name;
	
	private int goldRequirement;
	
	private int goldReward;
	
	@OneToMany(mappedBy = "quest", cascade = ALL, orphanRemoval = true)
	private List<QuestItem> questItems;
	
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

	public int getGoldRequirement() {
		return goldRequirement;
	}

	public void setGoldRequirement(int goldRequirement) {
		this.goldRequirement = goldRequirement;
	}

	public int getGoldReward() {
		return goldReward;
	}

	public void setGoldReward(int goldReward) {
		this.goldReward = goldReward;
	}

	public List<QuestItem> getQuestItems() {
		return questItems;
	}

	public void setQuestItems(List<QuestItem> questItems) {
		this.questItems = questItems;
	}

}
