package com.example.wallify.feature.wallify.search

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchScreen(
    recentSearches: List<String> = listOf("abc", "popular", "bokeh", "Phone wallpaper"),
    trendingSearches: List<String> = listOf(
        "bamboo forest",
        "israel",
        "canon",
        "andorra",
        "art class"
    ),
    trendingTopics: List<Pair<String, String?>> = listOf(
        "People" to "https://images.unsplash.com/photo-1",
        "Animals" to "https://images.unsplash.com/photo-2",
        "Experimental" to null,
        "Wallpapers" to "https://images.unsplash.com/photo-3",
        "Film" to "https://images.unsplash.com/photo-4"
    ),
    trendingCollections: List<String> = listOf(
        "School Supplies by Fanette G", "Cheers Society", "Astrophotography", "Golden Glow", "blue."
    ),
    onSearch: (String) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    Scaffold { innerPadding ->
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
                        onClick = { onSearch(it) },
                        shape = RoundedCornerShape(12.dp)
                    ) { Text(it) }
                }
            }

            Spacer(Modifier.height(20.dp))
            Text("Trending Searches", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(trendingSearches) { it ->
                    OutlinedButton(
                        onClick = { onSearch(it) },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_up_float),
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(it)
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            Text("Trending Topics", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(trendingTopics) { it ->
                    val (topic, imageUrl) = it
                    OutlinedButton(
                        onClick = { onSearch(topic) },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.height(44.dp)
                    ) {
                        if (imageUrl != null) {
                            // You can use CoilImage or AsyncImage for real image loading
                            Surface(
                                shape = CircleShape,
                                color = Color.LightGray,
                                modifier = Modifier.size(24.dp)
                            ) {}
                            Spacer(Modifier.width(4.dp))
                        }
                        Text(topic)
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            Text("Trending Collections", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(trendingCollections) {
                    OutlinedButton(
                        onClick = { onSearch(it) },
                        shape = RoundedCornerShape(12.dp)
                    ) { Text(it) }
                }
            }
        }
    }
}

