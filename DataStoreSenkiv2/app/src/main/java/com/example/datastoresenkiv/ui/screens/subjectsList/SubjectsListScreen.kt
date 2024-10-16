package com.example.datastoresenkiv.ui.screens.subjectsList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datastoresenkiv.R
import com.example.datastoresenkiv.data.db.DatabaseStorage
import com.example.datastoresenkiv.data.entity.SubjectEntity

@Composable
fun SubjectsListScreen(
    onDetailScreen: (Int) -> Unit,
) {
    val context = LocalContext.current

    val db = DatabaseStorage.getDatabase(context)
    val subjectsListState = remember { mutableStateOf<List<SubjectEntity>>(emptyList()) }


    LaunchedEffect(Unit) {
        subjectsListState.value = db.subjectsDao.getAllSubjects()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD0D0B6))
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.White, RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)),

        ) {
            Text(
                text = "Розклад / ТР-41 ",
                fontSize = 25.sp,
                modifier = Modifier.padding(16.dp),
                color = Color(0xFF2E8FE8)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(subjectsListState.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onDetailScreen(it.id) }
                            .padding(5.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White, RoundedCornerShape(20.dp))
                                .padding(16.dp)
                                .height(80.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painterResource(R.drawable.icon), null,
                                Modifier
                                    .size(48.dp)
                                    .background(Color(0xFF2E8FE8), RoundedCornerShape(14.dp))
                                    .padding(7.dp, 0.dp, 7.dp, 0.dp), Color.White
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(16.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                Text(
                                    text = it.title,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                                Text(
                                    modifier = Modifier
                                        .background(
                                            color = Color(0xFFFBE6D3),
                                            RoundedCornerShape(50.dp)
                                        )
                                        .padding(5.dp, 0.dp, 5.dp, 0.dp),
                                    text = "4 курс",
                                    fontSize = 14.sp,
                                    color = Color(0xFF8B4513),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}