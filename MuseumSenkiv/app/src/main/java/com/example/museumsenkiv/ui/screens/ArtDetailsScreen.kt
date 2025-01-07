package com.example.museumsenkiv.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.example.museumsenkiv.R
import com.squareup.picasso.Picasso


@Composable
fun ArtDetailsScreen(
    viewModel: MuseumViewModel,
    objectID: Int,
    themeViewModel: ThemeViewModel
) {
    val context = LocalContext.current
    val artDetails by viewModel.artDetailsState.collectAsState()
    var showSettingsDialog by remember { mutableStateOf(false) }
    LaunchedEffect(objectID) {
        viewModel.fetchArtDetails(objectID)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        artDetails?.let { details ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.onSecondary, shape = RoundedCornerShape(20.dp))
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = details.title ?: "Unknown Title",
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = details.department ?: "Unknown Department",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.iconlink),
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp)
                                .clickable {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(details.objectURL))
                                    context.startActivity(intent)
                                },
                            contentScale = ContentScale.Crop,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            val imageUrl = details.primaryImage.takeIf { it.isNotEmpty() }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(20.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    AndroidView(
                        factory = {
                            ImageView(it).apply {
                                scaleType = ImageView.ScaleType.CENTER_CROP
                            }
                        },
                        update = { imageView ->
                            if (imageUrl == null) {
                                imageView.setImageResource(R.drawable.nophoto)
                            } else {
                                Picasso.get().load(imageUrl).into(imageView)
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.onSecondary, shape = RoundedCornerShape(20.dp))
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = details.artists ?: "No title",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.onSecondary, shape = RoundedCornerShape(20.dp))
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Artist: ${details.artistDisplayName ?: "Unknown Artist"}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Artist Nationality: ${details.artistNationality ?: "Unknown Nationality"}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Date: ${details.objectDate ?: "Unknown Date"}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Medium: ${details.medium ?: "Unknown Medium"}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Accession Year: ${details.accessionYear ?: "Unknown Year"}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
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
