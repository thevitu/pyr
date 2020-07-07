package com.thevitu.pyr.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class QuestItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -891655746307186393L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Quest quest;
	
	private boolean requirement;
	
	private boolean reward;

	@ManyToOne
	private Item item;
	
	private int quantity;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Quest getQuest() {
		return quest;
	}

	public void setQuest(Quest quest) {
		this.quest = quest;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isRequirement() {
		return requirement;
	}

	public void setRequirement(boolean requirement) {
		this.requirement = requirement;
	}

	public boolean isReward() {
		return reward;
	}

	public void setReward(boolean reward) {
		this.reward = reward;
	}
	
	

}
