package com.rachmanm.tvshow.domain.model

data class Show(
    val id: Int,
    val name: String,
    val summary: String? = null,   // may contain HTML tags, e.g. <p>...</p>
    val premiered: String? = null, // e.g. "2011-04-17"
    val url: String? = null,       // show page url, used when sharing
    val rating: Rating? = null,
    val image: ShowImage? = null
)

data class Rating(
    val average: Double? = null    // nullable on purpose — API can return null
)

data class ShowImage(
    val medium: String? = null,
    val original: String? = null
)
