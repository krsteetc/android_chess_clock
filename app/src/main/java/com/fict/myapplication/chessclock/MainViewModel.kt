package com.fict.example.componentsplayground.activities.tempates

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class ClockModel(
    val isGameStarted: Boolean = false,
    val isPlayer1White: Boolean = true,
    val isPlayer1OnTurn: Boolean = true,
    val whiteTime: Long = 10 * 60L,
    val blackTime: Long = 10 * 60L,
) {
}

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ClockModel>(ClockModel())
    val uiState = _uiState.asStateFlow()

    val countDownTimer = object : CountDownTimer(10 * 60  * 1000, 1000) {
        override fun onTick(millisRemaining: Long) {
            tickOnEverySecond(millisRemaining)
        }

        override fun onFinish() {
        }
    }

    fun setWhiteTime(newWhiteTime: Long) {
        _uiState.update {
            it.copy(whiteTime = newWhiteTime)
        }
    }

    fun setBlackTime(newBlackTime: Long) {
        _uiState.update {
            it.copy(blackTime = newBlackTime)
        }
    }

    fun tickOnEverySecond(millisRemaining: Long) {
        setWhiteTime(millisRemaining / 1000)
    }

    fun startGame() {
        if (!uiState.value.isGameStarted) {
            countDownTimer.start()
            //todo isGameStarted set to true
        }
    }

    fun stopGame() {
        countDownTimer.cancel()
    }
}