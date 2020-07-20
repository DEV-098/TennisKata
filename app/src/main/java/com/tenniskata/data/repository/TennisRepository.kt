package com.tenniskata.data.repository

import com.tenniskata.GameState
import com.tenniskata.Player
import com.tenniskata.Points

interface TennisRepository {
    fun startGame(player1: Player, player2: Player)
    fun increasePoint(player: Player): GameState
    fun getPoints(player: Player): Points
}