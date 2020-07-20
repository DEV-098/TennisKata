package com.tenniskata.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tenniskata.GameState
import com.tenniskata.Player
import com.tenniskata.Points
import com.tenniskata.data.repository.TennisRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
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

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        classUnderTest = MainViewModel(repository)
        classUnderTest.state.observeForever(observer)
        every { observer.onChanged(any()) } returns Unit
    }

    @Test
    fun `when startGame is called state should be changed to GameInProgress with Both Players having Love score`() {
        //when
        val gameState = GameState.InProgress(Points.LOVE, Points.LOVE)
        every { repository.startGame(any(), any()) } returns gameState
        //then
        classUnderTest.startGame(player1.name, player2.name)
        //verify
        verify { observer.onChanged(gameState) }
    }
}