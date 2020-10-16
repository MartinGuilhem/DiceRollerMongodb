package com.example.ITAcademy.DiceRollerMongoDB.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ITAcademy.DiceRollerMongoDB.dto.Player;

@Repository
public interface IPlayerDAO extends MongoRepository<Player, Long> { 
	
//	Player findById(Long id);
	
}