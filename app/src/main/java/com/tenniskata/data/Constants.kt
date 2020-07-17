package com.tenniskata.data

sealed class Player(name: String) {
    data class Player1(val name: String) : Player(name)
    data class Player2(val name: String) : Player(name)
}

enum class Points(val text: String, val points: Int, val position: Int) {
    LOVE("Love", 0, 0),
    FIFTEEN("Fifteen", 15, 1),
    THIRTY("Thirty", 30, 2),
    FORTY("Forty", 40, 3)
}

sealed class GameState {
    data class InProgress(val player1Points: Points, val player2Points: Points) : GameState()
    data class PlayerWins(val player: Player) : GameState()
}