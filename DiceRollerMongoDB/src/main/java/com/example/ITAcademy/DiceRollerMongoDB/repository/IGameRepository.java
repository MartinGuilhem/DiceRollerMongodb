package com.example.ITAcademy.DiceRollerMongoDB.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ITAcademy.DiceRollerMongoDB.dto.Game;

@Repository
public interface IGameRepository extends MongoRepository <Game, Long> { //ver por que es serializable y no Integer (creo q tiene q ver con la PK)

	// List all games from player
	List<Game> findAllById(Long player_id);

}