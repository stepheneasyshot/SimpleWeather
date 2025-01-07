package com.stephen.simpleweather.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stephen.simpleweather.ui.navigation.AboutScreen
import com.stephen.simpleweather.ui.navigation.AuthorScreen
import com.stephen.simpleweather.ui.navigation.HomeScreen
import com.stephen.simpleweather.ui.navigation.ScreenTitle
import com.stephen.simpleweather.viewmodel.MainViewModel
import org.koin.compose.getKoin


@Composable
fun MainView(
    viewModel: MainViewModel = getKoin().get(),
    navController: NavHostController = rememberNavController(),
) {
    val weatherScreenState by viewModel.weatherViewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initGetData()
    }

    NavHost(navController = navController, startDestination = ScreenTitle.Home.name) {
        composable(route = ScreenTitle.Home.name) {
            HomeScreen(
                weatherScreenState,
                onNavToAbout = { navController.navigate(ScreenTitle.About.name) },
                onNavToAuthor = { navController.navigate(ScreenTitle.Author.name) })
        }
        composable(route = ScreenTitle.About.name) {
            AboutScreen(onBack = { navController.popBackStack() })
        }
        composable(route = ScreenTitle.Author.name) {
            AuthorScreen(onBack = { navController.popBackStack() })
        }
    }
}
