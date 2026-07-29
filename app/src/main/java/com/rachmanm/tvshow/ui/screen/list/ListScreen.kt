package com.rachmanm.tvshow.ui.screen.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rachmanm.tvshow.domain.model.Show
import com.rachmanm.tvshow.ui.components.ShowGridItem

/**
 * Main screen — displays shows in a 2-column scrollable grid.
 *
 * @param shows list of shows to display (list-endpoint style data: id, name, image.medium, rating.average)
 * @param onShowClick called with the tapped show, used to navigate to Detail
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    shows: List<Show>,
    onShowClick: (Show) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("TV Shows") })
        },
        modifier = modifier
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 12.dp,
                end = 12.dp,
                top = padding.calculateTopPadding() + 12.dp,
                bottom = 12.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(shows, key = { it.id }) { show ->
                ShowGridItem(
                    show = show,
                    onClick = { onShowClick(show) }
                )
            }
        }
    }
}