package com.example.wallify.feature.personalization.setting

import WAppBarCenter
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallify.R
import com.example.wallify.feature.personalization.setting.controller.SettingViewModel
import com.example.wallify.feature.personalization.setting.widgets.SettingItem
import com.example.wallify.feature.personalization.setting.widgets.SignInGoogle
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen
import com.example.core_viewmodel.controller.authentiacations.AuthViewModel
import com.example.wallify.feature.personalization.setting.widgets.BottomSheetImageBackGround

@Composable
fun SettingScreen(
    navController: NavController,
) {
    val userModel: AuthViewModel = hiltViewModel()
    val user by userModel.user.collectAsState()
    val viewModel: SettingViewModel = hiltViewModel()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var selectedUri by remember { mutableStateOf<Uri?>(null) }
    var showConfirmTick by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedUri = uri
        showConfirmTick = uri != null
    }
    Scaffold(
        topBar = {
            WAppBarCenter(
                title = {
                    Text(
                        text = "Setting"
                    )
                },
                showBackArrow = true,
                leadingOnPressed = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // profile
            item {
                SignInGoogle(
                    selectedUri = selectedUri,
                    showConfirmTick,
                    onClick = {
                        showBottomSheet = !showBottomSheet
                    },
                    onClickUpLoad = {
                        viewModel.updateUrlBackGround(user.idToken, selectedUri)
                        showConfirmTick = false
                        selectedUri = null
                    }
                )
            }
            item {
                SettingItem(
                    title = "Wallify Pro",
                    subtitle = "Unlock all features",
                    imageItem = R.drawable.flash,
                    onClickItem = {
                    },
                )
            }
            // List function
            item {
                SettingItem(
                    title = "Notifications",
                    subtitle = "Manage notification settings",
                    imageItem = R.drawable.elements,
                    onClickItem = {
                    }
                )
            }
            item {
                SettingItem(
                    title = "My Uploads",
                    subtitle = "View and manage your uploads",
                    imageItem = R.drawable.elements_upload,
                    onClickItem = {
                    }
                )
            }
            item {
                SettingItem(
                    title = "Favorites",
                    subtitle = "View your favorite wallpapers",
                    imageItem = R.drawable.heart,
                    onClickItem = {
                        navController.navigate(Screen.Favorite.route)
                    }
                )
            }
            item {
                SettingItem(
                    title = "Download History",
                    subtitle = "View your download history",
                    imageItem = R.drawable.elements_download,
                    onClickItem = {
                    }
                )
            }
            item {
                SettingItem(
                    title = "Sync Favorites",
                    subtitle = "Sync your favorites across devices",
                    imageItem = R.drawable.sync_38dp,
                    onClickItem = {
                    }
                )
            }
            item {
                Divider(
                    modifier = Modifier.padding(vertical = TSizes.md, horizontal = TSizes.lg)
                )
            }
            item {
                SettingItem(
                    title = "Follow Us",
                    subtitle = "Stay connected with us",
                    imageItem = R.drawable.view_object_track_38dp,
                    onClickItem = {
                    }
                )
            }
            item {
                SettingItem(
                    title = "Rate Wallify",
                    subtitle = "Leave a review on the Play Store",
                    imageItem = R.drawable.star,
                    onClickItem = {
                    }
                )
            }
            item {
                SettingItem(
                    title = "Help & Support",
                    subtitle = "Get assistance and support",
                    imageItem = R.drawable.help_38dp,
                    onClickItem = {
                    }
                )
            }
            item {
                SettingItem(
                    title = "Privacy Policy",
                    subtitle = "Read our privacy policy",
                    imageItem = R.drawable.privacy_tip_38dp,
                    onClickItem = {
                    }
                )
            }
            item {
                SettingItem(
                    title = "Send Feedback",
                    subtitle = "Contribute your ideas to the developer",
                    imageItem = R.drawable.outgoing_mail_56dp,
                    onClickItem = {
                        viewModel.sendFeedback(user.email)
                    }
                )
            }
        }
        if (showBottomSheet){
            BottomSheetImageBackGround(
                onClick = { it ->
                    showBottomSheet = it
                },
                upLoad = {
                    launcher.launch("image/*")
                    showBottomSheet = false
                }
            )
        }
    }
}