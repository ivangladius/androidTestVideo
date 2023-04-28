package com.example.myapplicationasdf

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapplicationasdf.ui.theme.MyApplicationasdfTheme
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationasdfTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlayVideo()
                }
            }
        }
    }
}

@Composable
fun PlayVideo() {

    val context = LocalContext.current
    val link = "https://hrardmediathek-a.akamaihd.net/odinson/giraffe-erdmaennchen-and-co/SVID-AD44DEC8-F47E-4F6E-9851-392FC455924B/81efa1c5-ebe3-4a06-ae94-557185ddb85d/0156388_sendeton_1920x1080-50p-5000kbit.mp4"

    val exoPlayer = ExoPlayer.Builder(context).build()
    val mediaItem = MediaItem.fromUri(Uri.parse(link))
    exoPlayer.setMediaItem(mediaItem)


        val playerView = StyledPlayerView(context)
        playerView.player = exoPlayer


        DisposableEffect(AndroidView(factory = {playerView})) {

            exoPlayer.prepare()
            exoPlayer.playWhenReady = true

            onDispose {
                exoPlayer.release()
            }
        }




}
