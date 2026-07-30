package com.rachmanm.tvshow.domain.usecase

import com.rachmanm.tvshow.di.ResultState
import com.rachmanm.tvshow.domain.model.Rating
import com.rachmanm.tvshow.domain.model.Show
import com.rachmanm.tvshow.domain.model.ShowImage
import com.rachmanm.tvshow.domain.repository.TvShowRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetTvShowListUseCaseTest {

    private lateinit var repository: TvShowRepository
    private lateinit var useCase: GetTvShowListUseCase

    private val fakeShows = listOf(
        Show(
            id = 250,
            name = "Kirby Buckets",
            rating = Rating(average = null), // TVMaze can legitimately return this
            image = ShowImage(medium = "medium.jpg", original = "original.jpg")
        )
    )

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetTvShowListUseCase(repository)
    }

    @Test
    fun `invoke emits Loading then Success with the repository data`() = runTest {
        coEvery { repository.getTvShowList() } returns fakeShows

        val emissions = useCase().toList()

        assertEquals(2, emissions.size)
        assertTrue(emissions[0] is ResultState.Loading)
        assertTrue(emissions[1] is ResultState.Success)

        val successState = emissions[1] as ResultState.Success
        assertEquals(fakeShows, successState.data)
        // spot-check the nullable rating survives untouched, not defaulted to 0
        assertNull(successState.data.first().rating?.average)
    }

    @Test
    fun `invoke emits Loading then Error when repository throws`() = runTest {
        coEvery { repository.getTvShowList() } throws IOException("Network error")

        val emissions = useCase().toList()

        assertEquals(2, emissions.size)
        assertTrue(emissions[0] is ResultState.Loading)
        assertTrue(emissions[1] is ResultState.Error)
        assertEquals("Network error", (emissions[1] as ResultState.Error).error)
    }

    @Test
    fun `invoke falls back to default message when exception has no message`() = runTest {
        coEvery { repository.getTvShowList() } throws IOException()

        val emissions = useCase().toList()

        val errorState = emissions[1] as ResultState.Error
        assertEquals("Terjadi kesalahan saat mengambil daftar TV show", errorState.error)
    }
}