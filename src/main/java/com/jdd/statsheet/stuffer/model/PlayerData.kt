package com.jdd.statsheet.stuffer.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlayerData constructor(@JsonProperty("name") val playerId: String) {

    @JsonProperty("id")
    val playerApiId: String = ""

    @JsonProperty(value = "jersey")
    val playerNumber: String = ""

    @JsonProperty(value = "position")
    val playerPosition: String = ""

    @JsonProperty(value = "height")
    val playerHeight: Int = 0

    @JsonProperty(value = "weight")
    val playerWeight: Double = 0.0

    @JsonProperty(value = "college")
    val playerCollege: String = ""

    @JsonProperty(value = "college_conf")
    val playerCollegeConference: String = ""

    @JsonProperty(value = "rookie_year")
    val playerRookieYear: Int = 0

    @JsonProperty(value = "team")
    val playerTeam: LinkedHashMap<String, String>? = null

    @JsonProperty(value = "draft")
    val playerDraft: LinkedHashMap<String, kotlin.Any>? = null

    @JsonProperty(value = "seasons")
    val playerSeasons: ArrayList<LinkedHashMap<String, kotlin.Any>>? = null
}