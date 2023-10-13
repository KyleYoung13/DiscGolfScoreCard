package com.example.scorecarddiscgolf.model

// SharedViewModel.kt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scorecarddiscgolf.data.Player

class SharedViewModel : ViewModel() {
    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>> get() = _players

    private val _currentHole = MutableLiveData<Int>()
    val currentHole: LiveData<Int> get() = _currentHole

    fun addPlayer(player: Player) {
        val currentPlayers = _players.value?.toMutableList() ?: mutableListOf()
        currentPlayers.add(player)
        _players.value = currentPlayers
    }

    fun updateCurrentHole(newHoleNumber: Int) {
        _currentHole.value = newHoleNumber
    }
}

