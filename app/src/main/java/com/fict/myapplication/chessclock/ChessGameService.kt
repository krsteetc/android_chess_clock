package com.fict.myapplication.chessclock

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

//do not use it directly
var isPlayer1Turn by mutableStateOf(false)
var isGameStarted by mutableStateOf(false)
//var player1Time by mutableStateOf(120L)
//var player2Time by mutableStateOf(120L)