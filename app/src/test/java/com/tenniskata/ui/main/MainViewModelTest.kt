package com.tenniskata.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tenniskata.GameState
import com.tenniskata.Player
import com.tenniskata.Points
import com.tenniskata.data.repository.TennisRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class MainViewModelTest {
    @MockK
    lateinit var repository: TennisRepository

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var observer: Observer<GameState>

    private lateinit var classUnderTest: MainViewModel

    private val player1 = Player("Player 1")
    private val player2 = Player("Player 2")

    private var gameState: GameState = GameState.InProgress(Points.LOVE, Points.LOVE)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        classUnderTest = MainViewModel(repository)
        classUnderTest.state.observeForever(observer)
        every { observer.onChanged(any()) } returns Unit
        every { repository.startGame(any(), any()) } returns gameState
    }

    @Test
    fun `when startGame is called state should be changed to GameInProgress with Both Players having Love score`() {
        //when
        //then
        classUnderTest.startGame(player1.name, player2.name)
        //verify
        verify { observer.onChanged(gameState) }
    }

    @Test
    fun `when increasePlayer1Points is called before game started then the state should be in GameNotStarted`() {
        //when
        //then
        classUnderTest.increasePlayer1Point()
        //verify
        verify { observer.onChanged(GameState.GameNotStarted) }
    }

    @Test
    fun `when increasePlayer1Points is called after game started repositories increase point should be called with Player1's object`() {
        //when
        gameState = mockk()
        val playerSlot = slot<Player>()
        every { repository.increasePoint(capture(playerSlot)) } returns gameState
        //then
        classUnderTest.startGame(player1.name, player2.name)
        classUnderTest.increasePlayer1Point()
        //verify
        verify { observer.onChanged(gameState) }
        verify { repository.increasePoint(playerSlot.captured) }
        assert(playerSlot.captured.name == player1.name)
    }


    @Test
    fun `when increasePlayer2Points is called before game started then the state should be in GameNotStarted`() {
        //when
        //then
        classUnderTest.increasePlayer2Point()
        //verify
        verify { observer.onChanged(GameState.GameNotStarted) }
    }

    @Test
    fun `when increasePlayer2Points is called repositories increase point should be called with Player2's object`() {
        //when
        gameState = mockk()
        val playerSlot = slot<Player>()
        every { repository.increasePoint(capture(playerSlot)) } returns gameState
        //then
        classUnderTest.startGame(player1.name, player2.name)
        classUnderTest.increasePlayer2Point()
        //verify
        verify { observer.onChanged(gameState) }
        verify { repository.increasePoint(playerSlot.captured) }
        assert(playerSlot.captured.name == player2.name)
    }
}