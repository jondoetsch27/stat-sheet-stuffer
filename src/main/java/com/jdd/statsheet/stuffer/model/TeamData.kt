package com.jdd.statsheet.stuffer.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TeamData constructor(@JsonProperty("id") val teamId: String) {

    @JsonProperty("players")
    val teamPlayers: ArrayList<LinkedHashMap<String, kotlin.Any>>? = null

    @JsonProperty("alias")
    val teamName: String = ""

}