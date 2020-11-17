package com.example.ITAcademy.DiceRollerMongoDB.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ITAcademy.DiceRollerMongoDB.dto.Game;
import com.example.ITAcademy.DiceRollerMongoDB.dto.Player;
import com.example.ITAcademy.DiceRollerMongoDB.service.GameServiceImpl;
import com.example.ITAcademy.DiceRollerMongoDB.service.PlayerServiceImpl;

@RestController
@RequestMapping("/api")
public class PlayerController {

	// Use of methods from Service
	@Autowired
	PlayerServiceImpl playerServiceImpl;
	@Autowired
	GameServiceImpl gameServiceImpl;
	
	
	// CREATE PLAYER
	@PostMapping("/players")
	public Player createPlayer(@RequestBody Player player) {
		player.setWinAvg(0.00);
		Long id;
		if(player.getId()==null) {
			List<Player> players = playerServiceImpl.listPlayers();
			if(players.size()!=0) {
				id = (players.get(players.size()-1).getId())+1;
			}
			else {
				id = (long)1;
			}
			player.setId(id);
		}
		return playerServiceImpl.createPlayer(player);
	}

	// GET ALL PLAYERS WITH THEIR WINAVG
	@GetMapping("/players")
	public ResponseEntity<Object> getPlayers() {
		HashMap<String, Object> map = new HashMap<>();
		try {
			map.put("Success", true);
			map.put("Players", playerServiceImpl.listPlayers());
		}catch(Exception e) {
			map.put("Success", false);
			map.put("Players", e.getMessage());
		}
		return ResponseEntity.ok().body(map);
	}
	
	// UPDATE PLAYER NAME
	@PutMapping("/players/{id}")
	public Player updatePlayer(@PathVariable(name = "id") Long id, @RequestBody Player player) {
		Player playerToUpdate = playerServiceImpl.getPlayer(id);
		playerToUpdate.setName(player.getName());
		return playerServiceImpl.updatePlayer(playerToUpdate);
	}
	
	// GET TOTAL RANKING
	@GetMapping("/players/ranking")
	public ResponseEntity<Object> getRanking() {		
		HashMap<String, Object> map = new HashMap<>();
		try {
			List<Player> players = playerServiceImpl.listPlayers();
			map.put("Success", true);
			map.put("The total Average Ranking of All Players is", playerServiceImpl.getRanking(players));
		}catch(Exception e) {
			map.put("Success", false);
			map.put("Players", e.getMessage());			
		}				
		return ResponseEntity.ok().body(map);	
	}
		
	// GET PLAYER WITH LOWEST WINAVG
	@GetMapping("/players/ranking/loser")
	public ResponseEntity<Object> getLoser() {
		HashMap<String, Object> map = new HashMap<>();		
		try {
			map.put("Success", true);
			map.put("Loser", playerServiceImpl.Loser());
		}catch(Exception e) {
			map.put("Success", false);
			map.put("Players", e.getMessage());			
		}		
		return ResponseEntity.ok().body(map);			
	}
	
	// GET PLAYER WITH HIGHEST WINAVG
	@GetMapping("/players/ranking/winner")
	public ResponseEntity<Object> getWinner() {
		HashMap<String, Object> map = new HashMap<>();		
		try {
			map.put("Success", true);
			map.put("Winner", playerServiceImpl.Winner());
		}catch(Exception e) {
			map.put("Success", false);
			map.put("Players", e.getMessage());			
		}		
		return ResponseEntity.ok().body(map);
	}

	
	// DELETE PLAYER BY ID
	@DeleteMapping("/players/{id}")
	public void deletePlayer(@PathVariable(name = "id") Player player) {
		Long id=player.getId();
		List<Game> games = player.getGames();
		for (Game g : games) {
			Long gameId = g.getId();
			gameServiceImpl.deleteGame(gameId);
		}
		playerServiceImpl.deletePlayer(id);
	}
	
	// DELETE ALL PLAYERS
	@DeleteMapping("/players/delete")
	public void deletePlayers() {
		gameServiceImpl.deleteGames();
		playerServiceImpl.deletePlayers();
	}

}
