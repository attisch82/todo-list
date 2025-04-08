package com.farkasatesz.mytodo.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object ActiveScreen : Screen()
    @Serializable
    data class CompletedScreen(val name: String) : Screen()
}