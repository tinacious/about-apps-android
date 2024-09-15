package com.tinaciousdesign.aboutapps.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tinaciousdesign.aboutapps.ui.screens.about.AboutScreen
import com.tinaciousdesign.aboutapps.ui.screens.appsearch.AppSearchScreen
import com.tinaciousdesign.aboutapps.ui.screens.appsearch.AppSearchViewModel

@Composable
fun NavigationRouter(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Route.AppsList) {
        composable<Route.AppsList> { backStackEntry ->
            val viewModel = hiltViewModel<AppSearchViewModel>()

            AppSearchScreen(viewModel)
        }

        composable<Route.About> { backStackEntry ->
            AboutScreen()
        }
    }
}
