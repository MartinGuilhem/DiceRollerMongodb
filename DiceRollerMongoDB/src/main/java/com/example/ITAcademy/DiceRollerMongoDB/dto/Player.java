package com.example.ITAcademy.DiceRollerMongoDB.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Document(collection = "player")
public class Player {
		
	// ATTRIBUTES

	@Id
	@NonNull
	Long id;
	String name;
	Date date = new Date(System.currentTimeMillis());
	Double winAvg;
	List<Game> games= new ArrayList<Game>(); // Entities relationship One to Many

	
		// CONSTRUCTORS
	public Player() {}

	public Player(Long id, String name) {
		this.id = id;
		this.name = addName(name);
		this.date = new Date(System.currentTimeMillis());
		this.winAvg=0.00;
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

	public List<Game> getGames() {
		return games;
	}

	public void setGame(List<Game> games) {
		this.games=games;
	}
	
	public void addGame(Game game) {
		this.games.add(game);
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
		if(games.size()!=0) {
			for (Game g : games)  
				if (g.isWon())
					gamesWon++;
			double winAverage=(double) gamesWon / (double) games.size();
			this.setWinAvg(winAverage);
		}
		else {
			this.setWinAvg(0.00);			
		}		
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name= " + name + ", winAvg=" + winAvg + ", entry date=" + date + "  ]";
	}

}
