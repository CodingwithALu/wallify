package com.example.wallify.navigation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.core_model.BottomNavItem
import com.example.wallify.utlis.route.Screen
import com.example.wallify.R
import com.example.wallify.utlis.route.routesToHideBottomBar
import androidx.compose.ui.draw.clip
import com.example.wallify.utlis.constants.TSizes

@Composable
fun BottomAppBarr(
    showBar: Boolean = false,
    navController: NavController
) {
    val navItems = listOf(
        BottomNavItem(Screen.Home.route, icon = null, imageRes = R.drawable.home),
        BottomNavItem(Screen.Streak.route, icon = null, imageRes = R.drawable.flash),
        BottomNavItem(Screen.Collection.route, icon = null, imageRes = R.drawable.layers),
        BottomNavItem(Screen.Favorite.route, icon = null, imageRes = R.drawable.heart)
    )
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentRoute = navBackStackEntry?.destination?.route
    LaunchedEffect(navController.currentBackStackEntry) {
        currentRoute = navController.currentBackStackEntry?.destination?.route
        selectedIndex = navItems.indexOfFirst { it.label == currentRoute }
    }

    if (showBar) {
        AnimatedVisibility(
            visible = showBar,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(1000)
            ) + fadeIn(animationSpec = tween(1000)),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(1000)
            ) + fadeOut(animationSpec = tween(1000)),
        ) {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
                    .clip(RoundedCornerShape(TSizes.lg)),
                containerColor = Color.Transparent,
                tonalElevation = 0.dp
            ) {
                if (currentRoute !in routesToHideBottomBar) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(Color.Transparent)
                            .align (Alignment.Top),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        navItems.forEachIndexed { index, item ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .align(Alignment.Top)
                                    .clickable {
                                        selectedIndex = index
                                        navController.navigate(item.label)
                                    }
                            ) {
                                Icon(
                                    painter = painterResource(item.imageRes),
                                    contentDescription = item.label,
                                    tint = if (selectedIndex == index) Color(0xFF19D44B) else Color.Gray,
                                    modifier = Modifier.size(28.dp)
                                )
                                Text(
                                    text = item.label,
                                    color = if (selectedIndex == index) Color(0xFF19D44B) else Color.Gray,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
