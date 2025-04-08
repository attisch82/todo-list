package com.farkasatesz.mytodo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.farkasatesz.mytodo.ui.screens.activeScreen.ActiveScreen
import com.farkasatesz.mytodo.ui.screens.activeScreen.ActiveScreenViewModel
import com.farkasatesz.mytodo.ui.screens.completedScreen.CompletedScreen
import com.farkasatesz.mytodo.ui.screens.completedScreen.CompletetedScreenViewModel

@Composable
fun TodoNavigation() {

    val navController = rememberNavController()
    val activeTodoViewModel : ActiveScreenViewModel = hiltViewModel()
    val completedTodoViewModel : CompletetedScreenViewModel = hiltViewModel()


    NavHost(
        navController = navController,
        startDestination = Screen.ActiveScreen
    ){
        composable<Screen.ActiveScreen> {
            ActiveScreen(navController, activeTodoViewModel)
        }
        composable<Screen.CompletedScreen> {
            val args = it.toRoute<Screen.CompletedScreen>()
            CompletedScreen(navController, completedTodoViewModel, args.name)
        }

    }

}