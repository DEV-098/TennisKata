package com.tenniskata.data.repository

import com.tenniskata.data.Player
import com.tenniskata.data.Points

class TennisRepositoryImpl : TennisRepository {
    private var player1Points: Int = Points.LOVE.position
    private var player2Points: Int = Points.LOVE.position
    private var player1: Player? = null
    private var player2: Player? = null

    override fun startGame(player1: Player, player2: Player) {
        this.player1 = player1
        this.player2 = player2
        player1Points = Points.LOVE.position
        player2Points = Points.LOVE.position
    }

    override fun increasePoint(player: Player) {
        when (player) {
            player1 -> player1Points++
            player2 -> player2Points++
        }
    }


    override fun getPoints(player: Player): Points {
        val playerPoints = if (player == player1) {
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