import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallify.common.widgets.appbar.TAppBar
import com.example.wallify.feature.wallify.collections.collectionPhotos.CollectionPhotosViewModel
import com.example.wallify.feature.wallify.collections.collectionPhotos.widgets.BoxHeader
import com.example.wallify.feature.wallify.collections.collectionPhotos.widgets.PhotosCollections
import com.example.wallify.feature.wallify.home.model.Urls
import com.example.wallify.feature.wallify.photos.product_set_wallpaper.FastCircularProgress
import com.example.wallify.feature.wallify.photos.viewmodel.PhotosViewModel

@Composable
fun CollectionPhotosScreen(
    id: String?,
    navController: NavController
) {
    val viewModel: CollectionPhotosViewModel = hiltViewModel()
    val photosViewModel: PhotosViewModel = hiltViewModel()
    val collection by viewModel.collections.collectAsState()
    val collectionPhotos by viewModel.photosByCollections.collectAsState()
    var showBoxHeader by rememberSaveable { mutableStateOf(true) }
    var showBottomSheetDownload by rememberSaveable { mutableStateOf(false) }
    var url by remember { mutableStateOf(Urls.empty()) }
    val isLoading = viewModel.isLoading
    val isLoadingDownload = photosViewModel.isLoading
    LaunchedEffect(id) {
        id!!.let {
            viewModel.fetchCollectionById(id)
        }
    }
    Scaffold(
        topBar = {
            TAppBar(
                showTopBar = !showBoxHeader,
                title = {
                    Text(
                        text = collection.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                showBackArrow = true,
                leadingOnPressed = {
                    navController.popBackStack()
                }
            )
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            BoxHeader(
                collection = collection,
                showBoxHeader = showBoxHeader
            )
            if (isLoading) {
                FastCircularProgress()
            } else {
                PhotosCollections(
                    photos = collectionPhotos,
                    onScroll = {
                        showBoxHeader = it
                    },
                    onClickDownloads = {it ->
                        url = it
                        showBottomSheetDownload = true
                    }
                )
            }
        }
        if( isLoadingDownload ){
            FastCircularProgressDownload()
        }
        if (showBottomSheetDownload){
            BottomSheetSetAnDownload(
                title = "Download photo",
                onDismiss = {
                    showBottomSheetDownload = false
                },
                onRawClick = {
                    showBottomSheetDownload = false
                    photosViewModel.downloadImageWithNotification(
                        url = url.raw,
                        successMsg = "Image Downloaded Successfully",
                        errorMsg = "Error Downloading Image"
                    )
                },
                onRegularClick = {
                    showBottomSheetDownload = false
                    photosViewModel.downloadImageWithNotification(
                        url = url.regular,
                        successMsg = "Image Downloaded Successfully",
                        errorMsg = "Error Downloading Image"
                    )
                },
                onFullClick = {
                    showBottomSheetDownload = false
                    photosViewModel.downloadImageWithNotification(
                        url = url.full,
                        successMsg = "Image Downloaded Successfully",
                        errorMsg = "Error Downloading Image"
                    )
                }
            )
        }
    }
}
@Composable
fun FastCircularProgressDownload() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent.copy(0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            CircularProgressIndicator()
            Spacer(Modifier.height(4.dp))
            Text(
                text = "downloading...",
                fontWeight = FontWeight.Bold,
                color = ProgressIndicatorDefaults.circularColor
            )
        }
    }
}