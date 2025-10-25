import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigator
import com.example.wallify.feature.wallify.collections.controller.CollectionViewModel
import com.example.wallify.feature.wallify.home.widgets.ImageMasonryList

@Composable
fun CollectionPhotosScreen(
    id : String?,
    navController: NavController
) {
    val viewModel: CollectionViewModel = hiltViewModel()
    val collectionPhotos by viewModel.photosByCollections.collectAsState()
    val isLoading = viewModel.isLoading
    LaunchedEffect(Unit) {
        viewModel.fetchPhotosByCollectionId(id!!)
    }
    Scaffold()
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ){
            ImageMasonryList(
                topics = collectionPhotos,
                navController = navController
            )
        }
    }
}