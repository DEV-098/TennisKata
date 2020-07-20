package com.tenniskata.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tenniskata.GameState
import com.tenniskata.Player
import com.tenniskata.R
import com.tenniskata.data.repository.TennisRepositoryImpl

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModelFactory = MainViewModelFactory(TennisRepositoryImpl())
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        setupObservers()
    }

    private fun setupObservers() {
        mainViewModel.state.observe(this, Observer { state ->
            when (state) {
                is GameState.GameNotStarted -> handleGameNotStarted()
                is GameState.InProgress -> handleGameInProgress(state)
                is GameState.Deuce -> handleGameDeuce()
                is GameState.Advantage -> handleAdvantage(state.player)
                is GameState.PlayerWins -> handlePlayerWins(state.player)
            }
        })
    }

    private fun handlePlayerWins(winner: Player) {

    }

    private fun handleAdvantage(advantagePlayer: Player) {

    }

    private fun handleGameDeuce() {

    }

    private fun handleGameInProgress(state: GameState.InProgress) {

    }

    private fun handleGameNotStarted() {

    }
}