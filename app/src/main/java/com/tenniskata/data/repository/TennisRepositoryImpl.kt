package com.tenniskata.data.repository

import com.tenniskata.data.GameState
import com.tenniskata.data.Player
import com.tenniskata.data.Points

class TennisRepositoryImpl : TennisRepository {
    private var player1Points: Int = Points.LOVE.numberOfBallWins
    private var player2Points: Int = Points.LOVE.numberOfBallWins
    private lateinit var player1: Player
    private lateinit var player2: Player

    override fun startGame(player1: Player, player2: Player) {
        this.player1 = player1
        this.player2 = player2
        player1Points = Points.LOVE.numberOfBallWins
        player2Points = Points.LOVE.numberOfBallWins
    }

    override fun increasePoint(player: Player): GameState {
        val state = getState()
        if (state is GameState.Advantage) {
            if (player != state.player) {
                player1Points = Points.FORTY.numberOfBallWins
                player2Points = Points.FORTY.numberOfBallWins
            } else {
                return GameState.PlayerWins(player)
            }
        } else {
            when (player) {
                player1 -> player1Points++
                player2 -> player2Points++
            }
        }
        return getState()
    }

    private fun getState(): GameState {
        return when {
            isAnyPlayerAbsent() -> GameState.GameNotStarted
            isAnyOneWon() -> GameState.PlayerWins(getWinner())
            isAdvantage() -> GameState.Advantage(getHigherPointsPlayer())
            isDeuce() -> GameState.Deuce
            isInProgress() -> GameState.InProgress(getPoints(player1), getPoints(player2))

            else -> throw IllegalStateException("This state should not be reached")

        }
    }

    private fun isAnyOneWon(): Boolean {
        return higherPoints() >= Points.ADVANTAGE.numberOfBallWins && pointDiffs() > 1
    }

    private fun pointDiffs(): Int {
        return kotlin.math.abs(player1Points - player2Points)
    }

    private fun higherPoints(): Int {
        return getPoints(getHigherPointsPlayer()).numberOfBallWins
    }

    private fun getWinner(): Player {
        return if (player1Points == Points.ADVANTAGE.numberOfBallWins && player1Points - player2Points > 1) {
            player1
        } else if (player2Points == Points.ADVANTAGE.numberOfBallWins && player2Points - player1Points > 1) {
            player2
        } else {
            throw IllegalStateException("Game is in progress")
        }
    }

    private fun getHigherPointsPlayer(): Player {
        return if (player1Points > player2Points) {
            player1
        } else {
            player2
        }
    }

    private fun isAdvantage(): Boolean {
        return (player1Points == Points.ADVANTAGE.numberOfBallWins
                && player2Points == Points.FORTY.numberOfBallWins) ||
                (player2Points == Points.ADVANTAGE.numberOfBallWins
                        && player1Points == Points.FORTY.numberOfBallWins)
    }

    private fun isDeuce(): Boolean {
        return player1Points == Points.FORTY.numberOfBallWins && player2Points == Points.FORTY.numberOfBallWins
    }

    private fun isInProgress(): Boolean {
        return player1Points < Points.ADVANTAGE.numberOfBallWins && player2Points < Points.ADVANTAGE.numberOfBallWins
    }

    private fun isAnyPlayerAbsent(): Boolean {
        return !::player1.isInitialized || !::player2.isInitialized
    }


    override fun getPoints(player: Player): Points {
        val playerPoints = if (player == player1) {
            player1Points
        } else {
            player2Points
        }
        return when (playerPoints) {
            Points.LOVE.numberOfBallWins -> Points.LOVE
            Points.FIFTEEN.numberOfBallWins -> Points.FIFTEEN
            Points.THIRTY.numberOfBallWins -> Points.THIRTY
            Points.FORTY.numberOfBallWins -> Points.FORTY
            Points.ADVANTAGE.numberOfBallWins -> Points.ADVANTAGE
            else -> throw IllegalStateException("Invalid Points")
        }
    }
}