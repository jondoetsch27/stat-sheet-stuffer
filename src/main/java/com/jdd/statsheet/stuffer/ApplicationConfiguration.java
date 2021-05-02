package com.jdd.statsheet.stuffer;

import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableConfigurationProperties
@Configuration
@EnableAsync
public class ApplicationConfiguration {

  @Value("${nfl.api.key}")
  public String nflApiKey;

  @Bean
  public static final LinkedHashMap<String, String> teamApiIds() {
    LinkedHashMap<String, String> teamApiIdHashMap = new LinkedHashMap<>();
    teamApiIdHashMap.put("ATL", "e6aa13a4-0055-48a9-bc41-be28dc106929");
    teamApiIdHashMap.put("ARI", "de760528-1dc0-416a-a978-b510d20692ff");
    teamApiIdHashMap.put("BAL", "ebd87119-b331-4469-9ea6-d51fe3ce2f1c");
    teamApiIdHashMap.put("BUF", "768c92aa-75ff-4a43-bcc0-f2798c2e1724");
    teamApiIdHashMap.put("CAR", "f14bf5cc-9a82-4a38-bc15-d39f75ed5314");
    teamApiIdHashMap.put("CHI", "7b112545-38e6-483c-a55c-96cf6ee49cb8");
    teamApiIdHashMap.put("CIN", "ad4ae08f-d808-42d5-a1e6-e9bc4e34d123");
    teamApiIdHashMap.put("CLE", "d5a2eb42-8065-4174-ab79-0a6fa820e35e");
    teamApiIdHashMap.put("DAL", "e627eec7-bbae-4fa4-8e73-8e1d6bc5c060");
    teamApiIdHashMap.put("DEN", "ce92bd47-93d5-4fe9-ada4-0fc681e6caa0");
    teamApiIdHashMap.put("DET", "c5a59daa-53a7-4de0-851f-fb12be893e9e");
    teamApiIdHashMap.put("GB", "a20471b4-a8d9-40c7-95ad-90cc30e46932");
    teamApiIdHashMap.put("HOU", "82d2d380-3834-4938-835f-aec541e5ece7");
    teamApiIdHashMap.put("IND", "82cf9565-6eb9-4f01-bdbd-5aa0d472fcd9");
    teamApiIdHashMap.put("JAC", "f7ddd7fa-0bae-4f90-bc8e-669e4d6cf2de");
    teamApiIdHashMap.put("KC", "6680d28d-d4d2-49f6-aace-5292d3ec02c2");
    teamApiIdHashMap.put("LAC", "1f6dcffb-9823-43cd-9ff4-e7a8466749b5");
    teamApiIdHashMap.put("LAR", "2eff2a03-54d4-46ba-890e-2bc3925548f3");
    teamApiIdHashMap.put("LV", "7d4fcc64-9cb5-4d1b-8e75-8a906d1e1576");
    teamApiIdHashMap.put("MIA", "4809ecb0-abd3-451d-9c4a-92a90b83ca06");
    teamApiIdHashMap.put("MIN", "33405046-04ee-4058-a950-d606f8c30852");
    teamApiIdHashMap.put("NE", "97354895-8c77-4fd4-a860-32e62ea7382a");
    teamApiIdHashMap.put("NO", "0d855753-ea21-4953-89f9-0e20aff9eb73");
    teamApiIdHashMap.put("NYG", "04aa1c9d-66da-489d-b16a-1dee3f2eec4d");
    teamApiIdHashMap.put("NYJ", "5fee86ae-74ab-4bdd-8416-42a9dd9964f3");
    teamApiIdHashMap.put("PHI", "386bdbf9-9eea-4869-bb9a-274b0bc66e80");
    teamApiIdHashMap.put("PIT", "cb2f9f1f-ac67-424e-9e72-1475cb0ed398");
    teamApiIdHashMap.put("SEA", "3d08af9e-c767-4f88-a7dc-b920c6d2b4a8");
    teamApiIdHashMap.put("SF", "f0e724b0-4cbf-495a-be47-013907608da9");
    teamApiIdHashMap.put("TB", "4254d319-1bc7-4f81-b4ab-b5e6f3402b69");
    teamApiIdHashMap.put("TEN", "d26a1ca5-722d-4274-8f97-c92e49c96315");
    teamApiIdHashMap.put("WAS", "22052ff7-c065-42ee-bc8f-c4691c50e624");
    return teamApiIdHashMap;
  }

  @Bean
  public String getNflApiKey() {
    return nflApiKey;
  }

}