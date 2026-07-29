package com.rachmanm.tvshow.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rachmanm.tvshow.domain.model.Show

/**
 * Single cell used inside the shows grid.
 *
 * @param show the show to render
 * @param onClick called when the cell is tapped (used to navigate to Detail)
 */
@Composable
fun ShowGridItem(
    show: Show,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.68f)
        ) {
            ShowPoster(
                imageUrl = show.image?.medium,
                contentDescription = show.name,
                modifier = Modifier.fillMaxWidth().aspectRatio(0.68f)
            )
            RatingBadge(
                rating = show.rating?.average,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(6.dp)
            )
        }
        Text(
            text = show.name,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, start = 2.dp, end = 2.dp)
        )
    }
}