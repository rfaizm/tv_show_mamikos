package com.rachmanm.tvshow.di

import android.content.Context
import com.rachmanm.tvshow.data.api.ApiConfig
import com.rachmanm.tvshow.data.repository.TvShowRepositoryImpl
import com.rachmanm.tvshow.domain.repository.TvShowRepository
import com.rachmanm.tvshow.domain.usecase.GetTvShowListUseCase

object Injection {
    private fun provideTvShowRepository(context: Context): TvShowRepository {
        val apiService = ApiConfig.getApiService()
        return TvShowRepositoryImpl(apiService)
    }

    fun provideGetTvShowUseCase(context: Context): GetTvShowListUseCase {
        return GetTvShowListUseCase(provideTvShowRepository(context))
    }

}