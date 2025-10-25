package com.example.wallify.feature.wallify.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallify.common.widgets.products.WProductCardVertical
import com.example.wallify.common.widgets.shimmer.FastCircularProgressIndicator
import com.example.wallify.feature.wallify.search.controller.SearchViewModel
import com.example.wallify.utlis.constants.TSizes
import com.example.wallify.utlis.route.Screen

@Composable
fun SearchScreen(
    navController: NavController
) {
    val viewmodel: SearchViewModel = hiltViewModel()
    val searchPhotos by viewmodel.searchPhotos.collectAsState()
    val recentSearches by viewmodel.searchHistory.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    // get keyboard controller and focus manager
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(searchQuery) {
        viewmodel.searchPhotos(searchQuery)
    }
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
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            // khi nhấn Enter / Search trên IME: lưu lịch sử, gọi tìm kiếm, ẩn bàn phím và clear focus
                            if (searchQuery.isNotBlank()) {
                                // ViewModel methods already launch their own coroutines
                                viewmodel.saveSearchQuery(searchQuery)
                                keyboardController?.hide()
                                focusManager.clearFocus()
                            }
                        }
                    ),
                    decorationBox = { innerTextField ->
                        if (searchQuery.isEmpty()) {
                            Text("Search photos and illustrations", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )
                if (searchQuery.isNotEmpty()) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "Clear",
                        tint = Color.Gray,
                        modifier = Modifier
                            .clickable(
                                onClick = { searchQuery = "" }
                            ))
                }
            }
            Spacer(Modifier.height(16.dp))
            // list Photos
            if (searchPhotos.isNotEmpty()) {
                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    items(searchPhotos) { photo ->
                        WProductCardVertical(
                            item = photo,
                            onclick = {
                                navController.navigate("${Screen.PhotosList.route}/${photo.id}")
                            }
                        )
                    }
                }
            } else {
                if (recentSearches.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("History Searches ", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(
                            "Clear All",
                            fontSize = 14.sp,
                            color = Color.Blue,
                            modifier = Modifier
                                .clickable(
                                    onClick = {
                                        viewmodel.clearSearchResults()
                                    }
                                )
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(recentSearches) { it ->
                        SearchItem(
                            it,
                            onClickSave = {
                                viewmodel.searchPhotos(it)
                            },
                            onClickRemove = {
                                viewmodel.removeSearchQuery(it)
                            })
                    }
                }
            }
        }
    }
}

@Composable
fun SearchItem(
    it: String,
    onClickSave: () -> Unit,
    onClickRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(
                onClick = {
                    onClickSave()
                }
            )
            .fillMaxWidth()
            .padding(TSizes.xs),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(it)
        Icon(
            Icons.Default.Clear,
            contentDescription = "Remove",
            tint = Color.Gray,
            modifier = Modifier
                .clickable(
                    onClick = {
                        onClickRemove() // invoke the lambda
                    }
                )
        )
    }
}
