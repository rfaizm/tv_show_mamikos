package com.rachmanm.tvshow.domain.repository

import com.rachmanm.tvshow.domain.model.Show

interface TvShowRepository {
    suspend fun getTvShowList(): List<Show>
}