package com.example.ITAcademy.DiceRollerMongoDB.service;

import java.util.List;

import com.example.ITAcademy.DiceRollerMongoDB.dto.Game;
import com.example.ITAcademy.DiceRollerMongoDB.dto.Player;

public interface IGameService {

	// Create Game
	public Game addGame(Game game);

	// Get game By ID
	public Game getGameById(Long gameId);

	// Get games from player
	public List<Game> listGames(Long player_id);

	// List all games
	public List<Game> listGames();

	// Delete Game
	public void deleteGame(Long gameId);

	// Delete all games
	public void deleteGames();

	// Roll the dices
	public Long rollDices(Player player);

	// Win or Not
	public boolean won(int dice1, int dice2);

}
