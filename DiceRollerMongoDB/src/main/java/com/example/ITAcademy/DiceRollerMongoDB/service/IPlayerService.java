package com.example.ITAcademy.DiceRollerMongoDB.service;

import java.util.List;

import com.example.ITAcademy.DiceRollerMongoDB.dto.Player;

public interface IPlayerService {

	// CRUD METHODS

		// Create player
		public Player createPlayer(Player player);

		// Get all players
		public List<Player> listPlayers();

		// Get Player by id
		public Player getPlayer(Long id);

		// Update Player
		public Player updatePlayer(Player player);

		// Get total ranking of all players
		public Double getRanking(List<Player> players);
		
		// Get player with lowest ranking
		public Player Loser();

		// Get player with highest ranking
		public Player Winner();
		
		// Delete player by id
		public void deletePlayer(Long id);
		
		// Delete all players and games
		public void deletePlayers();

}
