package com.fict.myapplication.chessclock

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.fict.myapplication.chessclock.ui.theme.ChessClockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChessClockTheme {

                WindowCompat.setDecorFitsSystemWindows(window, false)
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {
                    //Display
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen(){
    Surface(
        color = Color.DarkGray,
        modifier = Modifier.fillMaxSize()
    ){

        Column (

            modifier = Modifier.fillMaxSize()
                ) {

            Column(Modifier.weight(46.5f)) {
                Player1()
            }


            Row(

                modifier = Modifier.weight(7f),

            ) {

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    ResetButton()
                    PlayButton()
                    TimeButton()
                    SoundButton()
                }

            }


            Column(Modifier.weight(46.5f)) {
                Player2()
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChessClockTheme {
        MainScreen()
    }
}