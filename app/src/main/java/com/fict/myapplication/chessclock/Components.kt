package com.fict.myapplication.chessclock

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fict.example.componentsplayground.activities.tempates.MainViewModel
import com.fict.myapplication.chessclock.ui.theme.darkGreen

//fun ClickedButton1(btn : Button){
//    btn(
//       colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
//   ){}
//}
//fun ClickedButton2(btn : Button){
//   btn
//}

//todo rename white and black instead of player1/2
@Composable
fun Player1(viewModel: MainViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val whiteTime = "${uiState.whiteTime / 60} : ${uiState.whiteTime % 60}" //todo add formatting and conversion

    Button(
        onClick = {
            clicked1 = true
            clicked2 = false

            viewModel.setWhiteTime(15L)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = if (clicked2) colors.darkGreen else colors.primary),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Column(
            modifier = Modifier.weight(45f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Moves: ",
                modifier = Modifier.rotate(180f),
            )

            Text(
                text = "$whiteTime",
                modifier = Modifier.rotate(180f),
                fontSize = 100.sp
            )

        }
    }
}

@Composable
fun Player2(viewModel: MainViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val blackTime = uiState.blackTime //todo add formatting and conversion

    Button(
        onClick = {

            clicked2 = true
            clicked1 = false
            //startTimer()
            viewModel.startGame()
        },

        colors = ButtonDefaults.buttonColors(backgroundColor = if (clicked1) colors.darkGreen else colors.primary),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Column(

            modifier = Modifier.weight(45f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "$blackTime",
                fontSize = 100.sp
            )

            Text(
                text = "Moves: "
            )
        }
    }
}

//@Composable
//fun CountDownView(viewModel: Timer = androidx.lifecycle.viewmodel.compose.viewModel()) {
//
//    val time by viewModel.time.observeAsState(Utility.TIME_COUNTDOWN.formatTime())
//    val progress by viewModel.progress.observeAsState(1.00F)
//    val isPlaying by viewModel.isPlaying.observeAsState(false)
//    val celebrate by viewModel.celebrate.observeAsState(false)
//
//    CountDownView(time = time, progress = progress, isPlaying = isPlaying, celebrate = celebrate) {
//        viewModel.handleCountDownTimer()
//    }
//
//}


@Composable
fun PlayButton() {
    Image(
        painterResource(R.drawable.play_button),
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .clickable { /*TODO*/ }
    )
}

@Composable
fun ResetButton() {
    Image(
        painterResource(R.drawable.replay_button),
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .clickable { /*TODO*/ }
    )
}

@Composable
fun TimeButton() {
    Image(
        painterResource(R.drawable.time_button),
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .clickable { /*TODO*/ }
    )
}

@Composable
fun SoundButton() {
    Image(
        painterResource(R.drawable.sound_button),
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .clickable { /*TODO*/ }
    )
}
