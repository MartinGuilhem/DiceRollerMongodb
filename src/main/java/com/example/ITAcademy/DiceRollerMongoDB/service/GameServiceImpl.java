package com.example.ITAcademy.DiceRollerMongoDB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ITAcademy.DiceRollerMongoDB.dto.Game;
import com.example.ITAcademy.DiceRollerMongoDB.dto.Player;
import com.example.ITAcademy.DiceRollerMongoDB.repository.IGameRepository;

@Service
public class GameServiceImpl implements IGameService {


	// Use of methods from repository DAO
	@Autowired
	IGameRepository iGameDAO;
	@Autowired 
	PlayerServiceImpl playerServiceImpl;

	// Create Game
	@Override
	public Game addGame(Game game) {
		return iGameDAO.save(game);
	}
	
	// GET game By ID
	@Override
	public Game getGameById(Long gameId) {
		return iGameDAO.findById(gameId).get();
	}
		
	// Get games from player
	@Override
	public List<Game> listGames(Long player_id) {
		return iGameDAO.findAllById(player_id);
	}

	// Delete Game
	@Override
	public void deleteGame(Long gameId) {
		iGameDAO.deleteById(gameId);
	}

	// List all games
	@Override
	public List<Game> listGames() {
		return iGameDAO.findAll();
	}
	
	// Roll the dices
	@Override
	public Long rollDices(Player player) {		
		int dice1=(int) (Math.random()*(6-1+1)+1); 
		int dice2=(int) (Math.random()*(6-1+1)+1); 
		boolean won=won(dice1, dice2);
		
		Long player_id = player.getId();
		Game game = new Game(null, dice1, dice2, won, player_id);
		List<Game> games = listGames();
		if(games.size()!=0) {
			game.setId((games.get(games.size()-1).getId())+1);
		}
		else {
			game.setId((long)1);			
		}
		System.out.println(game.toString());
		this.addGame(game); // save game to database
		player.addGame(game);
		player.updateWinAvGames();
		playerServiceImpl.updatePlayer(player);
		return game.getId();
	}
	
	// Win or Not
	@Override
	public boolean won(int dice1, int dice2) {
		if(dice1+dice2==7) 
			return true;		
		else 
			return false;
	}

	// Delete all games
	@Override
	public void deleteGames() {
		iGameDAO.deleteAll();		
	}

}
