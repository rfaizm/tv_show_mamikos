package com.rachmanm.tvshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rachmanm.tvshow.di.ResultState
import com.rachmanm.tvshow.di.ViewModelFactory
import com.rachmanm.tvshow.ui.screen.detail.DetailScreen
import com.rachmanm.tvshow.ui.screen.list.ListScreen
import com.rachmanm.tvshow.ui.screen.list.ListScreenViewModel
import com.rachmanm.tvshow.ui.theme.TvShowsTheme
import com.rachmanm.tvshow.ui.util.sampleShows
import com.rachmanm.tvshow.ui.util.shareShow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvShowsTheme {
                TvShowsApp()
            }
        }
    }
}

private const val ROUTE_LIST = "list"
private const val ROUTE_DETAIL = "detail/{showId}"

@Composable
fun TvShowsApp() {
    val navController = rememberNavController()
    val context = LocalContext.current

    // Hoisted here so Detail can look the tapped show up from the same
    // in-memory list already fetched, instead of firing a second call.
    val listViewModel: ListScreenViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context)
    )

    NavHost(navController = navController, startDestination = ROUTE_LIST) {
        composable(ROUTE_LIST) {
            ListScreen(
                viewModel = listViewModel,
                onShowClick = { show -> navController.navigate("detail/${show.id}") }
            )
        }
        composable(
            route = ROUTE_DETAIL,
            arguments = listOf(navArgument("showId") { type = NavType.IntType })
        ) { backStackEntry ->
            val showId = backStackEntry.arguments?.getInt("showId")
            val state by listViewModel.showListState.observeAsState(ResultState.Loading)
            val show = (state as? ResultState.Success)?.data?.firstOrNull { it.id == showId }

            if (show != null) {
                DetailScreen(
                    show = show,
                    onBackClick = { navController.popBackStack() },
                    onShareClick = { shareShow(context, show) }
                )
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}