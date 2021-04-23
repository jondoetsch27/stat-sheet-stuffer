package com.jdd.statsheet.stuffer.controller;

import com.jdd.statsheet.stuffer.model.Player;
import com.jdd.statsheet.stuffer.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

  @Autowired PlayerService playerService;

  Logger logger = LoggerFactory.getLogger(PlayerController.class);

  @PostMapping(path = "/players/add/")
  public ResponseEntity<Player> addPlayer(@RequestParam String playerFirstName, @RequestParam String playerLastName) {
    String playerName = playerFirstName + " " + playerLastName;
    Player player = null;
    ResponseEntity<Player> playerResponseEntity = null;
    logger.debug("Received POST request at /players/add for Player: " + playerName);

    try {
      player = playerService.createPlayer(playerName);
      playerResponseEntity = new ResponseEntity<>(player, HttpStatus.CREATED);
    } catch (RuntimeException runtimeException) {
      logger.error("Player Creation Failed" + runtimeException);
      playerResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return playerResponseEntity;
  }

}
