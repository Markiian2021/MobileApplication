package com.example.spacexsenkiv.ui.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.example.spacexsenkiv.R
import com.example.spacexsenkiv.data.entity.response.launchesResponse.LaunchResponse
import com.squareup.picasso.Picasso
import org.koin.androidx.compose.getViewModel

@Composable
fun LaunchList(
    onLaunchNews: () -> Unit,
    onLaunchItem: (LaunchResponse) -> Unit,
    viewModel: LaunchViewModel = getViewModel()
) {
    val launchResponseState = viewModel.launchResponseStateFlow.collectAsState()
    var showShortDetailDialog by remember { mutableStateOf(false) }
    var selectedLaunch by remember { mutableStateOf<LaunchResponse?>(null) }

    var showSearchDialog by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var filteredLaunches by remember { mutableStateOf(listOf<LaunchResponse>()) }

    LaunchedEffect(searchQuery) {
        filteredLaunches = viewModel.searchLaunch(searchQuery)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xF2CCCCCC))
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.iconspace),
                contentDescription = null,
                modifier = Modifier.size(250.dp, 30.dp),
                contentScale = ContentScale.Crop
            )
        }

        (filteredLaunches.ifEmpty { launchResponseState.value })?.forEach { launch ->
        Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .clickable { onLaunchItem(launch) }
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                        .padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        launch.links.patch?.small?.let { patchUrl ->
                            AndroidView(
                                factory = { ImageView(it).apply { scaleType = ImageView.ScaleType.CENTER_CROP } },
                                update = { Picasso.get().load(patchUrl).into(it) },
                                modifier = Modifier.size(50.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = launch.name,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val successIcon = when (launch.success) {
                                    true -> R.drawable.icontrue
                                    false -> R.drawable.iconfalse
                                    else -> R.drawable.iconunknown
                                }

                                Image(
                                    painter = painterResource(id = successIcon),
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = launch.success?.let { if (it) "Success" else "Failed" } ?: "Unknown",
                                    fontSize = 15.sp,
                                    color = Color(0xFF656565)
                                )
                            }
                        }

                        Image(
                            painter = painterResource(id = R.drawable.iconinfo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    selectedLaunch = launch
                                    showShortDetailDialog = true
                                },
                            contentScale = ContentScale.Crop,
                            colorFilter = ColorFilter.tint(Color(0xFFFF971C))
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Image(
                            painter = painterResource(id = R.drawable.iconfalse),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    viewModel.deleteLaunch(launch.id)
                                },
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
    if (showShortDetailDialog && selectedLaunch != null) {
        Dialog(
            onDismissRequest = { showShortDetailDialog = false }
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFFC4DCFF), shape = RoundedCornerShape(20.dp))
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.iconfalse),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    showShortDetailDialog = false
                                },
                            contentScale = ContentScale.Crop,
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        selectedLaunch!!.links.patch?.small?.let { patchUrl ->
                            AndroidView(
                                factory = {
                                    ImageView(it).apply {
                                        scaleType = ImageView.ScaleType.CENTER_CROP
                                    }
                                },
                                update = { Picasso.get().load(patchUrl).into(it) },
                                modifier = Modifier.size(70.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = selectedLaunch!!.name,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Date:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF699DFF)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = selectedLaunch!!.date_local,
                            fontSize = 16.sp,
                            color = Color(0xFF656565)
                        )

                    }
                    Spacer(modifier = Modifier.height(5.dp))

                    val core = selectedLaunch!!.cores.firstOrNull()
                    if (core != null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Gridfins:",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (core.gridfins == true) "Yes" else "No",
                                fontSize = 16.sp,
                                color = Color(0xFF656565)
                            )
                            Image(
                                painter = painterResource(id = if (core.gridfins == true) R.drawable.icontrue else R.drawable.iconfalse),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Legs:",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = when (core.legs) {
                                    true -> "Yes"
                                    false -> "No"
                                    else -> "Unknown"
                                },
                                fontSize = 16.sp,
                                color = Color(0xFF656565)
                            )
                            Image(
                                painter = painterResource(
                                    id = when (core.legs) {
                                        true -> R.drawable.icontrue
                                        false -> R.drawable.iconfalse
                                        else -> R.drawable.iconunknown
                                    }
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Reused:",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (core.reused == true) "Yes" else "No",
                                fontSize = 16.sp,
                                color = Color(0xFF656565)
                            )
                            Image(
                                painter = painterResource(id = if (core.reused == true) R.drawable.icontrue else R.drawable.iconfalse),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Landing:",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = when (core.landing_success) {
                                    true -> "Yes"
                                    false -> "No"
                                    else -> "Unknown"
                                },
                                fontSize = 16.sp,
                                color = Color(0xFF656565)
                            )
                            Image(
                                painter = painterResource(
                                    id = when (core.landing_success) {
                                        true -> R.drawable.icontrue
                                        false -> R.drawable.iconfalse
                                        else -> R.drawable.iconunknown
                                    }
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
    }
    if (showSearchDialog) {
        Dialog(onDismissRequest = { showSearchDialog = false }) {
            Box(
                modifier = Modifier
                    .background(Color(0xFFC4DCFF), shape = RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.iconfalse),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    showSearchDialog = false
                                },
                            contentScale = ContentScale.Crop,
                        )
                    }
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = { Text("Search") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                showSearchDialog = false
                            },
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text("Search")
                        }
                    }
                }
            }
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
                    .background(Color(0xFF343434), shape = CircleShape)
                    .padding(10.dp)
                    .clickable { showSearchDialog = true },
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Color.White)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.iconnews),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFF343434), shape = CircleShape)
                    .padding(10.dp)
                    .clickable { onLaunchNews() },
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Color.White)
            )

        }
    }
}


