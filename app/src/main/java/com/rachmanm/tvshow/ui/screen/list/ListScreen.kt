package com.rachmanm.tvshow.ui.screen.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rachmanm.tvshow.di.ResultState
import com.rachmanm.tvshow.di.ViewModelFactory
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
    onShowClick: (Show) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ListScreenViewModel = viewModel(
        factory = ViewModelFactory.getInstance(LocalContext.current)
    )
) {
    val state by viewModel.showListState.observeAsState(ResultState.Loading)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("TV Shows") })
        },
        modifier = modifier
    ) { padding ->
        when (val currentState = state) {
            is ResultState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ResultState.Success -> {
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
                    items(currentState.data, key = { it.id }) { show ->
                        ShowGridItem(
                            show = show,
                            onClick = { onShowClick(show) }
                        )
                    }
                }
            }

            is ResultState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = currentState.error,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = { viewModel.loadShows() }) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}