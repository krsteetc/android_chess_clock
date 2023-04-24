package com.fict.myapplication.chessclock

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fict.example.componentsplayground.activities.tempates.MainViewModel
import com.fict.myapplication.chessclock.ui.theme.fontFamily
import com.fict.myapplication.chessclock.ui.theme.darkGreen
import com.fict.myapplication.chessclock.ui.theme.midRed


@Composable
fun Player1(viewModel: MainViewModel, context: Context) {

    val uiState by viewModel.uiState.collectAsState()
    val fontSize: TextUnit
    val player1Time: String
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.finish_sound)

    if (uiState.player1TimeToDisplay >= 3600) {
        player1Time = String.format(
            "%02d:%02d:%02d",
            uiState.player1TimeToDisplay / 3600,
            (uiState.player1TimeToDisplay / 60) - (60 * (uiState.player1TimeToDisplay / 3600).toInt()),
            uiState.player1TimeToDisplay % 60
        )
        fontSize = 70.sp
    } else {
        player1Time = String.format(
            "%02d:%02d", uiState.player1TimeToDisplay / 60, uiState.player1TimeToDisplay % 60
        )
        fontSize = 105.sp

    }

    if (isGameFinished && isPlayer1Turn && isMediaPlayerEnabled) {
        mediaPlayer.start()
    }

    Button(
        onClick = {
            if (!isGameFinished) {
                if (!isGameStarted) {
                    if (uiState.isRestarted) {
                        isPlayer1Turn = false
                        uiState.isRestarted = false
                    }
                    viewModel.startGame(context)
                } else {
                    if (isPlayer1Turn) {
                        isPlayer1Turn = false
                        viewModel.switchTurn(context)
                    }
                }
            }
        }, colors = ButtonDefaults.buttonColors(
            backgroundColor = if (!isGameFinished) {
                if (isGameStarted && isPlayer1Turn) {
                    colors.darkGreen
                } else {
                    colors.primary
                }
            } else if (isPlayer1Turn) {
                colors.midRed
            } else colors.primary
        ), modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Column(
            modifier = Modifier.weight(45f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Moves: ${uiState.player1Moves}",
                fontFamily = fontFamily,
                modifier = Modifier.rotate(180f),
            )

            Text(
                text = player1Time,
                fontFamily = fontFamily,
                modifier = Modifier.rotate(180f),
                fontSize = fontSize
            )

        }
    }
}

@Composable
fun Player2(viewModel: MainViewModel, context: Context) {

    val uiState by viewModel.uiState.collectAsState()
    val fontSize: TextUnit
    val player2Time: String
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.finish_sound)

    if (uiState.player2TimeToDisplay >= 3600) {
        player2Time = String.format(
            "%02d:%02d:%02d",
            uiState.player2TimeToDisplay / 3600,
            (uiState.player2TimeToDisplay / 60) - (60 * (uiState.player2TimeToDisplay / 3600).toInt()),
            uiState.player2TimeToDisplay % 60
        )
        fontSize = 70.sp
    } else {
        player2Time = String.format(
            "%02d:%02d", uiState.player2TimeToDisplay / 60, uiState.player2TimeToDisplay % 60
        )
        fontSize = 105.sp

    }

    if (isGameFinished && !isPlayer1Turn && isMediaPlayerEnabled) {
        mediaPlayer.start()
    }

    Button(
        onClick = {
            if (!isGameFinished) {
                if (!isGameStarted) {
                    if (uiState.isRestarted) {
                        isPlayer1Turn = true
                        uiState.isRestarted = false
                    }
                    viewModel.startGame(context)
                } else {
                    if (!isPlayer1Turn) {
                        isPlayer1Turn = true
                        viewModel.switchTurn(context)
                    }
                }
            }
        },

        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (!isGameFinished) {
                if (isGameStarted && !isPlayer1Turn) {
                    colors.darkGreen
                } else {
                    colors.primary
                }
            } else if (!isPlayer1Turn) {
                colors.midRed
            } else colors.primary
        ), modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Column(

            modifier = Modifier.weight(45f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = player2Time, fontFamily = fontFamily, fontSize = fontSize
            )

            Text(
                text = "Moves: ${uiState.player2Moves}", fontFamily = fontFamily, fontSize = 18.sp
            )
        }
    }
}

