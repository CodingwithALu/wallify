package com.example.wallify.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.core_model.BottomNavItem
import com.example.wallify.utlis.route.Screen
import com.example.wallify.R

@Composable
fun NavigationMenu(
    onCenterClick: () -> Unit = {},
    navController: NavController
) {
    val navItems = listOf(
        BottomNavItem(Screen.Home.route, Icons.Default.Home),
        BottomNavItem(Screen.Streak.route, Icons.Default.ShoppingCart),
        BottomNavItem(Screen.Collection.route, Icons.Default.Favorite),
        BottomNavItem(Screen.Favorite.route, Icons.Default.Person)
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
    Box(
        modifier = Modifier.padding(bottom = 46.dp)
    ) {
        if (currentRoute !in Screen.routesToHideBottomBar) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Color(0xFF222222)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                navItems.forEachIndexed { index, item ->
                    if (index == 2) {
                        Spacer(Modifier.width(56.dp))
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                selectedIndex = index
                                Log.d("Navigation", "Click vào: ${item.label}, selectedIndex: $selectedIndex")
                                navController.navigate(item.label)
                            }
                    ) {
                        Icon(
                            imageVector = item.icon,
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
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-28).dp)
                    .size(64.dp)
                    .zIndex(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(
                            color = Color(0x3319D44B),
                            shape = CircleShape
                        )
                )
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .shadow(8.dp, CircleShape)
                        .background(Color(0xFF19D44B), CircleShape)
                        .clickable { onCenterClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.input_circle),
                        contentDescription = "Center Action",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}
