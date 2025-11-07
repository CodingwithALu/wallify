package com.example.wallify.feature.personalization.setting.widgets

import TCircularImage
import com.example.wallify.common.widgets.images.TRoundedImage
import WTitleItems
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core_viewmodel.controller.authentiacations.AuthViewModel
import com.example.wallify.R
import com.example.wallify.common.widgets.custom_shapes.container.TRoundedContainer
import com.example.wallify.common.widgets.custom_shapes.container.TSearchContainer
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.constants.TextString
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN
import com.google.android.gms.common.api.ApiException

@Composable
fun SignInGoogle(
    onClick : () -> Unit
) {
    val viewModel: AuthViewModel = hiltViewModel()
    val googleLoginInfo by viewModel.googleLoginInfo.collectAsState()
    val user by viewModel.user.collectAsState()
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken
            val accessToken = account.serverAuthCode
            val avatarUrl = account.photoUrl?.toString()
            val userName = account.displayName
            val email = account.email
            viewModel.loginWithGoogle(
                idToken = idToken ?: "",
                accessToken = accessToken ?: "",
                userName = userName ?: "",
                email = email ?: "",
                avatarUrl = avatarUrl ?: ""
            )
        } catch (e: Exception) {
            // Xử lý lỗi
            Log.d("SignInGoogle", "Access Token: ${e.message}")
        }
    }
    TRoundedContainer(
        modifier = Modifier
            .aspectRatio(1.6f)
            .padding(horizontal = TSizes.sm),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                TRoundedImage(
                    imageUrl = user?.urlBackground ?: "https://wallify-s3.s3.us-east-1.amazonaws.com/image/user/VKt78Yt9MpONW52RAn3WIbGtG3b2/dc70edf5-6811-42ff-898f-287573182e85.jpg",
                    isNetworkImage = true,
                    fit = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                IconButton(
                    onClick = { onClick() },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(TSizes.defaultSpace)
                        .size(28.dp)
                        .background(Color.White, shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.photo_camera_56dp),
                        contentDescription = "Change Avatar",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(vertical = TSizes.defaultSpace)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TCircularImage(
                        image = user?.urlProfile ?: TextString.urlDefaultAvatar,
                        isNetworkImage = true,
                        fit = ContentScale.Crop,
                        modifier = Modifier
                            .height(56.dp)
                            .width(56.dp)
                    )
                    WTitleItems(
                        title = if (googleLoginInfo.isLoggedIn) user?.fullName ?: "" else "Save & Sync Your Favorites",
                        subTitle = if (googleLoginInfo.isLoggedIn) user?. email ?: "" else "Sign in below to get started"
                    )
                    Spacer(modifier = Modifier.height(TSizes.lg))
                    TSearchContainer(
                        onTap = {
                            if (googleLoginInfo.isLoggedIn) {
                                viewModel.logout()
                            } else {
                                val gso = GoogleSignInOptions.Builder(DEFAULT_SIGN_IN)
                                    .requestIdToken(context.getString(R.string.default_web_client_id))
                                    .requestEmail()
                                    .requestServerAuthCode(context.getString(R.string.default_web_client_id))
                                    .build()
                                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                                val signInIntent = googleSignInClient.signInIntent
                                launcher.launch(signInIntent)
                                googleSignInClient.revokeAccess()
                            }
                        },
                        modifier = Modifier
                            .height(54.dp),
                        icon = {
                            if (!googleLoginInfo.isLoggedIn) {
                                Image(
                                    painter = painterResource(R.drawable.google_icon),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(32.dp)
                                        .height(32.dp)
                                )
                            }
                        },
                        text = if (googleLoginInfo.isLoggedIn) "Sign out" else "Sign in with Google"
                    )
                }
            }
        }
    )
}