package dev.vansh.whatsappchatmaker

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.InsertEmoticon
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatScreen(innerPadding: PaddingValues){
    var chatList = remember {
        mutableStateListOf<Chat>(
            Chat("Hi", true, "10:00 AM", null),
            Chat(
                "Hallo, wie geht es dir?. Ich vermisee dich so schnell wie möglich",
                false,
                "10:01 AM",
                null
            ),
            Chat("I Miss you BOBO!!", true, "10:00 AM", null),
            Chat("Me too Babe", false, "10:00 AM", null)
        )
    }
    var sendButtonMenu by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding)
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
                modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp)
            ) {
                LazyColumn() {
                    items(chatList) { item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {

                            val alignment =
                                if (item.userSent) Alignment.CenterEnd else Alignment.CenterStart

                            val color = if (item.userSent) Color(0xFFD8FDD2) else Color(0xFFFFFFFF)

                            item.msg?.let {


                                Box(
                                    modifier = Modifier.background(
                                        color = color,
                                        shape = RoundedCornerShape(16.dp)
                                    ).padding(vertical = 6.dp, horizontal = 8.dp)
                                        .widthIn(min = 60.dp, max = 240.dp).align(alignment)
                                ) {
                                    Text(
                                        it,
                                        modifier = Modifier.align(Alignment.TopStart)
                                            .padding(bottom = 20.dp, end = 10.dp)
                                    )
                                    Row(
                                        modifier = Modifier.align(Alignment.BottomEnd),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {


                                        Text(
                                            item.time,

                                            fontSize = 12.sp,
                                            color = Color(0xFF565D60)
                                        )
                                        if (item.userSent) {
                                            Spacer(modifier = Modifier.width(1.dp))
                                            Image(
                                                painterResource(R.drawable.double_tick),
                                                contentDescription = "Message Seen",
                                                modifier = Modifier.size(25.dp)
                                            )
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }


            var text by rememberSaveable { mutableStateOf("") }
            Row(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(10.dp),
                verticalAlignment = Alignment.Bottom) {

                TextField(
                    leadingIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.InsertEmoticon, contentDescription = "Stickers")
                        }
                    },
                    trailingIcon = {
                        Row {
                            IconButton(onClick = {}) {
                                Icon(Icons.Default.Attachment, contentDescription = "Attachments", modifier = Modifier.rotate(270f))
                            }
                            IconButton(onClick = {}) {
                                Icon(Icons.Default.CameraAlt, contentDescription = "Take Picture")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.88f),
                    value = text,
                    onValueChange = {
                        text = it;
                    },
                    placeholder = { Text("Message") },
                    colors= TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(28.dp))
                Spacer(modifier = Modifier.width(15.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    if(sendButtonMenu) {
                        fun addChat(userSent: Boolean){
                            if(text.trim().isNotBlank()){
                                val time = SimpleDateFormat(
                                    "hh:mm a",
                                    Locale.getDefault()
                                ).format(
                                    Date()
                                )
                                chatList.add(
                                    Chat(msg = text.trim(), userSent = userSent, time = time.toString(),img = null)
                                )}
                            text=""
                        }
                        IconButton(
                            onClick = {
                                addChat(false)
                            },
                            modifier = Modifier.clip(shape = CircleShape)
                                .background(color = Color(0xffffffff))
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.Send,
                                contentDescription = "Send",
                                tint = Color.Black
                            )
                        }


                        IconButton(
                            onClick = {
                                addChat(true)
                            },
                            modifier = Modifier.clip(shape = CircleShape)
                                .background(color = Color(0xFFD8FDD2))
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.Send,
                                contentDescription = "Send",
                                tint = Color.Black, modifier = Modifier.rotate(180f)
                            )
                        }
                    }

                    IconButton(
                        onClick = {sendButtonMenu=!sendButtonMenu},
                        modifier = Modifier.clip(shape = CircleShape)
                            .background(color = Color(0xff1dab61))
                    ) {
                        if (text.isBlank()){
                            Icon(
                                Icons.Default.Mic,
                                contentDescription = "Send",
                                tint = Color.White
                            )
                        }
                        else {
                            Icon(
                                Icons.AutoMirrored.Filled.Send,
                                contentDescription = "Send",
                                tint = Color.White
                            )
                        }
                    }

                }
            }
        }
    }
}




data class Chat(
    var msg:String?,
    var userSent:Boolean,
    var time:String,
    var img: Uri?
)

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun PreviewChatScreen(){
    ChatScreen(PaddingValues(12.dp))
}


