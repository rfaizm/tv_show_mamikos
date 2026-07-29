package com.rachmanm.tvshow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rachmanm.tvshow.ui.theme.RatingBackground
import com.rachmanm.tvshow.ui.theme.RatingStar


/**
 * Small pill-shaped rating badge.
 *
 * @param rating nullable average rating (e.g. rating.average) — shows "N/A" when null
 */
@Composable
fun RatingBadge(
    rating: Double?,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(RatingBackground)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = RatingStar,
            modifier = Modifier
        )
        Text(
            text = rating?.let { "%.1f".format(it) } ?: "N/A",
            color = Color.White,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}