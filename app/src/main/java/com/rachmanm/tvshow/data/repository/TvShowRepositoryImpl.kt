package com.rachmanm.tvshow.data.repository

import android.util.Log
import com.rachmanm.tvshow.data.api.ApiService
import com.rachmanm.tvshow.data.mapper.toShowList
import com.rachmanm.tvshow.domain.model.Show
import com.rachmanm.tvshow.domain.repository.TvShowRepository

class TvShowRepositoryImpl(
    private val apiService: ApiService
) : TvShowRepository {

    private val TAG = "TvShowRepositoryImpl"

    override suspend fun getTvShowList(): List<Show> {
        val response = apiService.getListTvShow()
        Log.d(TAG, "getTvShowList: fetched ${response.size} shows")
        return response.toShowList()
    }
}