package com.example.ITAcademy.DiceRollerMongoDB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ITAcademy.DiceRollerMongoDB.service.IPlayerService;
import com.example.ITAcademy.DiceRollerMongoDB.dao.IPlayerDAO;
import com.example.ITAcademy.DiceRollerMongoDB.dto.Player;

@Service
public class PlayerServiceImpl implements IPlayerService {

	
	// Use of methods from repository DAO
	@Autowired
	IPlayerDAO iPlayerDAO;
	
	// Create player
	@Override
	public Player createPlayer(Player player) {
		return iPlayerDAO.save(player);
	}

	// Get all players with their winAvg
	@Override
	public List<Player> listPlayers() {
		return iPlayerDAO.findAll();
	}
	
	// Get player by id
	@Override
	public Player getPlayer(Long id) {
		return iPlayerDAO.findById(id).get();
	}
	
	// Update player
	@Override
	public Player updatePlayer(Player player) {
		return iPlayerDAO.save(player);
	}
	
	// Get total ranking of all players
	@Override
	public Double getRanking(List<Player> players) {
		double ranking=0.00;
		double sumRanking=0.00;
		for (int i=0;i<players.size();i++) {
			ranking=players.get(i).getWinAvg();
			sumRanking = sumRanking + ranking;
		}
		System.out.println("### \n\n #La suma total de los rankings es ="+sumRanking);
		System.out.println("### \n\n #La cantidad de jugadores es ="+players.size());
		return sumRanking / (double) players.size();
	}

	// Get player with lowest ranking
	@Override
	public Player Loser() {
		List<Player> players = this.listPlayers();
		Long id = null;
		int i = 0;
		double min = 100.00;
		for (i = 0; i < players.size(); i++) {
			if (players.get(i).getWinAvg() < min) {
				min = players.get(i).getWinAvg();
				System.out.println("\n\n### ## # winAvg= "+min);
				id = players.get(i).getId();
			}
		}
		return this.getPlayer(id);
	}

	// Get player with highest ranking
	@Override
	public Player Winner() {
		List<Player> players = this.listPlayers();
		Long id = null;
		int i = 0;
		double max = 0.00;
		for (i = 0; i < players.size(); i++) {
			System.out.println("\n\n### ## winAvg "+i+"= "+players.get(i).getWinAvg());
			if (players.get(i).getWinAvg() > max) {
				max = players.get(i).getWinAvg();
				System.out.println("\n\n### ## # winAvg= "+max);
				id = players.get(i).getId();
			}
		}
		return this.getPlayer(id);
	}

	// Delete player by id
	@Override
	public void deletePlayer(Long id) {
		iPlayerDAO.deleteById(id);
	}	
	
	// Delete all players and games
	@Override
	public void deletePlayers() {
		iPlayerDAO.deleteAll();
	}

}
