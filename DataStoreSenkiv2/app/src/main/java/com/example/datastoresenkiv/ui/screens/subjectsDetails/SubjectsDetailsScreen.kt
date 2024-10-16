package com.example.datastoresenkiv.ui.screens.subjectsDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datastoresenkiv.R
import com.example.datastoresenkiv.data.db.DatabaseStorage
import com.example.datastoresenkiv.data.entity.SubjectEntity
import com.example.datastoresenkiv.data.entity.SubjectLabEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SubjectsDetailsScreen(id: Int) {
    val context = LocalContext.current
    val db = DatabaseStorage.getDatabase(context)

    val subjectState = remember { mutableStateOf<SubjectEntity?>(null) }
    val subjectLabsState = remember { mutableStateOf<List<SubjectLabEntity>>(emptyList()) }

    LaunchedEffect(Unit) {
        subjectState.value = db.subjectsDao.getSubjectById(id)
        subjectLabsState.value = db.subjectLabsDao.getSubjectLabsBySubjectId(id)
    }

    fun updateLab(lab: SubjectLabEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            db.subjectLabsDao.update(lab)
            subjectLabsState.value = db.subjectLabsDao.getSubjectLabsBySubjectId(id)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD0D0B6))
            .padding(16.dp)
    ) {
        item {
            Text(
                text = subjectState.value?.title ?: "",
                modifier = Modifier
                    .padding(10.dp),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E8FE8)
            )
        }
        items(subjectLabsState.value) { lab ->
            var title by remember { mutableStateOf(lab.title ?: "") }
            var description by remember { mutableStateOf(lab.description ?: "") }
            var comment by remember { mutableStateOf(lab.comment ?: "") }
            var inProgress by remember { mutableStateOf(lab.inProgress) }
            var isCompleted by remember { mutableStateOf(lab.isCompleted) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .background(Color(0x60989FA1), RoundedCornerShape(20.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                        .padding(10.dp)
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(id = R.drawable.graduationcap),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color(0xFF2E8FE8)),
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = "Лабораторна робота ${lab.labName}",
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(id = R.drawable.iconfalse),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                CoroutineScope(Dispatchers.IO).launch {
                                    db.subjectLabsDao.delete(lab)
                                    withContext(Dispatchers.Main) {
                                        subjectLabsState.value = db.subjectLabsDao.getSubjectLabsBySubjectId(id)
                                    }
                                }
                            },
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Назва:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E8FE8)
                    )
                    OutlinedTextField(
                        value = title,
                        onValueChange = { newTitle ->
                            title = newTitle
                            updateLab(lab.copy(title = newTitle))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(fontSize = 17.sp),
                        shape = RoundedCornerShape(20.dp),
                        trailingIcon = null
                    )
                    Text(
                        text = "Опис:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E8FE8)
                    )
                    OutlinedTextField(
                        value = description,
                        onValueChange = { newDescription ->
                            description = newDescription
                            updateLab(lab.copy(description = newDescription))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(fontSize = 17.sp),
                        shape = RoundedCornerShape(20.dp),
                        trailingIcon = null
                    )
                    Text(
                        text = "В процесі:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E8FE8)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = inProgress,
                            onCheckedChange = { isChecked ->
                                inProgress = isChecked
                                updateLab(lab.copy(inProgress = isChecked))
                            }
                        )
                        Text(text = if (inProgress) "Виконується" else "Не виконується")
                    }
                    Text(
                        text = "Завершено:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E8FE8)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isCompleted,
                            onCheckedChange = { isChecked ->
                                isCompleted = isChecked
                                updateLab(lab.copy(isCompleted = isChecked))
                            }
                        )
                        Text(text = if (isCompleted) "Завершено" else "Не завершено")
                    }
                    Text(
                        text = "Коментарі:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E8FE8)
                    )
                    OutlinedTextField(
                        value = comment,
                        onValueChange = { newComment ->
                            comment = newComment
                            updateLab(lab.copy(comment = newComment))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(fontSize = 17.sp),
                        shape = RoundedCornerShape(20.dp),
                    )
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
                val newLab = SubjectLabEntity(
                    subjectId = subjectState.value?.id ?: id,
                    labName = (subjectLabsState.value.maxByOrNull { it.labName }?.labName ?: 0) + 1,
                    title = "",
                    description = "",
                    inProgress = false,
                    isCompleted = false,
                    comment = ""
                )
                CoroutineScope(Dispatchers.IO).launch {
                    db.subjectLabsDao.insert(newLab)
                    withContext(Dispatchers.Main) {
                        subjectLabsState.value = db.subjectLabsDao.getSubjectLabsBySubjectId(id)
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
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