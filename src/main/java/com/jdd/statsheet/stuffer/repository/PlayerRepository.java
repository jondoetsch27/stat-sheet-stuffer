package com.jdd.statsheet.stuffer.repository;

import com.jdd.statsheet.stuffer.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {

}
