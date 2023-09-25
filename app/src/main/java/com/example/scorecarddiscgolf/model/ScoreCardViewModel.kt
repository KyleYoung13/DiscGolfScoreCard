package com.example.scorecarddiscgolf.model

import androidx.lifecycle.ViewModel
import com.example.scorecarddiscgolf.data.Hole

class ScoreCardViewModel : ViewModel() {
    var holes = mutableListOf<Hole>()

    fun addHole(hole: Hole) {
        holes.add(hole)
    }
}