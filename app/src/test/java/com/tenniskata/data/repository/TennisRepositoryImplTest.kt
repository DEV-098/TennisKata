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

        //Verify
        assertScores(Points.LOVE, Points.LOVE)
    }

    private fun assertScores(expectedPlayer1Points: Points, expectedPlayer2Points: Points) {
        val player1Points = classUnderTest.getPoints(Player.PLAYER_1)
        val player2Points = classUnderTest.getPoints(Player.PLAYER_2)
        Assert.assertEquals(expectedPlayer1Points, player1Points)
        Assert.assertEquals(expectedPlayer2Points, player2Points)
    }

    @Test
    fun `when increasePoint is called once getPoints should return Points_Fifteen`() {
        //When
        //Then
        classUnderTest.increasePoint(Player.PLAYER_1)
        //Verify

        assertScores(Points.FIFTEEN, Points.LOVE)
        //When
        //Then
        classUnderTest.increasePoint(Player.PLAYER_2)
        //Verify
        assertScores(Points.FIFTEEN, Points.FIFTEEN)
    }
}