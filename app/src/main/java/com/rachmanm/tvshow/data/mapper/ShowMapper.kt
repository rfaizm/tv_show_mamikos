package com.rachmanm.tvshow.data.mapper

import com.rachmanm.tvshow.data.api.dto.ListTVShowResponseItemDto
import com.rachmanm.tvshow.domain.model.Rating
import com.rachmanm.tvshow.domain.model.Show
import com.rachmanm.tvshow.domain.model.ShowImage

/**
 * Converts a single DTO item into the domain [Show] model.
 * Anything nullable in the DTO stays nullable here where it makes sense
 * (rating.average, image urls) instead of being defaulted away.
 */
fun ListTVShowResponseItemDto.toShow(): Show {
    return Show(
        id = this.id ?: 0,
        name = this.name.orEmpty(),
        summary = this.summary,
        premiered = this.premiered,
        url = this.url,
        rating = Rating(
            // rating.average is typed Any? in the DTO (Gson quirk) — safely
            // coerce it to Double, or null if it's genuinely null/absent.
            average = (this.rating?.average as? Number)?.toDouble()
        ),
        image = ShowImage(
            medium = this.image?.medium,
            original = this.image?.original
        )
    )
}

fun List<ListTVShowResponseItemDto>.toShowList(): List<Show> = this.map { it.toShow() }