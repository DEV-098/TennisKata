package com.tenniskata.data.repository

import com.tenniskata.GameState
import com.tenniskata.Player
import com.tenniskata.Points
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class TennisRepositoryImplTest {
    private lateinit var classUnderTest: TennisRepositoryImpl
    private val player1 = Player("Player 1")
    private val player2 = Player("Player 2")

    @Before
    fun setup() {
        classUnderTest = TennisRepositoryImpl()
        classUnderTest.startGame(player1, player2)
    }

    @Test
    fun `initially when game starts for both players the points should be Love`() {
        //When
        //Then

        //Verify
        assertScores(Points.LOVE, Points.LOVE)
    }

    private fun assertScores(expectedPlayer1Points: Points, expectedPlayer2Points: Points) {
        val player1Points = classUnderTest.getPoints(player1)
        val player2Points = classUnderTest.getPoints(player2)
        Assert.assertEquals(expectedPlayer1Points, player1Points)
        Assert.assertEquals(expectedPlayer2Points, player2Points)
    }

    @Test
    fun `when increasePoint is called once getPoints should return Points_Fifteen`() {
        //When
        //Then
        classUnderTest.increasePoint(player1)
        //Verify

        assertScores(Points.FIFTEEN, Points.LOVE)
        //When
        //Then
        classUnderTest.increasePoint(player2)
        //Verify
        assertScores(Points.FIFTEEN, Points.FIFTEEN)
    }

    @Test
    fun `when increasePoint is called 3 time getPoints should return Points_Forty`() {
        //When
        //Then
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player1)
        //Verify

        assertScores(Points.FORTY, Points.LOVE)
    }

    @Test(expected = IllegalStateException::class)
    fun `when increasePoint is called 5 time getPoints should throw IllegalStateException`() {
        //When
        //Then
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player1)
        classUnderTest.getPoints(player1)
        //Verify
    }

    @Test
    fun `when GameStarts and both player scored maximum 2 times the game should be in progress state`() {
        //when
        //then
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player2)
        classUnderTest.increasePoint(player1)
        val result = classUnderTest.increasePoint(player2)
        //verify
        assert(result is GameState.InProgress)
    }


    @Test
    fun `when GameStarts and both player scored exactly 3 times the game should be in deuce state`() {
        //when
        //then
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player2)
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player2)
        classUnderTest.increasePoint(player1)
        val result = classUnderTest.increasePoint(player2)
        //verify
        assert(result is GameState.Deuce)
    }

    @Test
    fun `when GameStarts and both player scored exactly 3 times and then player1 scores advantage the game should be in Advantage state`() {
        //when
        //then
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player2)
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player2)
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player2)
        val result = classUnderTest.increasePoint(player1)
        //verify
        assert(result is GameState.Advantage)
        Assert.assertEquals(player1, (result as GameState.Advantage).player)
    }


    @Test
    fun `when GameStarts and both player scored exactly 2 times and then player1 scores twice  the game should be in won by Player 1`() {
        //when
        //then
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player2)
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player2)
        classUnderTest.increasePoint(player1)
        val result = classUnderTest.increasePoint(player1)
        //verify
        assert(result is GameState.PlayerWins)
        Assert.assertEquals(player1, (result as GameState.PlayerWins).player)
    }


    @Test
    fun `when GameStarts and both player scored exactly 3 times and then player1 scores once Player 2 scores once and then Player 1 scores twice  the game should be in won by Player 1`() {
        //when
        //then
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player2)
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player2)
        classUnderTest.increasePoint(player1)
        classUnderTest.increasePoint(player2)
        //So the Game should be in deuce
        classUnderTest.increasePoint(player1)
        //Player 1 is in advantage
        classUnderTest.increasePoint(player2)
        //Player2 makes it deuce again
        classUnderTest.increasePoint(player1)
        //Player 1 took advantage
        val result = classUnderTest.increasePoint(player1)
        //Player 1 should win
        //verify
        assert(result is GameState.PlayerWins)
        Assert.assertEquals(player1, (result as GameState.PlayerWins).player)
    }
}