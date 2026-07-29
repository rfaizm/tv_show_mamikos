package com.rachmanm.tvshow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rachmanm.tvshow.ui.theme.OnSurfaceVariant
import com.rachmanm.tvshow.ui.theme.PosterPlaceholder


/**
 * Reusable poster image.
 *
 * @param imageUrl url to load (image.medium for list, image.original for detail) — nullable, since API can omit it
 * @param contentDescription accessibility label, typically the show name
 * @param modifier size/shape is controlled by the caller (fillMaxWidth + aspectRatio, fixed height, etc.)
 * @param shape corner shape applied to the poster
 */
@Composable
fun ShowPoster(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp)
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(PosterPlaceholder),
        contentAlignment = Alignment.Center
    ) {
        if (imageUrl != null) {
            AsyncImage(
                model = imageUrl,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Icon(
                imageVector = Icons.Filled.BrokenImage,
                contentDescription = null,
                tint = OnSurfaceVariant
            )
        }
    }
}