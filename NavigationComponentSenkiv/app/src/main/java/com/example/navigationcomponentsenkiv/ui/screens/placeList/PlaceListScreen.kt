package com.example.navigationcomponentsenkiv.ui.screens.placeList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigationcomponentsenkiv.data.ItemsData


@Composable
fun PlaceListScreen(
    onDetailsScreen: (Int) -> Unit,
) {
    val itemsList = ItemsData.itemsList

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFADDFFF))
    ) {
        items(itemsList) { item ->
            Column(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shadow(8.dp, shape = RoundedCornerShape(30.dp))
                        .clip(RoundedCornerShape(30.dp))
                ) {
                    Image(
                        painter = painterResource(id = item.photo),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                onDetailsScreen(item.id)
                            },
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFFFFFFC5),
                            )
                            .align(Alignment.BottomCenter)
                    ) {
                        Text(
                            text = item.title,
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    onDetailsScreen(item.id)
                                },
                            color = Color(0xFF282828),
                            fontSize = 17.sp,
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PlaceListScreenPreview() {
    PlaceListScreen {  }
}

