package com.fict.myapplication.chessclock

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

//do not use it directly
var clicked1 by mutableStateOf(false)
var clicked2 by mutableStateOf(false)

//todo
//change name to isTurnOnPlayer1
//add var isGameStarted

//isGameStarted && isTurnOnWhite green else dark
//isGameStarted && !isTurnOnWhite green else dark