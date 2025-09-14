@file:Suppress("DEPRECATION")

package com.example.wallify.utlis.devices

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat

object TDeviceUtils {

    // Hide the virtual keyboard
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = activity.currentFocus ?: View(activity)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // Set the status bar color
    fun setStatusBarColor(activity: Activity, color: androidx.compose.ui.graphics.Color) {
        activity.window.statusBarColor = color.toArgb()
    }

    // Check if the device is in landscape orientation
    fun isLandscapeOrientation(context: Context): Boolean {
        return context.resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
    }

    // Check if the device is in portrait orientation
    fun isPortraitOrientation(context: Context): Boolean {
        return context.resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT
    }

    // Enable or disable full screen mode
    fun setFullScreen(activity: Activity, enable: Boolean) {
        if (enable) {
            WindowCompat.setDecorFitsSystemWindows(activity.window, false)
            activity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        } else {
            WindowCompat.setDecorFitsSystemWindows(activity.window, true)
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }

    // Get the screen height in pixels
    fun getScreenHeight(activity: Activity): Int {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        return metrics.heightPixels
    }

    // Get the screen width in pixels
    fun getScreenWidth(activity: Activity): Int {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        return metrics.widthPixels
    }

    // Get the device pixel ratio (screen density)
    fun getPixelRatio(activity: Activity): Float {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        return metrics.density
    }

    // Get the status bar height in pixels
    @SuppressLint("InternalInsetResource")
    fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
    }

    // Get the navigation bar height in pixels
    @SuppressLint("InternalInsetResource")
    fun getNavigationBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
    }

    // Get the default app bar (toolbar) height in pixels
    fun getAppBarHeight(context: Context): Int {
        val styledAttributes = context.theme.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        val appBarHeight = styledAttributes.getDimension(0, 0f).toInt()
        styledAttributes.recycle()
        return appBarHeight
    }

    // Get the height of the virtual keyboard if visible
    fun getKeyboardHeight(activity: Activity): Int {
        val rootView = activity.window.decorView
        val insets = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            rootView.rootWindowInsets
        } else {
            null
        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            insets?.getInsets(WindowInsets.Type.ime())?.bottom ?: 0
        } else {
            TODO("VERSION.SDK_INT < R")
        }
    }

    // Check if the virtual keyboard is currently visible
    fun isKeyboardVisible(activity: Activity): Boolean {
        return getKeyboardHeight(activity) > 0
    }

    // Vibrate the device for a given duration (in milliseconds)
    fun isPhysicalDevice(): Boolean {
        return Build.FINGERPRINT != "robolectric"
    }

    // Set the preferred screen orientation
    fun setPreferredOrientations(activity: Activity, orientation: Int) {
        activity.requestedOrientation = orientation
    }

    // Hide the status bar
    fun hideStatusBar(activity: Activity) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    // Show the status bar
    fun showStatusBar(activity: Activity) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    // Kiểm tra kết nối Internet
    fun hasInternetConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
    // Launch an URL in the browser or related app
    fun launchUrl(context: Context, url: String) {
        val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(url))
        context.startActivity(intent)
    }
}