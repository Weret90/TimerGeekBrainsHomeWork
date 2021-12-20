package com.umbrella.timer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umbrella.timer.data.TimerState
import com.umbrella.timer.domain.TimerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val timerRepository: TimerRepository) : ViewModel() {

    private var timerState: TimerState = TimerState.Stopped
    private var timerIsRunning = false

    private val _timerLiveData = MutableLiveData<Long>()
    val timerLiveData: LiveData<Long> = _timerLiveData

    fun startTimer() {
        if (!timerIsRunning) {
            timerIsRunning = true
            viewModelScope.launch(Dispatchers.IO) {
                timerRepository.startTimer(timerState).collect {
                    _timerLiveData.postValue(it)
                }
            }
        }
    }

    fun pauseTimer() {
        timerIsRunning = false
        viewModelScope.coroutineContext.cancelChildren()
        timerLiveData.value?.let {
            timerState = TimerState.Paused(it)
        }
    }

    fun stopTimer() {
        timerIsRunning = false
        viewModelScope.coroutineContext.cancelChildren()
        timerState = TimerState.Stopped
        _timerLiveData.value = 0
    }

    fun format(timestamp: Long): String {
        val millisecondsFormatted = (timestamp % 1000).pad(3)
        val seconds = timestamp / 1000
        val secondsFormatted = (seconds % 60).pad(2)
        val minutes = seconds / 60
        val minutesFormatted = (minutes % 60).pad(2)
        val hours = minutes / 60
        return if (hours > 0) {
            val hoursFormatted = (minutes / 60).pad(2)
            "$hoursFormatted:$minutesFormatted:$secondsFormatted"
        } else {
            "$minutesFormatted:$secondsFormatted:$millisecondsFormatted"
        }
    }

    private fun Long.pad(desiredLength: Int) = this.toString().padStart(desiredLength, '0')
}