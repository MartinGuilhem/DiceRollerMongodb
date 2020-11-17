package com.example.ITAcademy.DiceRollerMongoDB.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import com.mongodb.lang.NonNull;

@Document(collection = "game")
public class Game { 

	// ATTRIBUTES
	@Id
	@NonNull
	Long id;	
	int dice1;
	int dice2;
	boolean won;
	Long player_id;	// Entities relationship one to one
	
	// CONSTRUCTORS
	public Game() {}

	public Game(Long id, int dice1, int dice2, boolean won, Long player_id) {
		this.id = id;
		this.dice1 = dice1;
		this.dice2 = dice2;
		this.won = won;
		this.player_id = player_id;
	}

	// GETTERS AND SETTERS
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDice1() {
		return dice1;
	}

	public void setDice1(int dice1) {
		this.dice1 = dice1;
	}

	public int getDice2() {
		return dice2;
	}

	public void setDice2(int dice2) {
		this.dice2 = dice2;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public Long getPlayer() {
		return player_id;
	}

	public void setPlayer(Long player_id) {
		this.player_id = player_id;
	}

	@Override
	public String toString() {
		return "Game [ id="+ id + ", dice1=" + dice1 + ", dice2=" + dice2 + ", Win?=" + won + ", player_id=" + player_id
				+ "]";
	}

	
}
