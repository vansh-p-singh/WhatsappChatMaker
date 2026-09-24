package dev.vansh.whatsappchatmaker

import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatScreen(){
    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding()
    ) {
        Row(
            modifier = Modifier.height(65.dp).background(Color(0xFFFFFF)).fillMaxWidth().padding(3.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ){
                IconButton(
                    onClick = {}
                ){
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Favorite",
                    )
                }

//                DP
                Image(painterResource(R.drawable.dp),contentDescription = "Profile Picture", contentScale = ContentScale.Crop,
                    modifier = Modifier.size(47.dp).clip(CircleShape))

//                Name
                Text(text="Laura Schmidt", fontSize = 18.sp, fontWeight = FontWeight.W400)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)

            ){
                Icon(Icons.Outlined.Videocam, contentDescription = "Video Call",
                    modifier = Modifier.size(28.dp))
                Icon(Icons.Outlined.Phone, contentDescription = "Call",
                    modifier = Modifier.size(26.dp))
                Icon(Icons.Default.MoreVert, contentDescription = "More Options",
                    modifier = Modifier.size(28.dp))

            }

        }

//        Chat Screen
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // 1. The Background Image layer
            Image(
                painter = painterResource(id = R.drawable.chat_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // 2. The Content layer stacked on top
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn() {
                    items(chatList){
                        item->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {

                            val alignment=if (item.userSent) Alignment.CenterEnd else Alignment.CenterStart

                            val color=if (item.userSent) Color(0xFFD8FDD2) else Color(0xFFFFFFFF)

                                item.msg?.let {


                                    Box(
                                        modifier = Modifier.background(
                                            color = color,
                                            shape = RoundedCornerShape(16.dp)
                                        ).padding(vertical=6.dp, horizontal = 8.dp).align(alignment)
                                    ) {
                                        Text(
                                            it,
                                            modifier =Modifier.align(Alignment.TopStart)
                                                .padding(bottom=15.dp,end=50.dp)
                                        )
                                        Row(modifier =Modifier.align(Alignment.BottomEnd),
                                            verticalAlignment = Alignment.CenterVertically){


                                        Text(item.time,

                                            fontSize = 12.sp,
                                            color = Color(0xFF565D60)
                                        )
                                            if (item.userSent){
                                                Spacer(modifier = Modifier.width(1.dp))
                                                Image(painterResource(R.drawable.double_tick), contentDescription = "Message Seen",
                                                modifier = Modifier.size(25.dp))
                                            }

                                        }
                                    }
                                }
                        }
                    }
                }
            }
        }
    }
}


var chatList = mutableListOf<Chat>(
    Chat("Hi",true,"10:00 AM",null),
    Chat("Hallo, wie geht es dir?",false,"10:01 AM",null),
    Chat("I Miss you BOBO!!",true,"10:00 AM",null),
    Chat("Me too Babe",false,"10:00 AM",null),
)
data class Chat(
    var msg:String?,
    var userSent:Boolean,
    var time:String,
    var img: Uri?
)

@Preview(showSystemUi = true)
@Composable
fun PreviewChatScreen(){
    ChatScreen()
}

