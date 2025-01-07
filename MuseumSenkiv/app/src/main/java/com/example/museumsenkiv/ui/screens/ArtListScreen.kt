package com.example.museumsenkiv.ui.screens

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.example.museumsenkiv.R
import com.squareup.picasso.Picasso


@Composable
fun ArtListScreen(
    viewModel: MuseumViewModel,
    departmentId: Int,
    onArtClick: (Int) -> Unit,

    themeViewModel: ThemeViewModel
) {
    val artList by viewModel.artListState.collectAsState(initial = emptyList())
    var showSearchDialog by remember { mutableStateOf(false) }
    var showSettingsDialog by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(departmentId) {
        viewModel.fetchArtList(query = searchQuery, departmentId = departmentId)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(artList) { art ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.onSecondary)
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { onArtClick(art.objectID) })
                ) {
                    val imageUrl = art.primaryImage.takeIf { !it.isNullOrEmpty() }

                    AndroidView(
                        factory = {
                            ImageView(it).apply {
                                scaleType = ImageView.ScaleType.CENTER_CROP
                            }
                        },
                        update = { imageView ->
                            if (imageUrl.isNullOrEmpty()) {
                                imageView.setImageResource(R.drawable.nophoto)
                            } else {
                                Picasso.get().load(imageUrl).into(imageView)
                            }
                        },
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = art.title ?: "Untitled",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = art.artistDisplayName ?: "Unknown Artist",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    if (showSearchDialog) {
        Dialog(onDismissRequest = { showSearchDialog = false }) {
            SearchDialog(
                searchQuery = searchQuery,
                onQueryChange = { searchQuery = it },
                onDismiss = { showSearchDialog = false },
                onSearch = {
                    showSearchDialog = false
                    viewModel.fetchArtList(query = searchQuery, departmentId = departmentId)
                }
            )
        }
    }

    if (showSettingsDialog) {
        Dialog(onDismissRequest = { showSettingsDialog = false }) {
            SettingsDialog(
                viewModel = themeViewModel,
                onDismiss = { showSettingsDialog = false }
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.iconsearch),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .padding(10.dp)
                    .clickable { showSearchDialog = true },
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Image(
                painter = painterResource(id = R.drawable.iconsettings),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .padding(10.dp)
                    .clickable { showSettingsDialog = true },
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            )
        }
    }
}
