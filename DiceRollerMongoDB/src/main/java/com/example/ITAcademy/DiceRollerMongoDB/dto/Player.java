package com.example.ITAcademy.DiceRollerMongoDB.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Document(collection = "player")
public class Player /* implements Serializable */{
		
	// ATTRIBUTES
	
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	@NonNull
	Long id;

//	Long seq;
	
	String name;
	
	//@Temporal(TemporalType.TIMESTAMP)
	Date date = new Date(System.currentTimeMillis());
	
	Double winAvg;

	// Entities relationship
	//@OneToMany(mappedBy = "player")
	//@JsonIgnore // To fix issue with infinite recursion
	private List<Game> game;

	
	
		// CONSTRUCTORS
	public Player() {}

	public Player(Long id, String name) {
		this.id = id;
		this.name = addName(name);
		this.date = new Date(System.currentTimeMillis());
	}

		// GETTERS & SETTERS
	
	
	
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
		this.name = addName(name);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getWinAvg() {
		return winAvg;
	}

	public void setWinAvg(Double winAvg) {
		if(winAvg==null)
			winAvg=0.00;
		else 
			this.winAvg = winAvg;
	}

	public List<Game> getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game.add(game);
	}

		// METHODS

	// Set author as Anonymous if name is null
	private String addName(String name) {
		if (name == null)
			name = "Anonymous";
		return name;
	}

	// SETTING WINAVG FROM GAME
	public void updateWinAvGames() {
		int gamesWon = 0;
		for (Game g : game) { 
			if (g.isWon())
				gamesWon++;
		}
		double winAverage=(double) gamesWon / (double) game.size();
		this.setWinAvg(winAverage);
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name= " + name + ", winAvg=" + winAvg + ", entry date=" + date + "  ]";
	}

}
