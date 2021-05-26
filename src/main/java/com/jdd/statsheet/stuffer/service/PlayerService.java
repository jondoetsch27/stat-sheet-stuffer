package com.jdd.statsheet.stuffer.service;

import com.jdd.statsheet.stuffer.exception.PlayerNotFoundException;
import com.jdd.statsheet.stuffer.model.Player;
import com.jdd.statsheet.stuffer.model.PlayerData;
import com.jdd.statsheet.stuffer.model.TeamData;
import com.jdd.statsheet.stuffer.repository.PlayerRepository;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  @Value("${nfl.api.key}")
  private String nflApiKey;

  private final DataService dataService;
  private final LinkedHashMap<String, String> teamApiIds;
  private final PlayerRepository playerRepository;

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
      teamDataFuture = dataService.retrieveTeamData(playerName, teamApiId, nflApiKey);
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
      throw new PlayerNotFoundException();
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

    playerDataFuture = dataService.retrievePlayerData(player.getPlayerApiId(), nflApiKey);

    try {
      player.setPlayerData(playerDataFuture.get());
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }

    return playerRepository.insert(player);
  }

  public Player readPlayer(String playerId) {
    try {
      if (playerRepository.findById(playerId).isPresent()) {
        return playerRepository.findById(playerId).get();
      }
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
    throw new PlayerNotFoundException();
  }

  public Player updatePlayer(Player player) {
    Player newPlayer;
    if (playerRepository.findById(player.getPlayerId()).isPresent()) {
      newPlayer = playerRepository.findById(player.getPlayerId()).get();
    } else {
      throw new PlayerNotFoundException();
    }
    newPlayer.setPlayerName(player.getPlayerName());
    newPlayer.setPlayerNumber(player.getPlayerNumber());
    newPlayer.setPlayerId(player.getPlayerId());
    playerRepository.deleteById(player.getPlayerId());
    return playerRepository.insert(newPlayer);
  }

  public void deletePlayer(String playerId) {
    if (playerRepository.findById(playerId).isPresent()) {
      playerRepository.deleteById(playerId);
    } else {
      throw new PlayerNotFoundException();
    }
  }

}