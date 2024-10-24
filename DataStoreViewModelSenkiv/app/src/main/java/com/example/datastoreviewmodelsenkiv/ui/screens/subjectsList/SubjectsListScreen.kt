package com.example.datastoreviewmodelsenkiv.ui.screens.subjectsList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datastoreviewmodelsenkiv.R
import org.koin.androidx.compose.getViewModel

@Composable
fun SubjectsListScreen(
    viewModel: SubjectsListViewModel = getViewModel(),
    onDetailScreen: (Int) -> Unit,
) {
    val subjectsListState = viewModel.subjectListStateFlow.collectAsState()
    var newSubject by remember { mutableStateOf("") }
    var showAddSubjectField by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD0D0B6))
    ) {
        Column(
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
                items(subjectsListState.value) { subject ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onDetailScreen(subject.id) }
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
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = subject.title,
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

                            Image(
                                painter = painterResource(id = R.drawable.iconfalse),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable {
                                        viewModel.deleteSubject(subject)
                                    },
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }

    }
    if (showAddSubjectField) {
        Box(

            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(color = Color(0xCC2E8FE9), RoundedCornerShape(14.dp))
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFFFBE6D3), RoundedCornerShape(14.dp, 14.dp, 0.dp, 0.dp))
                ){
                    Text(
                        text = "Предмет",
                        fontSize = 25.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(18.dp),
                        color = Color(0xFF8B4513)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text(
                        text = "Назва нового предмета:",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    OutlinedTextField(
                        value = newSubject,
                        onValueChange = { newSubject = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(BorderStroke(2.dp, Color.White),
                                shape = RoundedCornerShape(20.dp)
                            ),
                        shape = RoundedCornerShape(20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Column (
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Button(
                        onClick = {
                            if (newSubject.isNotBlank()) {
                                viewModel.addSubject(newSubject)
                                newSubject = ""
                                showAddSubjectField = false
                            }
                        },
                        modifier = Modifier
                            .align(alignment = Alignment.End)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(0.dp, 0.dp, 14.dp, 14.dp)
                    ) {
                        Text("OK")
                    }
                }

            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Button(
            onClick = {
                showAddSubjectField = true
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Image(
                painterResource(id = R.drawable.addplus),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}
