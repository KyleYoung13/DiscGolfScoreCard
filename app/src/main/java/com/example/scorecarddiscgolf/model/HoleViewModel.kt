package com.example.scorecarddiscgolf.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scorecarddiscgolf.adapter.PlayersAdapter
import com.example.scorecarddiscgolf.data.Player



class HoleViewModel: ViewModel() {

    private var totalScore: Int = 0
    private var score: String = ""
    private var holeNumber: Int = 0
    private val playersList = mutableListOf<Player>()
    private val _playersLiveData = MutableLiveData<List<Player>>()
    val playersLiveData: LiveData<List<Player>> get() = _playersLiveData
    val playersAdapter: PlayersAdapter = PlayersAdapter(playersLiveData)

    init {
        _playersLiveData.value = playersList
    }


    fun updateScore(newScore: String) {
        score = newScore
    }

    fun getScore(): String {
        return score
    }

    fun setScore(newScore: String){
        score = newScore
    }


    fun getHoleNumber(): Int {
        return holeNumber
    }
    fun setHoleNumber(holeNumber: Int) {
        if (holeNumber in 1..36) {
            this.holeNumber = holeNumber
        } else {
            throw IllegalArgumentException("The hole number must be between 1 and 36.")
        }
    }

    fun updateTotalScore(){
        val scoreInt = score.toIntOrNull() ?: 0
        totalScore += scoreInt
    }

}
