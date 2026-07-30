package com.rachmanm.tvshow.ui.screen.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rachmanm.tvshow.di.ResultState
import com.rachmanm.tvshow.domain.model.Show
import com.rachmanm.tvshow.domain.usecase.GetTvShowListUseCase
import com.rachmanm.tvshow.util.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListScreenViewModelTest {

    // Makes LiveData.setValue() run synchronously in a JVM unit test
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    // Swaps Dispatchers.Main so viewModelScope.launch runs on the test dispatcher
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var useCase: GetTvShowListUseCase

    private val fakeShows = listOf(Show(id = 1, name = "Homeland"))

    @Before
    fun setup() {
        useCase = mockk()
    }

    @Test
    fun `state becomes Success when use case emits Loading then Success`() = runTest {
        every { useCase() } returns flowOf(
            ResultState.Loading,
            ResultState.Success(fakeShows)
        )

        // loadShows() is called from init { }, so constructing the ViewModel triggers the fetch
        val viewModel = ListScreenViewModel(useCase)
        advanceUntilIdle()

        val state = viewModel.showListState.value
        assertTrue(state is ResultState.Success)
        assertEquals(fakeShows, (state as ResultState.Success).data)
    }

    @Test
    fun `state becomes Error when use case emits Loading then Error`() = runTest {
        every { useCase() } returns flowOf(
            ResultState.Loading,
            ResultState.Error("Network error")
        )

        val viewModel = ListScreenViewModel(useCase)
        advanceUntilIdle()

        val state = viewModel.showListState.value
        assertTrue(state is ResultState.Error)
        assertEquals("Network error", (state as ResultState.Error).error)
    }

    @Test
    fun `calling loadShows again re-fetches and updates state`() = runTest {
        every { useCase() } returns flowOf(ResultState.Loading, ResultState.Success(fakeShows))
        val viewModel = ListScreenViewModel(useCase)
        advanceUntilIdle()

        every { useCase() } returns flowOf(ResultState.Loading, ResultState.Error("Retry failed"))
        viewModel.loadShows()
        advanceUntilIdle()

        val state = viewModel.showListState.value
        assertTrue(state is ResultState.Error)
        assertEquals("Retry failed", (state as ResultState.Error).error)
    }
}