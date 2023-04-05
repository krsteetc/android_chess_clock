package com.fict.example.componentsplayground.activities.tempates

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import com.fict.myapplication.chessclock.isGameStarted
import com.fict.myapplication.chessclock.isPlayer1Turn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class ClockModel(
    val isGameStarted: Boolean = false,
    val isPlayer1White: Boolean = true,
    val isPlayer1OnTurn: Boolean = true,
    val player1TimeToDisplay: Long = 120L,
    val player2TimeToDisplay: Long = 120L,
    var timeRemainingForPlayer1: Long = 120L,
    var timeRemainingForPlayer2: Long = 120L
) {
}

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ClockModel>(ClockModel())
    val uiState = _uiState.asStateFlow()


    val countDownTimerPlayer1 = object : CountDownTimer(uiState.value.timeRemainingForPlayer1 * 1000, 1000) {
        override fun onTick(millisRemaining: Long) {
            tickOnEverySecond(millisRemaining)
        }

        override fun onFinish() {
        }
    }
    val countDownTimerPlayer2 = object : CountDownTimer(uiState.value.timeRemainingForPlayer2 * 1000, 1000) {
        override fun onTick(millisRemaining: Long) {
            tickOnEverySecond(millisRemaining)
        }

        override fun onFinish() {
        }
    }

    fun setPlayer1Time(newPlayer1Time: Long) {
        _uiState.update {
            it.copy(player1TimeToDisplay = newPlayer1Time)
        }
    }
    fun setPlayer1TimeRemaining(newPlayer1Time: Long) {
        _uiState.update {
            it.copy(timeRemainingForPlayer1 = newPlayer1Time)
        }
    }
    fun setPlayer2TimeRemaining(newPlayer2Time: Long) {
        _uiState.update {
            it.copy(timeRemainingForPlayer2 = newPlayer2Time)
        }
    }

    fun setPlayer2Time(newPlayer2Time: Long) {
        _uiState.update {
            it.copy(player2TimeToDisplay = newPlayer2Time)
        }
    }

    fun tickOnEverySecond(millisRemaining: Long) {
        if (isPlayer1Turn) {
            setPlayer1Time(millisRemaining / 1000)
            setPlayer1TimeRemaining(millisRemaining)
            Log.d("AASSS", " " + millisRemaining)
        } else {
            setPlayer2Time(millisRemaining / 1000)
            setPlayer2TimeRemaining(millisRemaining)
            Log.d("AASSS2", " " + millisRemaining)
        }
    }

    fun startGame() {
        if (!uiState.value.isGameStarted) {
            if (isPlayer1Turn) {
                countDownTimerPlayer1.start()
            } else {
                countDownTimerPlayer2.start()
            }
            isGameStarted = true
        }
    }

    fun stopGame() {
        countDownTimerPlayer1.cancel()
        countDownTimerPlayer2.cancel()
        isGameStarted = false
    }

    fun resetGame() {
        countDownTimerPlayer1.cancel()
        countDownTimerPlayer2.cancel()
        isGameStarted = false
        _uiState.update {
            it.copy(player1TimeToDisplay = 120L)
        }
        _uiState.update {
            it.copy(player2TimeToDisplay = 120L)
        }
    }

    fun switchTurn() {
        if (!isPlayer1Turn) {
            countDownTimerPlayer1.cancel()
            countDownTimerPlayer2.start()
        } else {
            countDownTimerPlayer1.start()
            countDownTimerPlayer2.cancel()
        }
    }
}