@Composable
fun ResetTimerDialog(viewModel: MainViewModel, context: Context) {
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.ui_sound)
    if (isResetDialogOpened) {
        viewModel.stopGame()
        AlertDialog(
            onDismissRequest = {
                isResetDialogOpened = false
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.resetGame()
                    isResetDialogOpened = false
                    if (isMediaPlayerEnabled) {
                        mediaPlayer.start()
                    }
                }) {
                    Text(
                        text = "Confirm", fontFamily = fontFamily, color = Color.LightGray
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    // close the dialog
                    isResetDialogOpened = false
                }) {
                    Text(
                        text = "Dismiss", fontFamily = fontFamily, color = Color.LightGray
                    )
                }
            },
            title = {
                Text(
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp),
                    text = "Reset the clock?",
                    fontFamily = fontFamily,
                    fontSize = 18.sp,
                    color = Color.White
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(15.dp),
            backgroundColor = Color.DarkGray
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TimeManagerDialog(viewModel: MainViewModel, context: Context) {
    val listItems = arrayOf("All Players", "Player 1", "Player 2")
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.ui_sound)


    if (isTimeManagerOpened) {
        viewModel.stopGame()
        AlertDialog(
            onDismissRequest = {
                isTimeManagerOpened = false
            },
            confirmButton = {
                TextButton(onClick = {
                    isTimeManagerOpened = false
                    viewModel.newInitialTime()
                    if (isMediaPlayerEnabled) {
                        mediaPlayer.start()
                    }
                    isResetDialogOpened = true
                }) {
                    Text(
                        text = "Confirm", fontFamily = fontFamily, color = Color.LightGray
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    isTimeManagerOpened = false
                }) {
                    Text(
                        text = "Dismiss", fontFamily = fontFamily, color = Color.LightGray
                    )
                }
            },
            title = {
                Text(
                    text = "Time Manager",
                    fontFamily = fontFamily,
                    fontSize = 24.sp,
                    color = Color.White
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    MyOutlineTextInput(
                        label = "Hours", placeHolder = "", paddingTop = 45, viewModel = viewModel
                    )
                    MyOutlineTextInput(
                        label = "Minutes", placeHolder = "", paddingTop = 5, viewModel = viewModel
                    )
                    MyOutlineTextInput(
                        label = "Seconds", placeHolder = "", paddingTop = 5, viewModel = viewModel
                    )
                    MyOutlineTextInput(
                        label = "Increment on move",
                        placeHolder = "Seconds",
                        paddingTop = 5,
                        viewModel = viewModel
                    )
                    ExposedDropdownMenuBox(
                        expanded = expanded, onExpandedChange = {
                            expanded = !expanded
                        }, modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
                    ) {
                        TextField(
                            value = selectedItem,
                            textStyle = TextStyle.Default.copy(fontFamily = fontFamily),
                            onValueChange = {},
                            readOnly = true,
                            label = { Text(text = "Apply to", fontFamily = fontFamily) },

                            colors = ExposedDropdownMenuDefaults.textFieldColors()
                        )

                        // menu
                        ExposedDropdownMenu(expanded = expanded,
                            onDismissRequest = { expanded = false }) {
                            listItems.forEach { selectedOption ->
                                DropdownMenuItem(onClick = {
                                    selectedItem = selectedOption
                                    when (selectedItem) {
                                        listItems[0] -> {
                                            viewModel.setLabelPosition(null)
                                        }
                                        listItems[1] -> {
                                            viewModel.setLabelPosition(true)
                                        }
                                        listItems[2] -> {
                                            viewModel.setLabelPosition(false)
                                        }
                                    }
                                    expanded = false
                                }) {
                                    Text(text = selectedOption, fontFamily = fontFamily)
                                }
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(15.dp),
            backgroundColor = Color.DarkGray
        )
    }
}

@Composable
fun MyOutlineTextInput(
    viewModel: MainViewModel, label: String, placeHolder: String, paddingTop: Int
) {
    var value by remember {
        mutableStateOf("")
    }
    var hours by remember {
        mutableStateOf("")
    }
    var minutes by remember {
        mutableStateOf("")
    }
    var seconds by remember {
        mutableStateOf("")
    }
    var increment by remember {
        mutableStateOf("")
    }

    OutlinedTextField(modifier = Modifier.padding(0.dp, paddingTop.dp, 0.dp, 0.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Next
        ),
        label = { Text(text = label, fontFamily = fontFamily) },
        placeholder = { Text(text = placeHolder, fontFamily = fontFamily) },
        value = value,
        onValueChange = { newText ->
            if (newText.length <= 2) {
                when (label) {
                    "Hours" -> {
                        hours = newText
                        value = newText
                        viewModel.uiState.value.addHours = if (hours != "") {
                            hours.toLong()
                        } else {
                            0L
                        }
                    }
                    "Minutes" -> {
                        minutes = newText
                        value = newText
                        viewModel.uiState.value.addMinutes = if (minutes != "") {
                            minutes.toLong()
                        } else {
                            0L
                        }
                    }
                    "Seconds" -> {
                        seconds = newText
                        value = newText
                        viewModel.uiState.value.addSeconds = if (seconds != "") {
                            seconds.toLong()
                        } else {
                            0L
                        }
                    }
                    else -> {
                        increment = newText
                        value = newText
                        viewModel.uiState.value.incrementInitializer = if (increment != "") {
                            increment.toLong()
                        } else {
                            0L
                        }
                    }
                }
            }
        })
}

@Composable
fun PlayButton(viewModel: MainViewModel, context: Context) {
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.ui_sound)
    Image(if (isGameStarted) {
        painterResource(R.drawable.pause_button)
    } else {
        painterResource(R.drawable.play_button)
    }, contentDescription = "", modifier = Modifier
        .size(40.dp)
        .clickable {
            if (isGameStarted) {
                viewModel.stopGame()
                if (isMediaPlayerEnabled) {
                    mediaPlayer.start()
                }
            } else {
                viewModel.uiState.value.isRestarted = false
                if (isGameFinished && isMediaPlayerEnabled) {
                    mediaPlayer.start()
                }
                viewModel.startGame(context)
            }
        })
}

@Composable
fun ResetButton(context: Context) {
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.ui_sound)
    Image(painterResource(R.drawable.replay_button),
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .clickable {
                isResetDialogOpened = true
                if (isMediaPlayerEnabled) {
                    mediaPlayer.start()
                }
            })
}

@Composable
fun TimeButton(viewModel: MainViewModel, context: Context) {
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.ui_sound)
    Image(painterResource(R.drawable.time_button),
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .clickable {
                viewModel.uiState.value.addHours = 0
                viewModel.uiState.value.addMinutes = 0
                viewModel.uiState.value.addSeconds = 0
                isTimeManagerOpened = true
                if (isMediaPlayerEnabled) {
                    mediaPlayer.start()
                }
            })
}

@Composable
fun SoundButton(context: Context) {
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.ui_sound)
    Image(if (isMediaPlayerEnabled) {
        painterResource(R.drawable.volume_up_button)
    } else {
        painterResource(R.drawable.volume_off_button)
    }, contentDescription = "", modifier = Modifier
        .size(40.dp)
        .clickable {
            if (isMediaPlayerEnabled) {
                isMediaPlayerEnabled = false
            } else {
                isMediaPlayerEnabled = true
                mediaPlayer.start()
            }
        })
}
