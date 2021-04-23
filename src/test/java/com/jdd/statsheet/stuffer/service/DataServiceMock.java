package com.jdd.statsheet.stuffer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import com.jdd.statsheet.stuffer.model.TeamData;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class DataServiceMock {

  private ObjectMapper objectMapper;

  public TeamData retrieveTeamData() throws Exception {

    TeamData teamData;
    objectMapper = new ObjectMapper().registerModule(new KotlinModule());


    JSONObject jsonObject1 = new JSONObject();
    jsonObject1.put("id", "33405046-04ee-4058-a950-d606f8c30852");
    jsonObject1.put("name", "Vikings");
    jsonObject1.put("market", "Minnesota");
    jsonObject1.put("alias", "MIN");
    jsonObject1.put("sr_id", "sr:competitor:4423");

    JSONObject jsonObject2 = new JSONObject();
    jsonObject2.put("id", "eea5f772-bd6b-4dc6-a7ef-118708f3a726");
    jsonObject2.put("name", "Vikings");
    jsonObject2.put("alias", "MIN");

    JSONObject jsonObject3 = new JSONObject();
    jsonObject3.put("id", "16");
    jsonObject3.put("origin", "gsis");

    JSONObject[] jsonObjects1 = new JSONObject[1];
    jsonObjects1[0] = jsonObject3;
    jsonObject2.put("references", jsonObjects1);
    jsonObject1.put("franchise", jsonObject2);

    JSONObject jsonObject4 = new JSONObject();
    jsonObject4.put("id", "f5ff00d4-1ed8-4918-bf73-13d66d510f98");
    jsonObject4.put("name", "U.S. Bank Stadium");
    jsonObject4.put("city", "Minneapolis");
    jsonObject4.put("state", "MN");
    jsonObject4.put("country", "USA");
    jsonObject4.put("zip", "55415");
    jsonObject4.put("address", "900 S 5th Street");
    jsonObject4.put("capacity", 66655);
    jsonObject4.put("surface", "artificial");
    jsonObject4.put("roof_type", "dome");
    jsonObject4.put("sr_id", "sr:venue:18423");

    JSONObject jsonObject5 = new JSONObject();
    jsonObject5.put("lat", "44.973686");
    jsonObject5.put("lng", "-93.257458");
    jsonObject4.put("location", jsonObject5);
    jsonObject1.put("venue", jsonObject4);

    JSONObject jsonObject6 = new JSONObject();
    jsonObject6.put("id", "6dc1933f-ca90-46e6-aaf7-9b95cc44306a");
    jsonObject6.put("name", "NFC North");
    jsonObject6.put("alias", "NFC_NORTH");
    jsonObject1.put("division", jsonObject6);

    JSONObject jsonObject7 = new JSONObject();
    jsonObject7.put("id", "b1808e5f-d40b-47c0-8af8-5175c0fdcd26");
    jsonObject7.put("name", "NFC");
    jsonObject7.put("alias", "NFC");
    jsonObject1.put("conference", jsonObject7);

    JSONObject jsonObject8 = new JSONObject();
    jsonObject8.put("id", "MIN");
    jsonObject8.put("origin", "gsis");

    JSONObject[] jsonObjects2 = new JSONObject[1];
    jsonObjects2[0] = jsonObject8;
    jsonObject1.put("references", jsonObjects2);

    JSONObject jsonObject9 = new JSONObject();
    jsonObject9.put("id", "3f6e65c9-51be-444e-9c3a-864bcedcd238");
    jsonObject9.put("full_name", "Adam Zimmer");
    jsonObject9.put("first_name", "Adam");
    jsonObject9.put("last_name", "Zimmer");
    jsonObject9.put("position", "Co-Defensive Coordinator");

    JSONObject jsonObject10 = new JSONObject();
    jsonObject10.put("id", "51e0d069-ed4c-40df-8191-7f03fa1cd260");
    jsonObject10.put("full_name", "Andre Patterson");
    jsonObject10.put("first_name", "Adam");
    jsonObject10.put("last_name", "Zimmer");
    jsonObject10.put("position", "Co-Defensive Coordinator");

    JSONObject[] jsonObjects3 = new JSONObject[2];
    jsonObjects3[0] = jsonObject9;
    jsonObjects3[1] = jsonObject10;
    jsonObject1.put("coaches", jsonObjects3);

    JSONObject jsonObject11 = new JSONObject();
    jsonObject11.put("id", "016a6d56-0b72-490e-8dae-41b5d3d2db63");
    jsonObject11.put("name", "Jaleel Johnson");
    jsonObject11.put("jersey", "94");
    jsonObject11.put("last_name", "Johnson");
    jsonObject11.put("first_name", "Jaleel");
    jsonObject11.put("abbr_name", "J.Johnson");
    jsonObject11.put("preferred_name", "Jaleel");
    jsonObject11.put("birth_date", "1994-07-12");
    jsonObject11.put("weight", 316.0);
    jsonObject11.put("height", 75);
    jsonObject11.put("position", "DT");
    jsonObject11.put("birth_place", "Lombard, IL, USA");
    jsonObject11.put("high_school", "Montini Catholic (IL)");
    jsonObject11.put("college", "Iowa");
    jsonObject11.put("college_conf", "Big Ten Conference");
    jsonObject11.put("rookie_year", 2017);
    jsonObject11.put("status", "U01");
    jsonObject11.put("sr_id", "sr:player:1130133");

    JSONObject jsonObject12 = new JSONObject();
    jsonObject12.put("year", 2017);
    jsonObject12.put("round", 4);
    jsonObject12.put("number", 109);

    JSONObject jsonObject13 = new JSONObject();
    jsonObject13.put("id", "33405046-04ee-4058-a950-d606f8c30852");
    jsonObject13.put("name", "Vikings");
    jsonObject13.put("market", "Minnesota");
    jsonObject13.put("alias", "MIN");
    jsonObject13.put("sr_id", "sr:competitor:4423");
    jsonObject12.put("team", jsonObject13);
    jsonObject11.put("draft", jsonObject12);

    JSONObject jsonObject14 = new JSONObject();
    jsonObject14.put("id", "00-0033940");
    jsonObject14.put("origin", "gsis");

    JSONObject jsonObject15 = new JSONObject();
    jsonObject15.put("id", "44922");
    jsonObject15.put("origin", "nflx");

    JSONObject[] jsonObjects4 = new JSONObject[2];
    jsonObjects4[0] = jsonObject14;
    jsonObjects4[1] = jsonObject15;
    jsonObject11.put("references", jsonObjects4);

    JSONObject jsonObject16 = new JSONObject();
    jsonObject16.put("id", "8960d61e-433b-41ea-a7ad-4e76be87b582");
    jsonObject16.put("name", "Dalvin Cook");
    jsonObject16.put("jersey", "33");
    jsonObject16.put("last_name", "Cook");
    jsonObject16.put("first_name", "Dalvin");
    jsonObject16.put("abbr_name", "D.Cook");
    jsonObject16.put("preferred_name", "Dalvin");
    jsonObject16.put("birth_date", "1995-08-10");
    jsonObject16.put("weight", 210.0);
    jsonObject16.put("height", 70);
    jsonObject16.put("position", "RB");
    jsonObject16.put("birth_place", "Miami, FL, USA");
    jsonObject16.put("high_school", "Miami Central (FL)");
    jsonObject16.put("college", "Florida State");
    jsonObject16.put("college_conf", "Atlantic Coast Conference");
    jsonObject16.put("rookie_year", 2017);
    jsonObject16.put("status", "A01");
    jsonObject16.put("sr_id", "sr:player:1129743");

    JSONObject jsonObject17 = new JSONObject();
    jsonObject17.put("year", 2017);
    jsonObject17.put("round", 2);
    jsonObject17.put("number", 41);

    JSONObject jsonObject18 = new JSONObject();
    jsonObject18.put("id", "33405046-04ee-4058-a950-d606f8c30852");
    jsonObject18.put("name", "Vikings");
    jsonObject18.put("market", "Minnesota");
    jsonObject18.put("alias", "MIN");
    jsonObject18.put("sr_id", "sr:competitor:4423");
    jsonObject17.put("team", jsonObject18);
    jsonObject16.put("draft", jsonObject17);

    JSONObject jsonObject19 = new JSONObject();
    jsonObject19.put("id", "00-0033893");
    jsonObject19.put("origin", "gsis");

    JSONObject jsonObject20 = new JSONObject();
    jsonObject20.put("id", "44853");
    jsonObject20.put("origin", "nflx");

    JSONObject[] jsonObjects5 = new JSONObject[2];
    jsonObjects5[0] = jsonObject19;
    jsonObjects5[1] = jsonObject20;
    jsonObject16.put("references", jsonObjects5);

    JSONObject[] jsonObjects6 = new JSONObject[2];
    jsonObjects6[0] = jsonObject11;
    jsonObjects6[1] = jsonObject16;
    jsonObject1.put("players", jsonObjects6);
    jsonObject1.put(
        "_comment", "Generation started @ 2021-04-15 23:09:01 UTC ended @ 2021-04-15 23:09:01 UTC");

    teamData = objectMapper.readValue(jsonObject1.toString(), TeamData.class);

    return teamData;
  }
}
