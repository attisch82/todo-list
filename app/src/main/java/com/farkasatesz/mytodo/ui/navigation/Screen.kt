package com.farkasatesz.mytodo.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object ActiveScreen : Screen()
    @Serializable
    object CompletedScreen : Screen()
}