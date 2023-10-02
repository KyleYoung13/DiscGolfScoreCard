package com.example.scorecarddiscgolf.model

import androidx.lifecycle.ViewModel
import com.example.scorecarddiscgolf.data.Hole
import com.example.scorecarddiscgolf.data.Player

class ScoreCardViewModel : ViewModel() {
    var holes = mutableListOf<Hole>()
    var players = mutableListOf<Player>()

    fun addHole(hole: Hole) {
        holes.add(hole)
    }

    fun addPlayer(player: Player){
        players.add(player)
    }
}