package com.example.ITAcademy.DiceRollerMongoDB.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ITAcademy.DiceRollerMongoDB.dto.Game;
import com.example.ITAcademy.DiceRollerMongoDB.dto.Player;

@Repository
public interface IGameDAO extends MongoRepository <Game, Long> { //ver por que es serializable y no Integer (creo q tiene q ver con la PK)

	// List all games from player
	List<Game> findAllByPlayer(Player player);

}