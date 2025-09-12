package com.example.core_viewmodel.utils.popups

import androidx.compose.material3.*
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object TLoaders {
    // Show a custom toast using SnackbarHost (for Compose screens)
    fun customToast(
        scope: CoroutineScope,
        snackbarHostState: SnackbarHostState,
        message: String
    ) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                withDismissAction = true,
                duration = SnackbarDuration.Short
            )
        }
    }

    // Show a success snackbar
    fun successSnackBar(
        scope: CoroutineScope,
        snackbarHostState: SnackbarHostState,
        title: String,
        message: String = "",
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "$title\n$message",
                withDismissAction = true,
                duration = duration
            )
        }
    }

    // Show a warning snackbar (orange background - custom via host)
    fun warningSnackBar(
        scope: CoroutineScope,
        snackbarHostState: SnackbarHostState,
        title: String,
        message: String = "",
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "$title\n$message",
                withDismissAction = true,
                duration = duration
            )
        }
    }

    // Show an error snackbar (red background - custom via host)
    fun errorSnackBar(
        scope: CoroutineScope,
        snackbarHostState: SnackbarHostState,
        title: String,
        message: String = "",
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "$title\n$message",
                withDismissAction = true,
                duration = duration
            )
        }
    }
}