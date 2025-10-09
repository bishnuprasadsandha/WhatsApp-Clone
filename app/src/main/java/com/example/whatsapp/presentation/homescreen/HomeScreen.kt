package com.example.whatsapp.presentation.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsapp.R
import com.example.whatsapp.presentation.bottomnavigation.BottomNavigation

@Composable
@Preview(showSystemUi = true)
fun HomeScreen() {

    val chatData = listOf(
        ChatListModel(
            image = R.drawable.salman_khan,
            name = "Salman Khan",
            time = "12:00AM",
            message = "Hii"
        ),

        ChatListModel(
            image = R.drawable.rashmika,
            name = "Rashmika",
            time = "9:00PM",
            message = "Hello Dear❤️"
        ),

        ChatListModel(
            image = R.drawable.sharukh_khan,
            name = "SRK",
            time = "6:45PM",
            message = "Hello Hero😎"
        ),
        ChatListModel(
            image = R.drawable.asish,
            name = "Asish Kumar",
            time = "12:00PM",
            message = "Where are you?"
        ),
        ChatListModel(
            image = R.drawable.akshay_kumar,
            name = "Akshay Kumar",
            time = "7:400PM",
            message = "Hello"
        ),
        ChatListModel(
            image = R.drawable.bhuvan_bam,
            name = "Bhuvan Bam",
            time = "12:00PM",
            message = "Congrats on 13k sub bro.."
        ),
        ChatListModel(
            image = R.drawable.disha_patani,
            name = "Dish Patani",
            time = "6:45PM",
            message = "Hello"
        ),
        ChatListModel(
            image = R.drawable.carryminati,
            name = "Carry",
            time = "7:00AM",
            message = "Toh Kaise hai aap log"
        ),
        ChatListModel(
            image = R.drawable.hrithik_roshan,
            name = "Hrithik Roshan",
            time = "9:005PM",
            message = "Hello"
        ),
        ChatListModel(
            image = R.drawable.mrbeast,
            name = "Mr Beast",
            time = "6:45PM",
            message = "Hello"
        ),
        ChatListModel(
            image = R.drawable.rajkummar_rao,
            name = "Raj Kumar Rao",
            time = "12:00PM",
            message = "Hello Hero😎"
        ),

    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = colorResource(id = R.color.light_green),
                modifier = Modifier.size(65.dp),
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.chat_icon),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        bottomBar = {
            BottomNavigation()
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "WhatsApp",
                    fontSize = 28.sp,
                    color = colorResource(id = R.color.light_green),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp),
                    fontWeight = FontWeight.Bold
                )
                Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.more),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            HorizontalDivider()

            LazyColumn {
                items(chatData) {
                    ChatDesign(chatListModel = it)
                }
            }
        }
    }

}