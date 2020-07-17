package com.tenniskata.data.repository

import com.tenniskata.data.GameState
import com.tenniskata.data.Player
import com.tenniskata.data.Points

interface TennisRepository {
    fun startGame(player1: Player, player2: Player)
    fun increasePoint(player: Player): GameState
    fun getPoints(player: Player): Points
}