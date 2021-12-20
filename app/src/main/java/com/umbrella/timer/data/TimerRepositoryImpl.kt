package com.umbrella.timer.data

import com.umbrella.timer.domain.TimerRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TimerRepositoryImpl : TimerRepository {

    override fun startTimer(timerState: TimerState): Flow<Long> {
        when (timerState) {
            is TimerState.Stopped -> {
                val timerStartedTime = System.currentTimeMillis()
                return flow {
                    while (true) {
                        delay(30)
                        emit(System.currentTimeMillis() - timerStartedTime)
                    }
                }
            }
            is TimerState.Paused -> {
                val timerStartedTime = System.currentTimeMillis()
                return flow {
                    while (true) {
                        delay(30)
                        emit(timerState.timerPausedValue + (System.currentTimeMillis() - timerStartedTime))
                    }
                }
            }
        }
    }
}