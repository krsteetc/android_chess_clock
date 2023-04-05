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


@Composable
fun Player1(viewModel: MainViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val whiteTime =
        "${uiState.player1TimeToDisplay / 60} : ${uiState.player1TimeToDisplay % 60}" //todo add formatting and conversion

    Button(
        onClick = {
            isPlayer1Turn = false
            if (!isGameStarted) {
                viewModel.startGame()
            } else {
                viewModel.switchTurn()
            }
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = if (isGameStarted && isPlayer1Turn) colors.darkGreen else colors.primary),
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
    val blackTime =
        "${uiState.player2TimeToDisplay / 60} : ${uiState.player2TimeToDisplay % 60}"  //todo add formatting and conversion

    Button(
        onClick = {
            isPlayer1Turn = true
            if (!isGameStarted) {
                viewModel.startGame()
            } else {
                viewModel.switchTurn()
            }
        },

        colors = ButtonDefaults.buttonColors(backgroundColor = if (isGameStarted && !isPlayer1Turn) colors.darkGreen else colors.primary),
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

@Composable
fun PlayButton(viewModel: MainViewModel) {
    Image(
        painterResource(R.drawable.play_button),
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .clickable {
                if (isGameStarted) {
                    viewModel.stopGame()
                } else {
                    viewModel.startGame()
                }
            }
    )
}

@Composable
fun ResetButton(viewModel: MainViewModel) {
    Image(
        painterResource(R.drawable.replay_button),
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .clickable { viewModel.resetGame() }
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
