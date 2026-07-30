package com.rachmanm.tvshow.data.api.dto

import com.google.gson.annotations.SerializedName

data class ListTVShowResponse(

    @field:SerializedName("ListTVShowResponse")
    val listTVShowResponse: List<ListTVShowResponseItemDto?>? = null
)

data class ImageDto(

    @field:SerializedName("original")
    val original: String? = null,

    @field:SerializedName("medium")
    val medium: String? = null
)

data class ExternalsDto(

    @field:SerializedName("thetvdb")
    val thetvdb: Int? = null,

    @field:SerializedName("imdb")
    val imdb: String? = null,

    @field:SerializedName("tvrage")
    val tvrage: Int? = null
)

data class LinksDto(

    @field:SerializedName("self")
    val self: SelfDto? = null,

    @field:SerializedName("previousepisode")
    val previousepisode: PreviousepisodeDto? = null
)

data class RatingDto(

    @field:SerializedName("average")
    val average: Any? = null
)

data class NetworkDto(

    @field:SerializedName("country")
    val country: CountryDto? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("officialSite")
    val officialSite: Any? = null
)

data class SelfDto(

    @field:SerializedName("href")
    val href: String? = null
)

data class ListTVShowResponseItemDto(

    @field:SerializedName("summary")
    val summary: String? = null,

    @field:SerializedName("image")
    val image: ImageDto? = null,

    @field:SerializedName("averageRuntime")
    val averageRuntime: Int? = null,

    @field:SerializedName("dvdCountry")
    val dvdCountry: Any? = null,

    @field:SerializedName("_links")
    val links: LinksDto? = null,

    @field:SerializedName("premiered")
    val premiered: String? = null,

    @field:SerializedName("rating")
    val rating: RatingDto? = null,

    @field:SerializedName("runtime")
    val runtime: Int? = null,

    @field:SerializedName("weight")
    val weight: Int? = null,

    @field:SerializedName("language")
    val language: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("officialSite")
    val officialSite: String? = null,

    @field:SerializedName("network")
    val network: NetworkDto? = null,

    @field:SerializedName("schedule")
    val schedule: ScheduleDto? = null,

    @field:SerializedName("webChannel")
    val webChannel: WebChannelDto? = null,

    @field:SerializedName("genres")
    val genres: List<String?>? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("ended")
    val ended: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("externals")
    val externals: ExternalsDto? = null,

    @field:SerializedName("updated")
    val updated: Int? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class ScheduleDto(

    @field:SerializedName("days")
    val days: List<String?>? = null,

    @field:SerializedName("time")
    val time: String? = null
)

data class WebChannelDto(

    @field:SerializedName("country")
    val country: CountryDto? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("officialSite")
    val officialSite: String? = null
)

data class PreviousepisodeDto(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("href")
    val href: String? = null
)

data class CountryDto(

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("timezone")
    val timezone: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)