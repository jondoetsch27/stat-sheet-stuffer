package com.jdd.statsheet.stuffer.controller;

import com.jdd.statsheet.stuffer.exception.PlayerNotFoundException;
import com.jdd.statsheet.stuffer.model.Player;
import com.jdd.statsheet.stuffer.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

  @Autowired
  PlayerService playerService;

  Logger logger = LoggerFactory.getLogger(PlayerController.class);

  @GetMapping(path = "/players/read/")
  public ResponseEntity<Player> readPlayer(@RequestParam String playerFirstName,
      @RequestParam String playerLastName, @RequestParam String playerNumber) {
    String playerName = playerFirstName + " " + playerLastName;
    String playerId = playerName + " " + playerNumber;
    Player player = null;
    ResponseEntity<Player> playerResponseEntity = null;
    logger.debug("Received GET request at /players/read for Player: " + playerName);

    try {
      player = playerService.readPlayer(playerId);
      playerResponseEntity = new ResponseEntity<>(player, HttpStatus.FOUND);
      logger.debug("Player Read Successful");
    } catch (PlayerNotFoundException playerNotFoundException) {
      logger.error("Player Not Found");
      playerResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      logger.error("Player Read Failed");
      playerResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return playerResponseEntity;
  }

  @PostMapping(path = "/players/add/")
  public ResponseEntity<Player> addPlayer(@RequestParam String playerFirstName,
      @RequestParam String playerLastName) {
    String playerName = playerFirstName + " " + playerLastName;
    Player player = null;
    ResponseEntity<Player> playerResponseEntity = null;
    logger.debug("Received POST request at /players/add for Player: " + playerName);

    try {
      player = playerService.createPlayer(playerName);
      playerResponseEntity = new ResponseEntity<>(player, HttpStatus.CREATED);
      logger.debug("Player Creation Successful");
    } catch (PlayerNotFoundException playerNotFoundException) {
      logger.error("Player Not Found");
      playerResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      logger.error("Player Creation Failed" + exception);
      playerResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return playerResponseEntity;
  }

  @PutMapping(path = "/players/update")
  public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
    ResponseEntity<Player> playerResponseEntity = null;
    Player newPlayer = null;
    logger.debug("Received PUT request at /players/update for Player: " + player.getPlayerName());

    try {
      newPlayer = playerService.updatePlayer(player);
      playerResponseEntity = new ResponseEntity<>(newPlayer, HttpStatus.ACCEPTED);
      logger.debug("Player Update Successful");
    } catch (PlayerNotFoundException playerNotFoundException) {
      logger.error("Player Not Found");
      playerResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      logger.error("Player Update Failed" + exception);
      playerResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return playerResponseEntity;
  }

  @DeleteMapping(path = "/players/delete")
  public ResponseEntity<Player> deletePlayer(@RequestParam String playerFirstName,
      @RequestParam String playerLastName, @RequestParam String playerNumber) {
    String playerName = playerFirstName + playerLastName;
    String playerId = playerName + playerNumber;
    Player deletedPlayer;
    ResponseEntity<Player> playerResponseEntity = null;
    logger.debug("Received DELETE request  at /players/delete for Player: " + playerName);

    try {
      deletedPlayer = playerService.deletePlayer(playerId);
      playerResponseEntity = new ResponseEntity<>(deletedPlayer, HttpStatus.GONE);
      logger.debug("Player Deletion Successful");
    } catch (PlayerNotFoundException playerNotFoundException) {
      logger.error("Player Not Found");
      playerResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception exception) {
      logger.error("Player Deletion Failed");
      playerResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return playerResponseEntity;
  }

}
