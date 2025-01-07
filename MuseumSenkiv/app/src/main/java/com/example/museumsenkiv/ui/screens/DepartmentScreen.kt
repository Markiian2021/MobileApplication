package com.example.museumsenkiv.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.museumsenkiv.R

@Composable
fun DepartmentScreen(
    viewModel: MuseumViewModel,
    onDepartmentClick: (String) -> Unit,
    navController: NavHostController,
    themeViewModel: ThemeViewModel
) {
    var showSettingsDialog by remember { mutableStateOf(false) }
    val departments = viewModel.departmentsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchDepartments()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        items(departments.value.chunked(2)) { departmentPair ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                departmentPair.forEach { department ->
                    val colors = listOf(
                        Color(0xFFB28D00),
                        Color(0xFFBDB76B),
                        Color(0xFF8B4513),
                        Color(0xFF9ACD32),
                        Color(0xFFDAA520),
                        Color(0xFF800000),
                        Color(0xFFB0C4DE),
                        Color(0xFF722D2D),
                    )
                    val randomColor = colors.random()

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp)
                            .clickable { onDepartmentClick(department.departmentId) },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(randomColor)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.imgvintage),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(150.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                        Text(
                            text = department.displayName,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
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
