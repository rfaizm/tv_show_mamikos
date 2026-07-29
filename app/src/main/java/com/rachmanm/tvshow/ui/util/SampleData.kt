package com.rachmanm.tvshow.ui.util

import com.rachmanm.tvshow.domain.model.Rating
import com.rachmanm.tvshow.domain.model.Show
import com.rachmanm.tvshow.domain.model.ShowImage

val sampleShows = listOf(
    Show(
        id = 1,
        name = "Under the Dome",
        summary = "<p>Under the Dome is the story of a small town cut off from the rest of the world.</p>",
        premiered = "2013-06-24",
        url = "https://www.tvmaze.com/shows/1/under-the-dome",
        rating = Rating(average = 6.5),
        image = ShowImage(
            medium = "https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg",
            original = "https://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg"
        )
    ),
    Show(
        id = 2,
        name = "Person of Interest",
        summary = "<p>A mysterious billionaire designs a machine that predicts crime before it happens.</p>",
        premiered = "2011-09-22",
        url = "https://www.tvmaze.com/shows/2/person-of-interest",
        rating = Rating(average = null), // deliberately null to show handling
        image = ShowImage(
            medium = "https://static.tvmaze.com/uploads/images/medium_portrait/163/408729.jpg",
            original = "https://static.tvmaze.com/uploads/images/original_untouched/163/408729.jpg"
        )
    ),
    Show(
        id = 3,
        name = "Bitten",
        summary = "<p>A woman struggles to balance her life as a werewolf with her human relationships.</p>",
        premiered = "2014-01-11",
        url = "https://www.tvmaze.com/shows/3/bitten",
        rating = Rating(average = 7.7),
        image = ShowImage(
            medium = null, // deliberately null to show placeholder
            original = null
        )
    ),
    Show(
        id = 4,
        name = "Homeland",
        summary = "<p>A U.S. Marine returns from Iraq and is suspected of being a sleeper agent.</p>",
        premiered = "2011-10-02",
        url = "https://www.tvmaze.com/shows/4/homeland",
        rating = Rating(average = 8.3),
        image = ShowImage(
            medium = "https://static.tvmaze.com/uploads/images/medium_portrait/141/353555.jpg",
            original = "https://static.tvmaze.com/uploads/images/original_untouched/141/353555.jpg"
        )
    )
)