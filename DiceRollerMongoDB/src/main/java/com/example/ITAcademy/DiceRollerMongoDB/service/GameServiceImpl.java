package com.example.ITAcademy.DiceRollerMongoDB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ITAcademy.DiceRollerMongoDB.dao.IGameDAO;
import com.example.ITAcademy.DiceRollerMongoDB.dto.Game;
import com.example.ITAcademy.DiceRollerMongoDB.dto.Player;

@Service
public class GameServiceImpl implements IGameService {


	// Use of methods from repository DAO
	@Autowired
	IGameDAO iGameDAO;
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
	public List<Game> listGames(Player player) {
		return iGameDAO.findAllByPlayer(player);
	}

	// Delete Game
	@Override
	public void deleteGame(Long gameId) {
		iGameDAO.deleteById(gameId);
	}
	
	// Roll the dices
	@Override
	public Long rollDices(Player player) {		
		int dice1=(int) (Math.random()*(6-1+1)+1); 
		int dice2=(int) (Math.random()*(6-1+1)+1); 
		boolean won=won(dice1, dice2);
		Game game = new Game(null, dice1, dice2, won, player);
		this.addGame(game);
		player.setGame(game);
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
