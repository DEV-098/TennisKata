package com.tenniskata.data.repository

import com.tenniskata.data.Player
import com.tenniskata.data.Points

interface TennisRepository {
    fun increasePoint(player: Player)
    fun getPoints(player: Player): Points
}