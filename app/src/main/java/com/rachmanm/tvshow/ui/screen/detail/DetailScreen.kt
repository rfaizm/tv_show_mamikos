package com.rachmanm.tvshow.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rachmanm.tvshow.domain.model.Show
import com.rachmanm.tvshow.ui.components.ShowPoster
import com.rachmanm.tvshow.ui.theme.OnSurfaceVariant
import com.rachmanm.tvshow.ui.util.stripHtml


/**
 * Detail screen — larger poster, title, summary and premiere date.
 * The actual sharing (building/launching the Intent) is left to the caller
 * via [onShareClick], keeping this composable free of Android context/business logic.
 *
 * @param show the show to display (expects image.original, name, summary, premiered)
 * @param onBackClick called when the back arrow is tapped
 * @param onShareClick called when the share icon is tapped
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    show: Show,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = show.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onShareClick) {
                        Icon(Icons.Filled.Share, contentDescription = "Share")
                    }
                }
            )
        },
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            ShowPoster(
                imageUrl = show.image?.original,
                contentDescription = show.name,
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
            )

            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = show.name,
                    style = MaterialTheme.typography.titleLarge
                )

                show.premiered?.let { premiered ->
                    Spacer(Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = null,
                            tint = OnSurfaceVariant,
                            modifier = Modifier.height(16.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "Premiered: $premiered",
                            style = MaterialTheme.typography.bodyMedium,
                            color = OnSurfaceVariant
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = show.summary?.let { stripHtml(it) } ?: "No summary available.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}