package com.umbrella.timer.data

sealed class TimerState {

    data class Paused(
        val timerPausedValue: Long,
    ) : TimerState()

    object Stopped : TimerState()
}