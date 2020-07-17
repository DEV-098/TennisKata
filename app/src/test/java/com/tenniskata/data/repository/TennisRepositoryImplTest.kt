package com.tenniskata.data.repository

import com.tenniskata.data.Player
import com.tenniskata.data.Points
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class TennisRepositoryImplTest {
    lateinit var classUnderTest: TennisRepositoryImpl

    @Before
    fun setup() {
        classUnderTest = TennisRepositoryImpl()
    }

    @Test
    fun `initially for both players the points should be Love`() {
        //When
        //Then
        val player1Points = classUnderTest.getPoints(Player.PLAYER_1)
        val player2Points = classUnderTest.getPoints(Player.PLAYER_2)

        //Verify
        Assert.assertEquals(Points.LOVE, player1Points)
        Assert.assertEquals(Points.LOVE, player2Points)
    }

    @Test
    fun `when increasePoint is called once getPoints should return Points_Fifteen`() {
        //When
        //Then
        classUnderTest.increasePoint(Player.PLAYER_1)
        //Verify
        val points = classUnderTest.getPoints(Player.PLAYER_1)
        Assert.assertEquals(Points.FIFTEEN, points)
    }
}