package com.farkasatesz.mytodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.farkasatesz.mytodo.ui.screens.activeScreen.ActiveScreen
import com.farkasatesz.mytodo.ui.screens.activeScreen.ActiveScreenViewModel
import com.farkasatesz.mytodo.ui.screens.completedScreen.CompletedScreen
import com.farkasatesz.mytodo.ui.screens.completedScreen.CompletetedScreenViewModel
import com.farkasatesz.mytodo.ui.theme.MyTodoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel : CompletetedScreenViewModel by viewModels()
            MyTodoTheme {
                CompletedScreen(viewModel)
            }
        }
    }
}