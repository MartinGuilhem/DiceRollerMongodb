package com.example.ITAcademy.DiceRollerMongoDB.dao;


//import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ITAcademy.DiceRollerMongoDB.dto.Player;

@Repository
public interface IPlayerDAO extends MongoRepository<Player, Long> { //ver por que es serializable y no Integer (creo q tiene q ver con la PK)
	
//	Player findById(Long id);
	
}