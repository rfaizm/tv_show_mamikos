package com.rachmanm.tvshow.data.api

import com.rachmanm.tvshow.data.api.dto.ListTVShowResponseItemDto
import retrofit2.http.GET

interface ApiService {
    @GET("shows?page=0")
    suspend fun getListTvShow() : List<ListTVShowResponseItemDto>
}
