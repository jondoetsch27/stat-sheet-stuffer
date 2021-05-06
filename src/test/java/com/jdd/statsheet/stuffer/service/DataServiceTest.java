package com.jdd.statsheet.stuffer.service;

import static com.jdd.statsheet.stuffer.TestConstants.API_KEY_URL;
import static com.jdd.statsheet.stuffer.TestConstants.MATTHEW_STAFFORD_API_ID;
import static com.jdd.statsheet.stuffer.TestConstants.NAME_KEY;
import static com.jdd.statsheet.stuffer.TestConstants.PLAYERS_URL;
import static com.jdd.statsheet.stuffer.TestConstants.PROFILE_URL;
import static com.jdd.statsheet.stuffer.TestConstants.ROSTER_URL;
import static com.jdd.statsheet.stuffer.TestConstants.SPORTS_RADAR_URL;
import static com.jdd.statsheet.stuffer.TestConstants.TEAMS_URL;
import static com.jdd.statsheet.stuffer.TestConstants.teamApiIdHashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import com.jdd.statsheet.stuffer.model.PlayerData;
import com.jdd.statsheet.stuffer.model.TeamData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.Future;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@EnableConfigurationProperties
public class DataServiceTest {

  private static DataService dataService;

  @Value("${nfl.api.key}")
  private String nflApiKey;

  @BeforeEach
  public void initalize() {
    dataService = new DataService();
  }

  @Test
  public void retrieveTeamDataTest() throws Exception {
    TeamData teamData =
        dataService.retrieveTeamData("Dalvin Cook", "33405046-04ee-4058-a950-d606f8c30852", nflApiKey).get();
    System.out.println(teamData.getTeamName() + " " + teamData.getTeamId());
  }

  @Test
  public void retrieveAllTeamDataTest() throws Exception {
    Future<TeamData> teamDataFuture = null;
    TeamData teamData = null;
    for (String teamName : teamApiIdHashMap.keySet()) {
      String teamApiId = teamApiIdHashMap.get(teamName);
      teamDataFuture = dataService.retrieveTeamData("Dalvin Cook", teamApiId, nflApiKey);
      if (teamDataFuture != null) {
        teamData = teamDataFuture.get();
        break;
      }
    }
    assertNotNull(teamData);
    assertEquals(teamData.getTeamName(), "MIN");
  }

  @Test
  public void callTeamApi() throws Exception {
    String teamId = "33405046-04ee-4058-a950-d606f8c30852";
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
      teamData = objectMapper.readValue(teamDataJson.toString(), TeamData.class);
      ArrayList arrayList = teamData.getTeamPlayers();
      for (LinkedHashMap<String, Object> linkedHashMap : teamData.getTeamPlayers()) {
        if (linkedHashMap.get(NAME_KEY).equals("Dalvin Cook")) {
          System.out.println(linkedHashMap.get(NAME_KEY).getClass());
        }
      }
    } else {
      System.out.println("GET request failed");
    }
  }

  @Test
  public void retrievePlayerData() throws Exception {

    PlayerData playerData;
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity httpEntity = new HttpEntity<Object>(httpHeaders);
    LinkedHashMap playerDataMap =
        restTemplate
            .exchange(
                (SPORTS_RADAR_URL + PLAYERS_URL + MATTHEW_STAFFORD_API_ID + PROFILE_URL
                    + API_KEY_URL + nflApiKey),
                HttpMethod.GET,
                httpEntity,
                LinkedHashMap.class)
            .getBody();
    if (playerDataMap != null) {
      JSONObject playerDataJson = new JSONObject(playerDataMap);
      ObjectMapper objectMapper = new ObjectMapper().registerModule(new KotlinModule());
      playerData = objectMapper.readValue(playerDataJson.toString(), PlayerData.class);
      System.out.println(playerData.getPlayerApiId());
    } else {
      System.out.println("GET request failed");
    }

  }
}
