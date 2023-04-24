package com.fict.example.componentsplayground.activities.tempates

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.fict.myapplication.chessclock.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class ClockModel(
    var player1InitialTime: Long = 600L,
    var player2InitialTime: Long = 600L,
    val player1TimeToDisplay: Long = player1InitialTime,
    val player2TimeToDisplay: Long = player2InitialTime,
    var timeRemainingForPlayer1: Long = player1InitialTime * 1000L,
    var timeRemainingForPlayer2: Long = player2InitialTime * 1000L,
    var player1Moves: Int = 0,
    var player2Moves: Int = 0,
    var addHours: Long = 0L,
    var addMinutes: Long = 0L,
    var addSeconds: Long = 0L,
    var incrementInitializer: Long = 0L,
    var incrementForPlayer1: Long = 0L,
    var incrementForPlayer2: Long = 0L,
    //Null = All Players, True = Player 1, False = Player 2
    var selectedLabel: Boolean? = null,
    var isRestarted: Boolean = true
)

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ClockModel>(ClockModel())
    val uiState = _uiState.asStateFlow()

    var countDownTimerPlayer1: CountDownTimer? = null

    fun startCountDownForPlayer1() {
        countDownTimerPlayer1?.cancel()
        countDownTimerPlayer1 =
            object : CountDownTimer(uiState.value.timeRemainingForPlayer1, 1000) {
                override fun onTick(millisRemaining: Long) {
                    tickOnEverySecond(millisRemaining)
                }

                override fun onFinish() {
                    isGameFinished = true
                    stopGame()
                }
            }
        countDownTimerPlayer1?.start()
    }

    var countDownTimerPlayer2: CountDownTimer? = null

    fun startCountDownForPlayer2() {
        countDownTimerPlayer2?.cancel()
        countDownTimerPlayer2 =
            object : CountDownTimer(uiState.value.timeRemainingForPlayer2, 1000) {
                override fun onTick(millisRemaining: Long) {
                    tickOnEverySecond(millisRemaining)
                }

                override fun onFinish() {
                    isGameFinished = true
                    stopGame()
                }
            }
        countDownTimerPlayer2?.start()
    }

    fun setPlayer1Time(newPlayer1Time: Long) {
        _uiState.update {
            it.copy(player1TimeToDisplay = newPlayer1Time)
        }
    }

    fun setPlayer2Time(newPlayer2Time: Long) {
        _uiState.update {
            it.copy(player2TimeToDisplay = newPlayer2Time)
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

    fun setPlayer1InitialTime(newPlayer1Time: Long) {
        _uiState.update {
            it.copy(player1InitialTime = newPlayer1Time)
        }
    }

    fun setPlayer2InitialTime(newPlayer2Time: Long) {
        _uiState.update {
            it.copy(player2InitialTime = newPlayer2Time)
        }
    }

    fun incrementPlayer1(increment: Long) {
        _uiState.update {
            it.copy(timeRemainingForPlayer1 = (uiState.value.timeRemainingForPlayer1 + increment))
        }
        _uiState.update {
            it.copy(player1TimeToDisplay = (uiState.value.timeRemainingForPlayer1 / 1000))
        }

    }

    fun incrementPlayer2(increment: Long) {
        _uiState.update {
            it.copy(timeRemainingForPlayer2 = (uiState.value.timeRemainingForPlayer2 + increment))
        }
        _uiState.update {
            it.copy(player2TimeToDisplay = (uiState.value.timeRemainingForPlayer2 / 1000))
        }
    }

    fun setLabelPosition(newPosition: Boolean?) {
        _uiState.update {
            it.copy(selectedLabel = newPosition)
        }
    }

    fun tickOnEverySecond(millisRemaining: Long) {
        if (isPlayer1Turn) {
            setPlayer1Time(millisRemaining / 1000)
            setPlayer1TimeRemaining(millisRemaining)
        } else {
            setPlayer2Time(millisRemaining / 1000)
            setPlayer2TimeRemaining(millisRemaining)
        }
    }

    fun startGame(context: Context) {
        val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.clock_sound)
        if (!isGameFinished) {
            if (isPlayer1Turn) {
                startCountDownForPlayer1()
            } else {
                startCountDownForPlayer2()
            }
            if (isMediaPlayerEnabled) {
                mediaPlayer.start()
            }
            isGameStarted = true
        } else {
            isResetDialogOpened = true
        }
    }

    fun stopGame() {
        countDownTimerPlayer1?.cancel()
        countDownTimerPlayer2?.cancel()
        isGameStarted = false
    }

//    fun pauseGame(context : Context) {
//        //if (!uiState.value.isGameStarted) {
//        if (isGamePaused) {
//            val mediaPlayer : MediaPlayer = MediaPlayer.create(context, R.raw.clock_sound)
//            if (isPlayer1Turn) {
//                startCountDownForPlayer1()
//            } else {
//                startCountDownForPlayer2()
//            }
//            isGamePaused = false
//            if(isMediaPlayerEnabled) {
//                mediaPlayer.start()
//            }
//        }else{
//            val mediaPlayer : MediaPlayer = MediaPlayer.create(context, R.raw.ui_sound)
//            countDownTimerPlayer1?.cancel()
//            countDownTimerPlayer2?.cancel()
//            isGamePaused = true
//            mediaPlayer.start()
//        }
//    }

    fun resetGame() {
        uiState.value.isRestarted = true
        countDownTimerPlayer1?.cancel()
        countDownTimerPlayer2?.cancel()
        isGameFinished = false
        isGameStarted = false
        setPlayer1Time(uiState.value.player1InitialTime)
        setPlayer2Time(uiState.value.player2InitialTime)
        setPlayer1TimeRemaining(uiState.value.player1InitialTime * 1000L)
        setPlayer2TimeRemaining(uiState.value.player2InitialTime * 1000L)
        uiState.value.player1Moves = 0
        uiState.value.player2Moves = 0
    }

    fun switchTurn(context: Context) {
        val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.clock_sound)
        if (isPlayer1Turn) {
            startCountDownForPlayer1()
            countDownTimerPlayer2?.cancel()
            incrementPlayer2(uiState.value.incrementForPlayer2)
            uiState.value.player2Moves++
        } else {
            startCountDownForPlayer2()
            countDownTimerPlayer1?.cancel()
            incrementPlayer1(uiState.value.incrementForPlayer1)
            uiState.value.player1Moves++
        }

        if (isMediaPlayerEnabled) {
            mediaPlayer.start()
        }
    }

    fun newInitialTime() {
        val newTime: Long =
            (uiState.value.addHours * 3600) + (uiState.value.addMinutes * 60) + uiState.value.addSeconds
        val newIncrement = (uiState.value.incrementInitializer * 1000L)
        when (uiState.value.selectedLabel) {
            null -> {
                if (newTime > 0) {
                    setPlayer1InitialTime(newTime)
                    setPlayer2InitialTime(newTime)
                }
                uiState.value.incrementForPlayer1 = newIncrement
                uiState.value.incrementForPlayer2 = newIncrement
            }
            true -> {
                if (newTime > 0) {
                    setPlayer1InitialTime(newTime)
                    setPlayer2InitialTime(uiState.value.player2InitialTime)
                }
                uiState.value.incrementForPlayer1 = newIncrement
            }
            false -> {
                if (newTime > 0) {
                    setPlayer1InitialTime(uiState.value.player1InitialTime)
                    setPlayer2InitialTime(newTime)
                }
                uiState.value.incrementForPlayer2 = newIncrement
            }
        }
    }
}