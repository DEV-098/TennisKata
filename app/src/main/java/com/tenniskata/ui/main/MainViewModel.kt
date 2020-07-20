package com.tenniskata.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tenniskata.GameState
import com.tenniskata.Player
import com.tenniskata.data.repository.TennisRepository

class MainViewModel(private val repository: TennisRepository) : ViewModel() {
    private val _state = MutableLiveData<GameState>()
    lateinit var player1: Player
    lateinit var player2: Player
    val state: LiveData<GameState>
        get() = _state

    fun startGame(player1Name: String, player2Name: String) {
        player1 = Player(player1Name)
        player2 = Player(player2Name)
        _state.postValue(repository.startGame(player1, player2))
    }

    fun increasePlayer1Point() {
        _state.postValue(repository.increasePoint(player1))
    }

    fun increasePlayer2Point() {
        _state.postValue(repository.increasePoint(player2))
    }
}