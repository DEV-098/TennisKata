package com.tenniskata

data class Player(val name: String)

enum class Points(val text: String, val points: Int, val numberOfBallWins: Int) {
    LOVE("Love", 0, 0),
    FIFTEEN("Fifteen", 15, 1),
    THIRTY("Thirty", 30, 2),
    FORTY("Forty", 40, 3),
    ADVANTAGE("Advantage",40, 4)
}

sealed class GameState {
    object GameNotStarted: GameState()
    object Deuce: GameState()
    data class InProgress(val player1Points: Points, val player2Points: Points) : GameState()
    data class PlayerWins(val player: Player) : GameState()
    data class Advantage(val player: Player): GameState()
}