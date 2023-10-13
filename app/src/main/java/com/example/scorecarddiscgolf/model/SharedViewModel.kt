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

    init {
        // Initialize shared data (players list and current hole) here if needed.
    }

    // Add functions to update shared data, if required.
}
