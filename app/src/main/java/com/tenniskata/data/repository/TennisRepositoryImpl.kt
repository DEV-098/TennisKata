package com.tenniskata.data.repository

import com.tenniskata.data.Player
import com.tenniskata.data.Points

class TennisRepositoryImpl : TennisRepository {
    var player1Points: Int = 0
    var player2Points: Int = 0
    override fun increasePoint(player: Player) {
        when (player) {
            Player.PLAYER_1 -> player1Points++
            Player.PLAYER_2 -> player2Points++
        }
    }

    override fun getPoints(player: Player): Points {
        val playerPoints = if (player == Player.PLAYER_1) {
            player1Points
        } else {
            player2Points
        }
        return when (playerPoints) {
            Points.LOVE.position -> Points.LOVE
            Points.FIFTEEN.position -> Points.FIFTEEN
            Points.THIRTY.position -> Points.THIRTY
            Points.FORTY.position -> Points.FORTY
            else -> throw IllegalStateException("Invalid Points")
        }
    }
}