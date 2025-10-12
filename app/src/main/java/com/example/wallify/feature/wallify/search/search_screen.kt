package com.example.wallify.feature.wallify.search
import android.net.wifi.aware.ParcelablePeerHandle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wallify.R
import com.example.wallify.common.widgets.shimmer.FastCircularProgressIndicator
import com.example.wallify.feature.wallify.search.controller.SearchViewModel

@Composable
fun SearchScreen(
    recentSearches: List<String> = listOf("abc", "popular", "bokeh", "Phone wallpaper"),
) {
    val viewmodel : SearchViewModel = hiltViewModel()
    val searchPhotos by viewmodel.searchPhotos.collectAsState()
    val  isLoading = viewmodel.isLoading
    var searchQuery by remember { mutableStateOf("") }
    LaunchedEffect(searchQuery) {
        viewmodel.searchPhotos(searchQuery)
    }
    Scaffold { innerPadding ->
        if (isLoading){
            FastCircularProgressIndicator()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(innerPadding)
        ) {
            // Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFFF5F5F5))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
                Spacer(Modifier.width(8.dp))
                BasicTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .weight(1f),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        if (searchQuery.isEmpty()) {
                            Text("Search photos and illustrations", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear", tint = Color.Gray)
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Recent Searches
            Text("Recent Searches · ", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextButton(onClick = { /* Clear recent searches */ }) {
                    Text("Clear", color = Color(0xFFED2C5C))
                }
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(recentSearches) { it ->
                    OutlinedButton(
                        onClick = {  },
                        shape = RoundedCornerShape(12.dp)
                    ) { Text(it) }
                }
            }
            // list Photos
            if(searchPhotos.isNotEmpty()){
                LazyColumn {
                    items(searchPhotos) { photo ->
                    }
                }
            }
        }
    }
}

