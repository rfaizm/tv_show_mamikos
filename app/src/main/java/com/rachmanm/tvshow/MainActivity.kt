package com.rachmanm.tvshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rachmanm.tvshow.ui.screen.detail.DetailScreen
import com.rachmanm.tvshow.ui.screen.list.ListScreen
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
    val context = androidx.compose.ui.platform.LocalContext.current

    NavHost(navController = navController, startDestination = ROUTE_LIST) {
        composable(ROUTE_LIST) {
            ListScreen(
                shows = sampleShows,
                onShowClick = { show -> navController.navigate("detail/${show.id}") }
            )
        }
        composable(
            route = ROUTE_DETAIL,
            arguments = listOf(navArgument("showId") { type = NavType.IntType })
        ) { backStackEntry ->
            val showId = backStackEntry.arguments?.getInt("showId")
            val show = sampleShows.first { it.id == showId }

            DetailScreen(
                show = show,
                onBackClick = { navController.popBackStack() },
                onShareClick = { shareShow(context, show) }
            )
        }
    }
}