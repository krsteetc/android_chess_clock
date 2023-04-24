package com.fict.myapplication.chessclock

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

//do not use it directly
var isPlayer1Turn by mutableStateOf(false)
var isGameStarted by mutableStateOf(false)
var isResetDialogOpened by mutableStateOf(false)
var isTimeManagerOpened by mutableStateOf(false)
var isMediaPlayerEnabled by mutableStateOf(true)
var isGameFinished by mutableStateOf(false)