package com.jdd.statsheet.stuffer.service;

import com.jdd.statsheet.stuffer.model.Player;
import com.jdd.statsheet.stuffer.model.PlayerData;
import com.jdd.statsheet.stuffer.model.TeamData;
import com.jdd.statsheet.stuffer.repository.PlayerRepository;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  private DataService dataService;
  private LinkedHashMap<String, String> teamApiIds;
  private PlayerRepository playerRepository;

  private static final String NUMBER_KEY = "jersey";
  private static final String NAME_KEY = "name";
  private static final String ID_KEY = "id";

  public PlayerService(
      DataService dataService,
      LinkedHashMap<String, String> teamApiIds,
      PlayerRepository playerRepository) {
    this.dataService = dataService;
    this.teamApiIds = teamApiIds;
    this.playerRepository = playerRepository;
  }

  public Player createPlayer(String playerName) {
    Player player = new Player(playerName);
    Future<PlayerData> playerDataFuture = null;
    Future<TeamData> teamDataFuture = null;
    TeamData teamData = null;
    String teamApiId;

    for (String teamName : teamApiIds.keySet()) {
      teamApiId = teamApiIds.get(teamName);
      teamDataFuture = dataService.retrieveTeamData(playerName, teamApiId);
      if (teamDataFuture != null) {
        try {
          teamData = teamDataFuture.get();
        } catch (Exception exception) {
          throw new RuntimeException(exception);
        }
        if (teamData != null) {
          break;
        }
      }
    }

    if (teamData == null) {
      throw new RuntimeException("Player Not Found");
    }

    for (LinkedHashMap playerMap : Objects.requireNonNull(teamData.getTeamPlayers())) {
      if (playerMap.get(NAME_KEY).equals(playerName)) {
        player.setPlayerApiId((String) playerMap.get(ID_KEY));
        player.setPlayerNumber((String) playerMap.get(NUMBER_KEY));
        player.setPlayerTeam(teamData.getTeamName());
        player.setPlayerName(playerName);
        player.setPlayerId(player.getPlayerName() + " " + player.getPlayerNumber());
      }
    }

    playerDataFuture = dataService.retrievePlayerData(player.getPlayerApiId());

    try {
      player.setPlayerData(playerDataFuture.get());
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }

    return playerRepository.insert(player);
  }

  public Player readPlayer(String playerId) {
    try {
      return playerRepository.findById(playerId).get();
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }

}