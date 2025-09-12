package com.example.wallify.feature.authentication.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wallify.R
import com.example.core_viewmodel.controller.onboarding.OnBoardingViewModel
import com.example.wallify.feature.authentication.screen.onboarding.widgets.OnBoardingDotNavigation
import com.example.wallify.feature.authentication.screen.onboarding.widgets.OnBoardingNextButton
import com.example.wallify.feature.authentication.screen.onboarding.widgets.OnBoardingPage
import com.example.wallify.feature.authentication.screen.onboarding.widgets.OnBoardingSkip
import com.example.wallify.ui.theme.onBackgroundLight
import com.example.wallify.utlis.constants.TSizes

@Composable
fun OnBoardingScreen(
    onSkip: () -> Unit = {},
    viewModel: OnBoardingViewModel
){
    val local = LocalContext.current
    val pageCount = 3
    val currentPage = viewModel.currentPageIndex
    Box(modifier = Modifier.fillMaxSize()) {
        when (currentPage) {
            0 -> OnBoardingPage(
                imageRes = R.drawable.onboarding_image1,
                title = stringResource(id = R.string.onboarding_title_1),
                subTitle = stringResource(id = R.string.onboarding_subtitle_1)
            )

            1 -> OnBoardingPage(
                imageRes = R.drawable.onboarding_image2,
                title = stringResource(id = R.string.onboarding_title_2),
                subTitle = stringResource(id = R.string.onboarding_subtitle_2)
            )

            2 -> OnBoardingPage(
                imageRes = R.drawable.onboarding_image3,
                title = stringResource(id = R.string.onboarding_title_3),
                subTitle = stringResource(id = R.string.onboarding_subtitle_3)
            )
        }
        OnBoardingSkip(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 48.dp, end = 24.dp),
            onSkip = {
                viewModel.skipPage(context = local){
                    onSkip()
                }

            }
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(paddingValues = PaddingValues(bottom = 68.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OnBoardingDotNavigation(
                totalDots = pageCount,
                selectedIndex = currentPage
            )
            Spacer(modifier = Modifier.height(TSizes.spaceBtwItems))
            // Next button
            OnBoardingNextButton(
                selectedIndex = currentPage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp)
                    .background(color = onBackgroundLight),
                onClick = {
                    viewModel.nextPage(context = local) { onSkip() }
                }
            )
        }
    }
}