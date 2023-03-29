package com.fict.myapplication.chessclock

import android.os.CountDownTimer
import android.os.SystemClock
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive


// TODO: delete me

//class  Timer : ViewModel() {
//    private var countDownTimer: CountDownTimer? = null
//
//    private val _time = MutableLiveData(Utility.TIME_COUNTDOWN.formatTime())
//    val time: LiveData<String> = _time
//
//
//    private val _isPlaying = MutableLiveData(false)
//    val isPlaying: LiveData<Boolean> = _isPlaying
//
//    fun handleCountDownTimer() {
//        if (isPlaying.value == true) {
//            pauseTimer()
//            //_celebrate.postValue(false)
//        } else {
//            startTimer()
//        }
//    }
//
//    fun startTimer() {
//
//        _isPlaying.value = true
//        countDownTimer = object : CountDownTimer(Utility.TIME_COUNTDOWN, 1000) {
//
//            override fun onTick(millisRemaining: Long) {
//                val progressValue = millisRemaining.toFloat() / Utility.TIME_COUNTDOWN
//                handleTimerValues(true, millisRemaining.formatTime(), progressValue, false)
//                //_celebrate.postValue(false)
//            }
//
//            override fun onFinish() {
//                pauseTimer()
//                //_celebrate.postValue(true)
//            }
//        }.start()
//    }
//
//    private fun pauseTimer() {
//        countDownTimer?.cancel()
//        handleTimerValues(false, Utility.TIME_COUNTDOWN.formatTime(), 1.0F, false)
//
//    }
//
//    private fun handleTimerValues(
//        isPlaying: Boolean,
//        text: String,
//        progress: Float,
//        celebrate: Boolean
//    ) {
//        _isPlaying.value = isPlaying
//        _time.value = text
//        //_progress.value = progress
//        // _celebrate.postValue(celebrate)
//    }
//
//}

//@Composable
//fun rememberCountdownTimerState(
//    initialMillis: Long,
//    step: Long = 1000
//): MutableState<Long> {
//    val timeLeft = remember { mutableStateOf(initialMillis) }
//    LaunchedEffect(initialMillis, step) {
//        val startTime = SystemClock.uptimeMillis()
//        while (isActive && timeLeft.value > 0) {
//            // how much time actually passed
//            val duration = (SystemClock.uptimeMillis() - startTime).coerceAtLeast(0)
//            timeLeft.value = (initialMillis - duration).coerceAtLeast(0)
//            delay(step.coerceAtMost(timeLeft.value))
//        }
//    }
//    return timeLeft
//}

//@Composable
//fun Timer() {
//    val millisInFuture: Long = 10 * 1000 // TODO: get actual value
//
//    val timeData = remember {
//        mutableStateOf(millisInFuture)
//    }
//
//    val countDownTimer =
//        object : CountDownTimer(millisInFuture, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//               // Log.d("TAG", "onTick: ")
//                timeData.value = millisInFuture
//            }
//
//            override fun onFinish() {
//
//            }
//        }
//
//    DisposableEffect(key1 = "key") {
//        countDownTimer.start()
//        onDispose {
//            countDownTimer.cancel()
//        }
//    }
//
//  //  Text(
// //       text = timeData.value.toString()
// //   )
//}