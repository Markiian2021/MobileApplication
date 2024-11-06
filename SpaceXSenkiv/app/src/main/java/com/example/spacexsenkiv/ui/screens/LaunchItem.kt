package com.example.spacexsenkiv.ui.screens

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.spacexsenkiv.R
import com.squareup.picasso.Picasso
import org.koin.androidx.compose.getViewModel

@Composable
fun LaunchItem(
    launchId: String?,
    viewModel: LaunchViewModel = getViewModel()
) {
    if (launchId != null) {
        val launchResponseState = viewModel.launchResponseStateFlow.collectAsState().value
        val launch = launchResponseState?.find { it.id == launchId }

        if (launch != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xF2CCCCCC))
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(25.dp))
                        .padding(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AndroidView(
                            factory = { ImageView(it).apply { scaleType = ImageView.ScaleType.CENTER_CROP } },
                            update = { Picasso.get().load(launch.links.patch?.small).into(it) },
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = launch.name,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF699DFF)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                val successIcon = when (launch.success) {
                                    true -> R.drawable.icontrue
                                    false -> R.drawable.iconfalse
                                    else -> R.drawable.iconunknown
                                }
                                Image(
                                    painter = painterResource(id = successIcon),
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = launch.success?.let { if (it) "Success" else "Failed" } ?: "Unknown",
                                    fontSize = 16.sp,
                                    color = Color(0xFF656565)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Date",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF699DFF)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = launch.date_local,
                            fontSize = 16.sp,
                            color = Color(0xFF656565)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                val imageUrls = launch.links.flickr?.original?.take(4)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    imageUrls?.chunked(2)?.forEach { rowImages ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            rowImages.forEach { imageUrl ->
                                Card(
                                    shape = RoundedCornerShape(20.dp),
                                    modifier = Modifier
                                        .size(120.dp)
                                        .weight(1f)
                                ) {
                                    AndroidView(
                                        factory = { ImageView(it).apply { scaleType = ImageView.ScaleType.CENTER_CROP } },
                                        update = { Picasso.get().load(imageUrl).into(it) },
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                            .padding(10.dp)
                    ) {
                        launch.cores.forEach { core ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                ) {
                                    Text(
                                        text = "Gridfins:",
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF699DFF)
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = if (core.gridfins == true) "Yes" else "No",
                                            fontSize = 16.sp,
                                            color = Color(0xFF656565),
                                            modifier = Modifier.padding(end = 4.dp)
                                        )
                                        Image(
                                            painter = painterResource(id = if (core.gridfins == true) R.drawable.icontrue else R.drawable.iconfalse),
                                            contentDescription = null,
                                            modifier = Modifier.size(15.dp),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }

                                VerticalDivider(
                                    modifier = Modifier.height(70.dp),
                                    thickness = 2.dp,
                                    color = Color(0xFF699DFF)
                                )

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Legs:",
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF699DFF)
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = when (core.legs) {
                                                true -> "Yes"
                                                false -> "No"
                                                else -> "Unkn"
                                            },
                                            fontSize = 16.sp,
                                            color = Color(0xFF656565),
                                            modifier = Modifier.padding(end = 4.dp)
                                        )
                                        Image(
                                            painter = painterResource(id = when (core.legs) {
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

                                VerticalDivider(
                                    modifier = Modifier.height(70.dp),
                                    thickness = 2.dp,
                                    color = Color(0xFF699DFF)
                                )

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Reused:",
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF699DFF)
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = when (core.reused) {
                                                true -> "Yes"
                                                false -> "No"
                                                else -> "Unkn"
                                            },
                                            fontSize = 16.sp,
                                            color = Color(0xFF656565),
                                            modifier = Modifier.padding(end = 4.dp)
                                        )
                                        Image(
                                            painter = painterResource(id = if (core.reused == true) R.drawable.icontrue else R.drawable.iconfalse),
                                            contentDescription = null,
                                            modifier = Modifier.size(15.dp),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }

                                VerticalDivider(
                                    modifier = Modifier.height(70.dp),
                                    thickness = 2.dp,
                                    color = Color(0xFF699DFF)
                                )

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Landing:",
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF699DFF)
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = when (core.landing_success) {
                                                true -> "Yes"
                                                false -> "No"
                                                else -> "Unkn"
                                            },
                                            fontSize = 16.sp,
                                            color = Color(0xFF656565),
                                            modifier = Modifier.padding(end = 4.dp)
                                        )
                                        Image(
                                            painter = painterResource(id = if (core.landing_success == true) R.drawable.icontrue else R.drawable.iconfalse),
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


                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Details",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF699DFF)
                    )
                    Text(
                        text = launch.details ?: "No details",
                        fontSize = 16.sp,
                        color = Color(0xFF656565)
                    )
                }
            }
        }
    }
}
