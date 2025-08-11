package com.example.wallify.utlis.helpers

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.*

object THelperFunctions {

    /**
     * Map string value to Color.
     */
    fun getColor(value: String): Color? = when (value) {
        "Green" -> Color(0xFF4CAF50)
        "Red" -> Color(0xFFF44336)
        "Blue" -> Color(0xFF2196F3)
        "Pink" -> Color(0xFFE91E63)
        "Grey" -> Color(0xFF9E9E9E)
        "Purple" -> Color(0xFF9C27B0)
        "Black" -> Color(0xFF000000)
        "White" -> Color(0xFFFFFFFF)
        "Yellow" -> Color(0xFFFFEB3B)
        "Orange" -> Color(0xFFFF5722)
        "Brown" -> Color(0xFF795548)
        "Teal" -> Color(0xFF009688)
        "Indigo" -> Color(0xFF3F51B5)
        else -> null
    }

    /**
     * Show a SnackBar message (Use with ScaffoldState in Compose).
     */
    suspend fun showSnackBar(snackbarHostState: SnackbarHostState, message: String) {
        snackbarHostState.showSnackbar(message)
    }

    /**
     * Show an AlertDialog in Compose.
     *
     * Usage: control visibility with a mutableStateOf<Boolean>
     */
    @Composable
    fun ShowAlertDialog(
        show: Boolean,
        title: String,
        message: String,
        onDismiss: () -> Unit
    ) {
        if (show) {
            AlertDialog(
                onDismissRequest = onDismiss,
                title = { Text(title) },
                text = { Text(message) },
                confirmButton = {
                    TextButton(onClick = onDismiss) {
                        Text("OK")
                    }
                }
            )
        }
    }

    /**
     * Navigate to another screen (Compose style: use NavController).
     * This is a placeholder; real navigation should use Navigation Compose.
     */
    fun navigateToScreen(navController: androidx.navigation.NavController, route: String) {
        navController.navigate(route)
    }

    /**
     * Truncate text to a maximum length and append "..." if needed.
     */
    fun truncateText(text: String, maxLength: Int): String =
        if (text.length <= maxLength) text else text.substring(0, maxLength) + "..."

    /**
     * Check if the system is using Dark Theme.
     */
    @Composable
    fun isDarkMode(): Boolean = isSystemInDarkTheme()

    /**
     * Get screen size, height, width (in pixels).
     */
    fun screenSize(context: Context): android.util.Size {
        val displayMetrics = context.resources.displayMetrics
        return android.util.Size(displayMetrics.widthPixels, displayMetrics.heightPixels)
    }

    fun screenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    fun screenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    /**
     * Format date with custom pattern.
     */
    fun getFormattedDate(date: Date, format: String = "dd MMM yyyy"): String {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(date)
    }

    /**
     * Remove duplicates from a list.
     */
    fun <T> removeDuplicates(list: List<T>): List<T> = list.distinct()

    /**
     * Compose utility: Wrap composables in Row with given rowSize per row.
     * Usage:
     * val wrappedRows = com.example.wallify.utlis.helpers.com.example.wallify.common.widgets.chips.THelperFunctions.wrapComposablesInRows(list, 3)
     */
    @Composable
    fun wrapComposablesInRows(
        composables: List<@Composable () -> Unit>,
        rowSize: Int
    ): List<@Composable () -> Unit> {
        val wrappedList = mutableListOf<@Composable () -> Unit>()
        var idx = 0
        while (idx < composables.size) {
            val rowChildren = composables.subList(idx, minOf(idx + rowSize, composables.size))
            wrappedList.add {
                Row {
                    rowChildren.forEach { it() }
                }
            }
            idx += rowSize
        }
        return wrappedList
    }
}