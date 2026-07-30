package com.rachmanm.tvshow.domain.usecase

import com.rachmanm.tvshow.di.ResultState
import com.rachmanm.tvshow.domain.model.Show
import com.rachmanm.tvshow.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTvShowListUseCase(private val tvShowRepository: TvShowRepository) {

    operator fun invoke(): Flow<ResultState<List<Show>>> = flow {
        emit(ResultState.Loading)
        try {
            val shows = tvShowRepository.getTvShowList()
            emit(ResultState.Success(shows))
        } catch (e: Exception) {
            emit(ResultState.Error(e.message ?: "Terjadi kesalahan saat mengambil daftar TV show"))
        }
    }

}