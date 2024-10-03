package com.example.navigationcomponentsenkiv.ui.screens.placeDetails


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigationcomponentsenkiv.data.ItemsData

@Composable
fun PlaceDetailsScreen(
    id: Int
) {
    val itemDetailState = remember {
        mutableStateOf(
            ItemsData.itemsList.first { it.id == id }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFADDFFF))
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(8.dp, shape = RoundedCornerShape(0.dp, 0.dp, 50.dp, 50.dp))
                    .clip(RoundedCornerShape(0.dp, 0.dp, 50.dp, 50.dp))
            ) {
                Image(
                    painter = painterResource(id = itemDetailState.value.photo),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .height(350.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = itemDetailState.value.title,
                modifier = Modifier
                    .padding(10.dp),
                color = Color(0xFF282828),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = itemDetailState.value.description,
                modifier = Modifier
                    .padding(10.dp),
                color = Color(0xFF282828),
                fontSize = 17.sp,
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PlaceDetailsScreenPreview() {
    PlaceDetailsScreen(
        id = 3
    )
}
