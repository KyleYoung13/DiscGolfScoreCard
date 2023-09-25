package com.example.scorecarddiscgolf.model

import androidx.lifecycle.ViewModel



class HoleViewModel(private val playerName: String, private var holeNumber: Int,
                    private var score: String) : ViewModel() {

    private var totalScore: Int = 0


    fun updateScore(newScore: String) {
        score = newScore
    }

    fun getScore(): String {
        return score
    }

    fun setScore(newScore: String){
        score = newScore
    }

    fun getTotalScore(): String{
        return score
    }

    fun getPlayerName(): String {
        return playerName
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
