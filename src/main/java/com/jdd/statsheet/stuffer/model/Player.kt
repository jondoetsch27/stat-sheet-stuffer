package com.jdd.statsheet.stuffer.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Players")
class Player constructor(@Id var playerId: String) {

    var playerName: String = ""
    var playerNumber: String = ""
    var playerTeam: String = ""
    var playerApiId: String = ""
    var playerData: PlayerData? = null

}