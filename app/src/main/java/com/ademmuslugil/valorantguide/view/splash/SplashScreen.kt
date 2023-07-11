package com.ademmuslugil.valorantguide.view.splash

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ademmuslugil.valorantguide.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Surface(
        color = colorResource(id = R.color.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_valorant_logo),
                contentDescription = "Valorant icon",
                modifier = Modifier.size(150.dp)
            )

            Text(
                text = stringResource(id = R.string.valorant_guide),
                style = TextStyle(
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.bowlbyonesc_regular)),
                    fontSize = 18.sp
                )
            )
        }
    }
    PlaySound(context = LocalContext.current)
    NavigateToHomeScreen(navController = navController)
}

@Composable
fun NavigateToHomeScreen(navController: NavController) {
    LaunchedEffect(key1 = true, block = {
        delay(2000L)
        navController.navigate("home_screen") {
            popUpTo(navController.graph.id){
                inclusive = true
            }
        }
    })
}

@Composable
fun PlaySound(context: Context) {
    val mediaPlayer = MediaPlayer()
    LaunchedEffect(Unit) {
        val soundUri = Uri.parse("android.resource://${context.packageName}/raw/valorant_spike_plant")
        mediaPlayer.setDataSource(context, soundUri)
        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
        }
        mediaPlayer.prepareAsync()
    }

    DisposableEffect(Unit){
        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release() //release the MediaPlayer object
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    //SplashScreen()
}