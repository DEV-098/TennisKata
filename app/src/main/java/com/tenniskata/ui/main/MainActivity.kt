package com.tenniskata.ui.main

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tenniskata.GameState
import com.tenniskata.Player
import com.tenniskata.Points
import com.tenniskata.R
import com.tenniskata.data.repository.TennisRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModelFactory = MainViewModelFactory(TennisRepositoryImpl())
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        handleGameNotStarted()
        setupObservers()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        btn_start.setOnClickListener {
            mainViewModel.startGame(
                tie_player_1_name.text.toString(),
                tie_player_2_name.text.toString()
            )
            hideKeyboard()
        }
        btn_score_player_1.setOnClickListener {
            mainViewModel.increasePlayer1Point()
        }
        btn_score_player_2.setOnClickListener {
            mainViewModel.increasePlayer2Point()
        }
    }

    private fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            btn_start?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
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
        tv_game_status.text = getString(
            R.string.game_status,
            getString(R.string.game_over, winner.name)
        )
        enableNameChanging(true)
        enableButtons(false)
    }

    private fun handleAdvantage(advantagePlayer: Player) {
        tv_game_status.text = getString(
            R.string.game_status,
            getString(R.string.advantage, advantagePlayer.name)
        )
        enableButtons(true)
    }

    private fun handleGameDeuce() {
        tv_game_status.text = getString(
            R.string.game_status,
            getString(R.string.deuces)
        )
        tie_player_1_score.setText(Points.FORTY.name)
        tie_player_2_score.setText(Points.FORTY.name)
        enableButtons(true)
    }

    private fun handleGameInProgress(state: GameState.InProgress) {
        tv_game_status.text = getString(
            R.string.game_status,
            getString(R.string.game_not_started)
        )
        tie_player_1_score.setText(state.player1Points.name)
        tie_player_2_score.setText(state.player2Points.name)
        enableNameChanging(false)
        enableButtons(true)
    }

    private fun enableNameChanging(isEnabled: Boolean) {
        tie_player_1_name.isEnabled = isEnabled
        tie_player_2_name.isEnabled = isEnabled
    }

    private fun handleGameNotStarted() {
        tv_game_status.text = getString(R.string.game_status, getString(R.string.game_not_started))
        enableButtons(false)
        enableNameChanging(true)
    }

    private fun enableButtons(isEnabled: Boolean) {
        btn_score_player_1.isEnabled = isEnabled
        btn_score_player_2.isEnabled = isEnabled
    }
}