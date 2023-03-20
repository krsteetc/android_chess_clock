package com.fict.myapplication.chessclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fict.myapplication.chessclock.ui.theme.ChessClockTheme
import java.time.Clock

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChessClockTheme {
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
   // val button1 = ClockButton(rotateAngle = 180f, checkIt = false, countTime = button2.Modifier)
  //  val button2 = ClockButton(rotateAngle = 0f, checkIt = true, countTime = button1.Modifier)
    Surface(
        color = Color.DarkGray,
        modifier = Modifier.fillMaxSize()
    ){

        Column (

            modifier = Modifier.fillMaxSize()
                ) {

            Column(Modifier.weight(46.5f)) {
                ClockButton(180f,false)
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
                ClockButton(0f,true)
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