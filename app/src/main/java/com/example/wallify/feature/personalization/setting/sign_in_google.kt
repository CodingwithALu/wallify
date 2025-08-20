package com.example.wallify.feature.personalization.setting

import TCircularImage
import TRoundedImage
import WTitleItems
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_viewmodel.controller.authentiacations.LoginViewModel
import com.example.wallify.R
import com.example.wallify.common.widgets.custom_shapes.container.TRoundedContainer
import com.example.wallify.common.widgets.custom_shapes.container.TSearchContainer
import com.example.wallify.utlis.constants.TSizes
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.*
import com.google.android.gms.common.api.ApiException

@Composable
fun SignInGoogle(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val gso = Builder(DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .requestServerAuthCode(context.getString(R.string.default_web_client_id))
        .build()
    val googleSignInClient = GoogleSignIn.getClient(context, gso)

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
                context = context,
                idToken = idToken ?: "",
                accessToken = accessToken ?: "",
                userName = userName ?: "",
                email = email ?: "",
                avatarUrl = avatarUrl ?: ""
            ){

            }
        } catch (e: Exception) {
            // Xử lý lỗi
            Log.d("SignInGoogle", "Access Token: ${e.message}")
        }
    }
    val googleLoginInfo by viewModel.googleLoginInfo.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.restoreGoogleLoginInfo(context)
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
                    drawableResId = R.drawable.wallhaven_o53v3m,
                    fit = ContentScale.Crop
                )
                if (googleLoginInfo.isLoggedIn){
                    TCircularImage(
                        image = viewModel.userAvatars,
                        isNetworkImage = true,
                        fit = ContentScale.Crop,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(vertical = TSizes.sm)
                    )
                    WTitleItems(
                        title = viewModel.userNames,
                        subTitle = viewModel.emails
                    )
                    TSearchContainer(
                        onTap = {
                            viewModel.logout(context){}
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .height(60.dp)
                            .padding(bottom = TSizes.sm),
                        text = "Log out"
                    )
                } else {
                    TCircularImage(
                        drawableResId = R.drawable.avater_profile,
                        image = "",
                        fit = ContentScale.Crop,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(vertical = TSizes.sm)
                    )
                    WTitleItems(
                        title = "Save & Sync Your Favorites",
                        subTitle = "Sign in below to get started"
                    )
                    TSearchContainer(
                        onTap = {
                            val signInIntent = googleSignInClient.signInIntent
                            launcher.launch(signInIntent)
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .height(60.dp)
                            .padding(bottom = TSizes.sm),
                        icon = {
                            Image(
                                painter = painterResource(R.drawable.google_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(32.dp)
                                    .height(32.dp)
                            )
                        },
                        text = "Sign in with Google"
                    )
                }
            }
        }
    )
}