package com.tenniskata.data.repository

import com.tenniskata.data.Player
import com.tenniskata.data.Points

class TennisRepositoryImpl() : TennisRepository {
    override fun increasePoint(player: Player) {

    }

    override fun getPoints(player: Player): Points {
        return Points.LOVE
    }
}