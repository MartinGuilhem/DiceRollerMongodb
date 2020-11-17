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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ITAcademy.DiceRollerMongoDB.dto.Game;
import com.example.ITAcademy.DiceRollerMongoDB.dto.Player;
import com.example.ITAcademy.DiceRollerMongoDB.service.GameServiceImpl;
import com.example.ITAcademy.DiceRollerMongoDB.service.PlayerServiceImpl;


@RestController
@RequestMapping("/api")
public class GameController {

	// Use of methods from Service
	@Autowired
	GameServiceImpl gameServiceImpl;
	@Autowired
	PlayerServiceImpl playerServiceImpl;

	
	// PLAYER {ID} ROLLS THE DICES (CREATE GAME)
 	@PostMapping("/players/{id}/games")
 	public ResponseEntity<Object> rollTheDices(@PathVariable(name ="id") Long id) {
 		HashMap<String, Object> map = new HashMap<>();
 		try {
 			Player player = playerServiceImpl.getPlayer(id);
 			Long gameId=gameServiceImpl.rollDices(player);
 			map.put("Game", gameServiceImpl.getGameById(gameId));
 		}catch(Exception e) {
			map.put("Success", false);
			map.put("Players", e.getMessage() + ". There is no player with this ID");
 		}
 		return ResponseEntity.ok().body(map);
 	}
	 	
	// GET GAMES FROM PLAYER
	@GetMapping("/players/{id}/games")
	public ResponseEntity<Object> listGames(@PathVariable(name = "id") Long id) {
		HashMap<String, Object> map = new HashMap<>();		
		try {
			map.put("Games", playerServiceImpl.getPlayer(id));
		}catch(Exception e) {
			map.put("Success", false);
			map.put("Players", e.getMessage());			
		}		
		return ResponseEntity.ok().body(map);
	}
 	
	// DELETE ALL GAMES FROM PLAYER
	@DeleteMapping("/players/{id}/games")
	public String deleteGames(@PathVariable(name = "id") Long id) {
		Player player = playerServiceImpl.getPlayer(id);
		List<Game> games = player.getGames();
		for (Game g : games) {
			Long gameId = g.getId();
			gameServiceImpl.deleteGame(gameId);
		}
		player.setWinAvg(0.00);
		games=new ArrayList<Game>();
		player.setGame(games);
		playerServiceImpl.updatePlayer(player);
		return "\n Games from " + player.getName() + " have been deleted";
	}
}
