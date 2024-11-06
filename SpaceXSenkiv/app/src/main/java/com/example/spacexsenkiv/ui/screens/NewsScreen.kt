package com.example.spacexsenkiv.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import android.net.Uri
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.core.content.ContextCompat
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.spacexsenkiv.R

@Composable
fun NewsScreen(viewModel: NewsViewModel = getViewModel()) {
    val articles by viewModel.articles.collectAsState()
    val context = LocalContext.current
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
        articles.forEach { article ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(10.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = article.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF699DFF)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column {
                        article.links.article.let { link ->
                            Image(
                                painter = painterResource(id = R.drawable.iconlink),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(25.dp)
                                    .clickable {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                                        ContextCompat.startActivity(context, intent, null)
                                    },
                                contentScale = ContentScale.Crop,
                                colorFilter = ColorFilter.tint(Color(0xFFFF971C))
                            )

                        }
                    }

                }
                Spacer(modifier = Modifier.height(4.dp))
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 2.dp,
                    color = Color(0xFF699DFF)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Date: ${article.event_date_utc}",
                    fontSize = 18.sp,
                    color = Color(0xFF656565)
                )
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 2.dp,
                    color = Color(0xFF699DFF)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = article.details,
                    fontSize = 18.sp,
                    color = Color(0xFF656565)
                )

            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
