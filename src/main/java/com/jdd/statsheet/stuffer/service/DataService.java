package com.jdd.statsheet.stuffer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import com.jdd.statsheet.stuffer.model.PlayerData;
import com.jdd.statsheet.stuffer.model.TeamData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.Future;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataService {

  private static final String SPORTS_RADAR_URL = "https://api.sportradar.us/nfl/official/trial";
  private static final String TEAMS_URL = "/v6/en/teams/";
  private static final String ROSTER_URL = "/full_roster.json";
  private static final String PLAYERS_URL = "/v6/en/players/";
  private static final String PROFILE_URL = "/profile.json";
  private static final String API_KEY_URL = "?api_key=";
  private static final String PLAYERS_KEY = "players";
  private static final String NAME_KEY = "name";
  private static final String ID_KEY = "id";
  private static final String NUMBER_KEY = "jersey";

  private final RestTemplate restTemplate = new RestTemplate();

  //  @Value("${nfl.api.key}")
  private String nflApiKey = "g9dssek87drzge36dt5edn8d";

  public DataService() {}

  public DataService(String nflApiKey) {
    this.nflApiKey = nflApiKey;
  }

  @Async
  public Future<TeamData> retrieveTeamData(String playerName, String teamId) {

    try {
      Thread.sleep(1000);
    } catch (InterruptedException interruptedException) {
      throw new RuntimeException(interruptedException);
    }

    TeamData teamData;
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity httpEntity = new HttpEntity<Object>(httpHeaders);

    LinkedHashMap teamDataMap =
        restTemplate
            .exchange(
                (SPORTS_RADAR_URL + TEAMS_URL + teamId + ROSTER_URL + API_KEY_URL + nflApiKey),
                HttpMethod.GET,
                httpEntity,
                LinkedHashMap.class)
            .getBody();

    if (teamDataMap != null) {

      JSONObject teamDataJson = new JSONObject(teamDataMap);
      ObjectMapper objectMapper = new ObjectMapper().registerModule(new KotlinModule());

      try {
        teamData = objectMapper.readValue(teamDataJson.toString(), TeamData.class);
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      }

      ArrayList arrayList = teamData.getTeamPlayers();

      for (LinkedHashMap<String, Object> linkedHashMap : teamData.getTeamPlayers()) {
        if (linkedHashMap.get(NAME_KEY).equals(playerName)) {
          return new AsyncResult<>(teamData);
        }
      }
      return null;

    } else {
      throw new RuntimeException("Null GET Response");
    }
  }

  @Async public Future<PlayerData> retrievePlayerData(String playerApiId) {

    try {
      Thread.sleep(1000);
    } catch (InterruptedException interruptedException) {
      throw new RuntimeException(interruptedException);
    }

    PlayerData playerData;
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity httpEntity = new HttpEntity<Object>(httpHeaders);
    LinkedHashMap playerDataMap =
        restTemplate
            .exchange(
                (SPORTS_RADAR_URL + PLAYERS_URL + playerApiId + PROFILE_URL + API_KEY_URL + nflApiKey),
                HttpMethod.GET,
                httpEntity,
                LinkedHashMap.class)
            .getBody();
    if (playerDataMap != null) {
      JSONObject playerDataJson = new JSONObject(playerDataMap);
      ObjectMapper objectMapper = new ObjectMapper().registerModule(new KotlinModule());
      try {
        playerData = objectMapper.readValue(playerDataJson.toString(), PlayerData.class);
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      }
      return new AsyncResult<PlayerData>(playerData);
    }
    throw new RuntimeException("Null GET Response");
  }

}