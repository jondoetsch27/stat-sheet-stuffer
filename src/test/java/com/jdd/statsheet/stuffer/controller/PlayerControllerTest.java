package com.jdd.statsheet.stuffer.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import com.jdd.statsheet.stuffer.model.Player;
import com.jdd.statsheet.stuffer.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PlayerController.class)
public class PlayerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PlayerService playerService;

  PlayerController playerController;

  @BeforeEach
  public void setUp() {
    playerController = new PlayerController();
  }

  @Test
  public void readPlayerTest() throws Exception {
    given(playerService.readPlayer("Josh Allen 17")).willReturn(new Player("Josh Allen 17"));
    this.mockMvc.perform(
        get("/players/read/?playerFirstName=Josh&playerLastName=Allen&playerNumber=17"))
        .andExpect(status().isFound());
  }

  @Test
  public void addPlayerTest() throws Exception {
    given(playerService.createPlayer("Matthew Stafford 9"))
        .willReturn(new Player("Matthew Stafford 9"));
    this.mockMvc.perform(post("/players/add/?playerFirstName=Matthew&playerLastName=Stafford"))
        .andExpect(status().isCreated());
  }

  @Test
  public void updatePlayerTest() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new KotlinModule());
    given(playerService.updatePlayer(new Player("Kyler Murray 1")))
        .willReturn(new Player("Kyler Murray 1"));
    this.mockMvc.perform(put("/players/update/").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(new Player("Kyler Murray 1"))))
        .andExpect(status().isAccepted());
  }

  @Test
  public void deletePlayerTest() throws Exception {
    given(playerService.deletePlayer("Matthew Stafford 19"))
        .willReturn(new Player("Matthew Stafford 9"));
    this.mockMvc.perform(
        delete("/players/delete/?playerFirstName=Matthew&playerLastName=Stafford&playerNumber=9"))
        .andExpect(status().isGone());
  }

}
