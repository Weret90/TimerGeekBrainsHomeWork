package com.umbrella.timer.domain

import com.umbrella.timer.data.TimerState
import kotlinx.coroutines.flow.Flow

interface TimerRepository {

    fun startTimer(timerState: TimerState): Flow<Long>
}