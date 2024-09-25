package com.example.todosenkiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todosenkiv.ui.theme.ToDoSenkivTheme
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoSenkivTheme {
                MainActivityScreen()
            }
        }

    }
}

@Composable
fun MainActivityScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFADDFFF)) // Color #ADDFFF

    ) {
        val textFieldSurName = remember { mutableStateOf("") }
        val textFieldFirstName = remember { mutableStateOf("") }
        val textFieldAge = remember { mutableStateOf("") }
        val itemList = remember { mutableStateListOf<Item>() }

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = textFieldSurName.value,
                onValueChange = { newSurName -> textFieldSurName.value = newSurName },
                placeholder = {
                    Text(text = "Enter SurName ")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(45.dp))
                    .background(Color.White, shape = RoundedCornerShape(45.dp))
            )
            TextField(
                value = textFieldFirstName.value,
                onValueChange = { newFirstName -> textFieldFirstName.value = newFirstName },
                placeholder = {
                    Text(text = "Enter FirstName")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(45.dp))
                    .background(Color.White, shape = RoundedCornerShape(45.dp))
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = textFieldAge.value,
                onValueChange = { newAge -> textFieldAge.value = newAge },
                placeholder = {
                    Text(text = "Enter Age ")
                },
                modifier = Modifier
                    .weight(0.1f)
                    .padding(8.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(45.dp))
                    .background(Color.White, shape = RoundedCornerShape(45.dp))
            )
            Button(
                onClick = {
                    itemList.add(
                        Item(
                            surname = textFieldSurName.value,
                            firstname = textFieldFirstName.value,
                            age = textFieldAge.value,
                        )
                    )
                },
                modifier = Modifier
                    .padding(8.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(45.dp))
            ) {
                Text(text = "Submit")
            }
        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            items(itemList) { item ->
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .shadow(4.dp, shape = RoundedCornerShape(45.dp))
                        .background(Color(0xFFFFFFC5), shape = RoundedCornerShape(45.dp))
                        .padding(16.dp)

                ) {
                    Text(text = "${item.surname} ${item.firstname} , ${item.age} years")
                }
            }
        }
    }
}

data class Item(val surname: String, val firstname: String, val age: String)

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    ToDoSenkivTheme {
        MainActivityScreen()
    }
}